package main;

import data.GestorArchivo;
import model.Healthful;
import model.Menu;

public class Main {
    public static void main(String[] args) {
        Healthful healthful = new Healthful(GestorArchivo.leerUsuarios("usuarios.txt"),
                GestorArchivo.leerPacientes("pacientes.txt"),
                GestorArchivo.leerMedicos("medicos.txt"),
                GestorArchivo.leerCitas("citas.txt"));
        Menu menu = new Menu(healthful);
        menu.inicio();
    }
}