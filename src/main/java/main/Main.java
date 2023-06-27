package main;

import data.GestorArchivo;
import model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Usuario> usuarios = GestorArchivo.leerUsuarios("usuarios.txt");
        List<Paciente> pacientes = GestorArchivo.leerPacientes("pacientes.txt");
        List<Medico> medicos = GestorArchivo.leerMedicos("medicos.txt");
        List<Cita> citas = GestorArchivo.leerCitas2("citas.txt");

        Healthful healthful = new Healthful(usuarios, pacientes, medicos, citas);
        Menu menu = new Menu(healthful);
        menu.inicio();
    }
}