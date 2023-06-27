package model;

import data.GestorArchivo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Healthful healthful;
    private String rut;

    public Menu(Healthful healthful) {
        this.healthful = healthful;
    }

    public void inicio() {
        System.out.println(textoInicio());
        procesarOpcionIngresada(ingresarOpcion(3));
    }

    public void login() {
    }

    public void verificarRol(String rut) {
        if (existePacienteConRut(rut) != null) {
            new MenuPaciente(existePacienteConRut(rut), healthful).menu();
        } else if (existeMedicoConRut(rut) != null) {
            new MenuMedico(existeMedicoConRut(rut), healthful).menu();
        }
    }

    public Usuario ingresarDatos() {
        String rut = solicitarRut();
        this.rut = rut;
        System.out.println("Ingresa tu contraseña: ");
        String contraseña = new Scanner(System.in).nextLine();
        return new Usuario(rut, contraseña, Rol.PACIENTE);
    }

    public String solicitarRut() {
        System.out.println("Ingrese su RUT (sin puntos ni guión)");
        String rut = new Scanner(System.in).nextLine();

        try {
            return validarFormatoRut(rut);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return solicitarRut();
        }
    }

    public String validarFormatoRut(String rut) {
        if (!rut.matches("^\\d{7,8}[Kk|\\d]$")) {
            throw new RuntimeException("El formato del RUT no es válido");
        }
        return rut;
    }

    public Paciente existePacienteConRut(String rut) {
        for (Paciente paciente : healthful.getPacientes()) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }

    public Medico existeMedicoConRut(String rut) {
        for (Medico medico : healthful.getMedicos()) {
            if (medico.getRut().equals(rut)) {
                return medico;
            }
        }
        return null;
    }

    public int ingresarOpcion(int cantidadOpciones) {
        Scanner teclado = new Scanner(System.in);
        try {
            return validarOpcionIngresada(teclado.nextInt(), cantidadOpciones);
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un numero");
            return ingresarOpcion(cantidadOpciones);
        }
    }

    public int validarOpcionIngresada(int opcionIngresada, int cantidadOpciones) {
        if (opcionIngresada < 1 || opcionIngresada > cantidadOpciones) {
            System.out.println("Ingrese una opcion valida");
            return ingresarOpcion(cantidadOpciones);
        }
        return opcionIngresada;
    }

    public void procesarOpcionIngresada(int opcionIngresada) {
        switch (opcionIngresada) {
            case 1 -> menuRegistro();
            case 2 -> login();
            case 3 -> {
                guardarCambios();
                System.exit(0);
            }
        }
        inicio();
    }

    public void guardarCambios() {
        GestorArchivo.guardarUsuarios("usuarios.txt", healthful);
        GestorArchivo.guardarPacientes("pacientes.txt", healthful);
        GestorArchivo.guardarMedicos("medicos.txt", healthful);
        GestorArchivo.guardarCitas("citas.txt", healthful);
    }

    public void menuRegistro() {
    }

    public String textoInicio() {
        return """
                Bienvenido a Healthful. Seleccione una opción:
                1. Registrarse
                2. Ingresar
                3. Salir
                """;
    }
}
