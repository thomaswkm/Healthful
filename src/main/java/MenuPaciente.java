import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPaciente {
    private Paciente p;

    public void menu() {
        System.out.println(textoMenu());
        procesarOpcionIngresada(ingresarOpcion(6));
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
            case 1 -> p.mostrarHorasDisponibles();
            case 2 -> p.mostrarMedicos();
            case 3 -> p.solicitarHora();
            case 4 -> p.cancelarHora();
            case 5 -> p.mostrarHorasAgendadas();
            case 6 -> System.exit(1);
        }
        menu();
    }

    public String textoMenu() {
        return """
                Bienvenido, elija una opción:
                1. Ver horas disponibles.
                2. Buscar médicos disponibles.
                3. Solicitar una cita.
                4. Cancelar una cita.
                5. Ver horas agendadas.
                6. Salir
                -> Ingrese una opcion:""";
    }
}

