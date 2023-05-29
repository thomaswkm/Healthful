import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPaciente {
    private Paciente p;
    private Healthful h;
    public MenuPaciente() {
    }

    public MenuPaciente(Paciente p, Healthful h) {
        this.p = p;
        this.h = h;
    }

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
            case 1 -> h.mostrarHorasDisponibles();
            case 2 -> h.mostrarMedicos();
            case 3 -> h.solicitarCita(p);
            case 4 -> h.cancelarCita(p);
            case 5 -> h.mostrarCitasAgendadas(p);
            case 6 -> System.exit(0);
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

