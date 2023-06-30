package main;

import data.GestorDeArchivos;
import gui.VentanaMenuBienvenida;
import model.Cita;
import model.Healthful;
import model.Medico;
import model.Paciente;
import model.Usuario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Usuario> usuarios = GestorDeArchivos.leerUsuarios("src/main/resources/data/usuarios.txt");
        List<Paciente> pacientes = GestorDeArchivos.leerPacientes("src/main/resources/data/pacientes.txt");
        List<Medico> medicos = GestorDeArchivos.leerMedicos("src/main/resources/data/medicos.txt");
        List<Cita> citas = GestorDeArchivos.leerCitas("src/main/resources/data/citas.txt");

        Healthful healthful = new Healthful(usuarios, pacientes, medicos, citas);
        new VentanaMenuBienvenida(healthful);
    }
}