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
        String password = "";
        if (retornarIndicePaciente(pacientes, rut) != -1) {
            password = ingresarPassword();
        }
        if (validacion(pacientes, rut, password)) {
            menu(pacientes, rut);
        } else {
            System.out.println("Datos incorrectos.");
            login(pacientes);
        }
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

    public static void respuesta(String[][] pacientes, String rut, int n) {
        Scanner sc = new Scanner(System.in);
        switch (n) {
            case 1 -> mostrarHorasDisponibles();
            case 2 -> mostrarMedicos();
            case 3 -> {
                System.out.println("Introduce la fecha:");
                solicitarCita(pacientes, rut, sc.nextLine());
            }
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
        String rut = "";
        try {
            rut = ingresarRut();
        } catch (Exception e) {
            System.out.println("Rut no válido");
        }
        return rut;
    }

    public static String ingresarRut() throws Exception {
        Scanner sc = new Scanner(System.in);
        String rut = sc.nextLine();

        if (!rut.matches("^\\d{7,8}[Kk|\\d]$")) {
            throw new Exception("El formato del RUT no es válido");
        }
        return rut;
    }

    public static String ingresarPassword() {
        System.out.println("Ingrese su contraseña");
        return new Scanner(System.in).nextLine();
    }

    public static boolean validacion(String[][] pacientes, String rut, String password) {
        int indice;
        try {
            indice = retornarIndicePaciente(pacientes, rut);
            if (pacientes[indice][1].equals(password)) {  //pensando en que la contraseña se guarda en el indice 1.
                return true;
            }
        } catch (Exception e) {
            System.out.println("El paciente no fue encontrado.");
        }
        return false;
    }

    public static int retornarIndicePaciente(String[][] pacientes, String rut) {
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

    public static void solicitarCita(String[][] pacientes, String rut, String cita) {
        pacientes[retornarIndicePaciente(pacientes, rut)][2] = cita;
    }

    public static void cancelarCita(String[][] pacientes, String rut) {
        pacientes[retornarIndicePaciente(pacientes, rut)][2] = "";
    }

    public static void mostrarHorasDisponibles() {
    }

    public static void mostrarMedicos() {
    }

    public static void mostrarHorasAgendadas(String[][] pacientes, String rut) {
        System.out.println(pacientes[retornarIndicePaciente(pacientes, rut)][2]);
    }

}
