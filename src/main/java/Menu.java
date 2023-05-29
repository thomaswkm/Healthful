import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Healthful h;
    private GestorArchivo ga;
    private String rut;

    public Menu(GestorArchivo ga, Healthful h){
        this.ga = ga;
        this.h = h;
    }

    public void inicio() {
        System.out.println(textoInicio());
        procesarOpcionIngresada(ingresarOpcion(3));
    }

    public void login(){
        if (ga.login(ingresarDatos())) {
            verificarRol(rut);
        } else {
            inicio();
        }
    }

    public void verificarRol(String rut) {
        if(existePacienteConRut(rut)!=null){
            new MenuPaciente(existePacienteConRut(rut),h).menu();
        } else if (existeMedicoConRut(rut)!=null) {
            new MenuMedico(existeMedicoConRut(rut),h).menu();
        }
    }

    public Usuario ingresarDatos() {
        String rut = solicitarRut();
        this.rut = rut;
        System.out.println("Ingresa tu contraseña: ");
        String contraseña = new Scanner(System.in).nextLine();
        return new Usuario(rut,contraseña);
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
        for (Paciente paciente : h.getPacientes()) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }

    public Medico existeMedicoConRut(String rut) {
        for (Medico medico : h.getMedicos()) {
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
        ga.guardarUsuarios("usuarios.txt", h);
        ga.guardarPacientes("pacientes.txt", h);
        ga.guardarMedicos("medicos.txt", h);
        ga.guardarCitas("citas.txt", h);
    }

    public void menuRegistro() {
        Usuario u = ingresarDatos();
        if(ga.registrarUsuario(u)){
            h.addUsuario(u);
            System.out.println("Desea registrarse como Paciente o Medico");

            Scanner scanner = new Scanner(System.in);
            String respuesta = "";

            while (!respuesta.equalsIgnoreCase("Paciente") && !respuesta.equalsIgnoreCase("Medico")) {
                respuesta = scanner.nextLine();

                if (!respuesta.equalsIgnoreCase("Paciente") && !respuesta.equalsIgnoreCase("Medico")) {
                    System.out.println("Opción inválida. Por favor, ingrese 'Paciente' o 'Medico'.");
                }
            }
            System.out.println("Ingresa tu nombre: ");
            String nombre = new Scanner(System.in).nextLine();

            if(respuesta.equals("Paciente")){
                Paciente p = new Paciente(u.toString().split(",")[0],nombre,new ArrayList<>());
                h.addPaciente(p);
            } else if (respuesta.equals("Medico")) {
                System.out.println("Ingresa una especialidad: ");
                String especialidad = new Scanner(System.in).nextLine();
                Medico m = new Medico(u.toString().split(",")[0],nombre,especialidad,new ArrayList<>());
                h.addMedico(m);
            }
        }
    }

    public String textoInicio(){
        return """
                Bienvenido a Healthful. Seleccione una opción:
                1. Registrarse
                2. Ingresar
                3. Salir
                """;
    }
}
