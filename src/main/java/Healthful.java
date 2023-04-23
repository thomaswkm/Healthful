import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Healthful {
    public static void main(String[] args) {
        //Para esta versión, primer indice corresponde a RUT, segundo a contraseña, rol usuario.
        String[][] usuarios = {
                {"203672403", "thomas123", "Personal"},
                {"21423562k", "testpassw", "Paciente"},
                {"185623510", "pass2", "Personal"}
        };
        login(usuarios);
    }

    public static void login(String[][] usuarios) {
        String rut = solicitarRut();
        String[] usuario = buscarUsuarioPorRut(usuarios, rut);

        if (usuarioNoEstaRegistrado(usuario)) {
            System.out.println("Usuario no registrado");
            login(usuarios);
            return;
        }

        String password = solicitarPassword();
        if (noEsPasswordValida(usuario, password)) {
            System.out.println("La contraseña ingresada no es válida");
            login(usuarios);
            return;
        }

        mostrarMenuSegunRolUsuario(usuario);
    }

    private static void mostrarMenuSegunRolUsuario(String[] usuario) {
        if (usuario[2].equals("Personal")) {
            menuPersonal(usuario);
        } else {
            menuPaciente(usuario);
        }
    }

    public static boolean usuarioNoEstaRegistrado(String[] usuario) {
        return Arrays.equals(usuario, new String[usuario.length]);
    }

    public static void procesarOpcionIngresadaMenuPaciente(String[] paciente, int opcionIngresada) {
        switch (opcionIngresada) {
            case 1 -> mostrarHorasDisponibles();
            case 2 -> mostrarMedicos();
            case 3 -> solicitarCita(paciente);
            case 4 -> cancelarCita(paciente);
            case 5 -> mostrarHorasAgendadas(paciente);
            case 6 -> salir();
        }
        menuPaciente(paciente);
    }

    public static void procesarOpcionIngresadaMenuPersonal(String[] personal, int opcionIngresada) {
        switch (opcionIngresada) {
            case 1 -> mostrarFichaPacientes(personal);
            case 2 -> salir();
        }
        menuPersonal(personal);
    }

    private static void mostrarFichaPacientes(String[] personal) {
        System.out.println("Funcionalidad por implementar");
    }

    private static void salir() {
        System.out.println("Saliendo...");
        System.exit(0);
    }

    public static String solicitarRut() {
        System.out.println("Ingrese su RUT (sin puntos ni guión)");
        String rut = new Scanner(System.in).nextLine();

        try {
            return validarFormatoRut(rut);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return solicitarRut();
        }
    }

    public static String validarFormatoRut(String rut) {
        if (!rut.matches("^\\d{7,8}[Kk|\\d]$")) {
            throw new RuntimeException("El formato del RUT no es válido");
        }
        return rut;
    }

    public static String solicitarPassword() {
        System.out.println("Ingrese su contraseña");
        return new Scanner(System.in).nextLine();
    }

    public static boolean noEsPasswordValida(String[] usuario, String passwordIngresada) {
        return !usuario[1].equals(passwordIngresada);
    }

    public static String[] buscarUsuarioPorRut(String[][] usuarios, String rutIngresado) {
        for (String[] usuario : usuarios) {
            if (usuario[0].equals(rutIngresado)) {
                return usuario;
            }
        }
        return new String[usuarios[0].length];
    }

    public static void menuPaciente(String[] usuario) {
        System.out.println(textoMenuPaciente());
        procesarOpcionIngresadaMenuPaciente(usuario, ingresarOpcion(6));
    }

    private static int validarOpcionIngresada(int opcionIngresada, int cantidadOpciones) {
        if (opcionIngresada < 1 || opcionIngresada > cantidadOpciones) {
            System.out.println("Ingrese una opcion valida");
            return ingresarOpcion(cantidadOpciones);
        }
        return opcionIngresada;
    }

    private static int ingresarOpcion(int cantidadOpciones) {
        Scanner teclado = new Scanner(System.in);
        try {
            return validarOpcionIngresada(teclado.nextInt(), cantidadOpciones);
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un numero");
            return ingresarOpcion(cantidadOpciones);
        }
    }

    public static void menuPersonal(String[] usuario) {
        System.out.println(textoMenuPersonal());
        procesarOpcionIngresadaMenuPersonal(usuario, ingresarOpcion(2));
    }

    public static void solicitarCita(String[] usuario) {
        System.out.println("Introduzca la fecha de la cita:");
        String cita = new Scanner(System.in).nextLine();

//        pacientes[buscarUsuarioPorRut(pacientes, rut)][2] = cita;
        System.out.println("Funcionalidad por implementar");
    }

    public static void cancelarCita(String[] usuario) {
        System.out.println("Funcionalidad por implementar");
    }

    public static void mostrarHorasDisponibles() {
        System.out.println("Funcionalidad por implementar");
    }

    public static void mostrarMedicos() {
        System.out.println("Funcionalidad por implementar");
    }

    public static void mostrarHorasAgendadas(String[] usuario) {
        System.out.println("Funcionalidad por implementar");
    }

    private static String textoMenuPersonal() {
        return """
                Bienvenido, elija una opción:
                1. Mostrar ficha paciente.
                2. Salir
                -> Ingrese una opcion:""";
    }

    private static String textoMenuPaciente() {
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
