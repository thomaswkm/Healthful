import java.util.Scanner;

public class Healthful {
    public static void main(String[] args) {
        String[][] pacientes = {{"203672403","thomas123"},{"21423562k","testpassw"},{"185623510","pass2"}};
        login(pacientes);
    }

    public static void login(String[][] pacientes){
        String rut = solicitarRut();
        String contraseña = "";
        if(retornarIndicePaciente(pacientes,rut)!=-1) {
            contraseña = ingresarContraseña();
        }
        if(validacion(pacientes,rut,contraseña)){
            System.out.println(menuPaciente());
        }else{
            System.out.println("Datos incorrectos.");
            login(pacientes);
        }
    }

    public static String solicitarRut(){
        System.out.println("Ingrese su RUT (sin puntos ni guión)");
        String rut = "";
        try{
            rut = ingresarRut();
        }catch (Exception e){
            System.out.println("Rut no válido");
        }
        return rut;
    }

    public static String ingresarRut() throws Exception {
        Scanner sc = new Scanner(System.in);
        String rut = sc.nextLine();
        if (!rut.matches("[0-9]{7,8}[Kk0-9]{1}")) {
            throw new Exception("El formato del RUT no es válido");
        }
        return rut;
    }

    public static String ingresarContraseña() {
        System.out.println("Ingrese su contraseña");
        return new Scanner(System.in).nextLine();
    }

    public static boolean validacion(String[][] pacientes, String rut, String contraseña){
        int indice;
        try {
            indice = retornarIndicePaciente(pacientes, rut);
            if (pacientes[indice][1].equals(contraseña)) {  //pensando en que la contraseña se guarda en el indice 1.
                return true;
            }
        }catch (Exception e){
            System.out.println("El paciente no fue encontrado.");
        }
        return false;
    }

    public static int retornarIndicePaciente(String[][] pacientes, String rut){
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i][0].equals(rut)){
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
                5. Salir
                -> Ingrese una opcion:
                """;
    }
    public static String menuPersonal() {
        return """
                Bienvenido, elija una opción:
                1. Mostrar ficha paciente.
                2. Salir
                -> Ingrese una opcion:
                """;
    }

}
