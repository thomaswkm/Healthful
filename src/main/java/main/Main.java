package main;

import data.GestorDeArchivos;
import gui.VentanaMenuBienvenida;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Usuario> usuarios = GestorDeArchivos.leerUsuarios("usuarios.txt");
//        List<Paciente> pacientes = GestorDeArchivos.leerPacientes("pacientes.txt");
//        List<Medico> medicos = GestorDeArchivos.leerMedicos("medicos.txt");
//        List<Cita> citas = GestorDeArchivos.leerCitas("citas.txt");

//        Healthful healthful = new Healthful(usuarios, pacientes, medicos, citas);
        Healthful healthful = new Healthful(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//        Menu menu = new Menu(healthful);
//        menu.inicio();
        new VentanaMenuBienvenida(healthful);
    }
}