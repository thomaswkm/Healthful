import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMedico {

    private Medico m;
    private Healthful h;

    public MenuMedico() {
    }

    public MenuMedico(Medico m, Healthful h) {
        this.m = m;
        this.h = h;
    }

    public void menu() {
        System.out.println(textoMenu());
        procesarOpcionIngresada(ingresarOpcion(3));
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
            case 1 -> h.mostrarFichaPaciente();
            case 2 -> h.cancelarCita(new Paciente());
            case 3 -> {
                new Menu(new GestorArchivo(),h).guardarCambios();
                System.exit(0);
            }
        }
        menu();
    }

    public String textoMenu() {
        return """
                Bienvenido, elija una opción:
                1. Mostrar ficha paciente.
                2. Cancelar cita.
                3. Salir
                -> Ingrese una opcion:""";
    }
}
