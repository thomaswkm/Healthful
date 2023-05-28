import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GestorArchivo ga = new GestorArchivo();
        Healthful healthful = new Healthful(ga.leerUsuarios("usuarios.txt"),ga.leerPacientes("pacientes.txt"),ga.leerMedicos("medicos.txt"),ga.leerCitas("citas.txt"));
        Menu menu = new Menu(ga, healthful);
        menu.inicio();
    }
}
