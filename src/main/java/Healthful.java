import java.util.InputMismatchException;
import java.util.Scanner;

public class Healthful {
    public static void main(String[] args) {
        //Para esta versión, primer indice corresponde a RUT, segundo a contraseña, tercero a una cita agendada.
        String[][] pacientes = {
                {"203672403", "thomas123", ""},
                {"21423562k", "testpassw", ""},
                {"185623510", "pass2", ""}};
        login(pacientes);
    }

    public static void login(String[][] pacientes) {
        String rut = solicitarRut();

        if (noEsPacienteRegistrado(pacientes, rut)) {
            System.out.println("Paciente no registrado");
            login(pacientes);
            return;
        }

        String password = ingresarPassword();
        if (noEsPasswordValida(pacientes, rut, password)) {
            System.out.println("La contraseña ingresada no es válida, vuelva a intentarlo");
            login(pacientes);
            return;
        }
        menu(pacientes, rut);
    }

    public static boolean noEsPacienteRegistrado(String[][] pacientes, String rut) {
        return buscarPacientePorRut(pacientes, rut) == -1;
    }

    public static void menu(String[][] pacientes, String rut) {
        Scanner sc = new Scanner(System.in);
        System.out.println(menuPaciente());
        try {
            respuesta(pacientes, rut, sc.nextInt());
        } catch (InputMismatchException ime) {
            System.out.println("Ingrese un número");
        }
        menu(pacientes, rut);
    }

    public static void respuesta(String[][] pacientes, String rut, int opcionIngresada) {
        switch (opcionIngresada) {
            case 1 -> mostrarHorasDisponibles();
            case 2 -> mostrarMedicos();
            case 3 -> solicitarCita(pacientes, rut);
            case 4 -> cancelarCita(pacientes, rut);
            case 5 -> mostrarHorasAgendadas(pacientes, rut);
            case 6 -> salir();
        }
    }

    private static void salir() {
        System.out.println("Saliendo...");
        System.exit(0);
    }

    public static String solicitarRut() {
        System.out.println("Ingrese su RUT (sin puntos ni guión)");
        try {
            return ingresarRut();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return solicitarRut();
        }
    }

    public static String ingresarRut() throws RuntimeException {
        String rut = new Scanner(System.in).nextLine();

        if (!rut.matches("^\\d{7,8}[Kk|\\d]$")) {
            throw new RuntimeException("El formato del RUT no es válido");
        }
        return rut;
    }

    public static String ingresarPassword() {
        System.out.println("Ingrese su contraseña");
        return new Scanner(System.in).nextLine();
    }

    public static boolean noEsPasswordValida(String[][] pacientes, String rut, String password) {
        int indice = buscarPacientePorRut(pacientes, rut);
        try {
            return !pacientes[indice][1].equals(password);  //pensando en que la contraseña se guarda en el indice 1.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El paciente no fue encontrado.");
        }
        return true;
    }

    public static int buscarPacientePorRut(String[][] pacientes, String rut) {
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i][0].equals(rut)) {
                return i;
            }
        }
        return -1;
    }

    public static String menuPaciente() {
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

    public static String menuPersonal() {
        return """
                Bienvenido, elija una opción:
                1. Mostrar ficha paciente.
                2. Salir
                -> Ingrese una opcion:""";
    }

    public static void solicitarCita(String[][] pacientes, String rut) {
        System.out.println("Introduzca la fecha de la cita:");
        String cita = new Scanner(System.in).nextLine();

        pacientes[buscarPacientePorRut(pacientes, rut)][2] = cita;
    }

    public static void cancelarCita(String[][] pacientes, String rut) {
        pacientes[buscarPacientePorRut(pacientes, rut)][2] = "";
    }

    public static void mostrarHorasDisponibles() {
    }

    public static void mostrarMedicos() {
    }

    public static void mostrarHorasAgendadas(String[][] pacientes, String rut) {
        System.out.println(pacientes[buscarPacientePorRut(pacientes, rut)][2]);
    }
}
