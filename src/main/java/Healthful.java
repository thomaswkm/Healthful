import java.sql.SQLOutput;
import java.util.Scanner;

public class Healthful {
    public static void main(String[] args) {
        login();
    }

    public static void login(){
        String rut = solicitarRut();
        String contraseña = ingresarContraseña(); // hay un bug aca, si se introduce mal una contraseña n veces, al terminar de mostrar el menu pide la contraseña n veces.
        if(validacion(rut,contraseña)){
            System.out.println(menuPaciente());
        }else{
            System.out.println("Datos incorrectos.");
            login();
        }
    }

    public static String solicitarRut(){
        System.out.println("Ingrese su RUT (sin puntos ni guión)");
        String rut = "";
        try{
            rut = ingresarRut();
        }catch (Exception e){
            System.out.println("Rut no válido");
            login();
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

    public static boolean validacion(String rut, String contraseña){
        //acá tendría que haber una forma de comparar la contraseña con una ya almacenada, puede ser en un archivo.txt(más adelante) o simplemente definirla en el main(por ahora).
        return true;
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
