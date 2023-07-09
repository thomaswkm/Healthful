package gui;

import model.Healthful;
import model.Medico;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class VentanaMenuMedico extends Ventana {
    private final Healthful healthful;
    private final Medico medico;
    private JButton botonVerPacientes;
    private JButton botonVerAgenda;
    private JButton botonVerCitas;
    private JButton botonCerrarSesion;

    public VentanaMenuMedico(Healthful healthful, Medico medico) {
        super("Menú Medico", 500, 520);
        this.healthful = healthful;
        this.medico = medico;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonVerPacientes();
        generarBotonVerAgenda();
        generarBotonVerCitas();
        generarBotonCerrarSesion();
    }

    private void generarBotonCerrarSesion() {
        String textoBoton = "Cerrar Sesión";
        botonCerrarSesion = super.generarBoton(textoBoton, getCentro(150), 250, 150, 20);
        this.add(botonCerrarSesion);
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.addActionListener(this);
    }

    private int getCentro(int largo) {
        return this.getWidth() / 2 - largo;
    }

    private void generarBotonVerCitas() {
        String textoBoton = "Ver Citas";
        botonVerCitas = super.generarBoton(textoBoton, getCentro(200), 150, 200, 40);
        this.add(botonVerCitas);
        botonVerCitas.addActionListener(this);
    }

    private void generarBotonVerAgenda() {
        String textoBoton = "Ver Agenda";
        botonVerAgenda = super.generarBoton(textoBoton, getCentro(200), 100, 200, 40);
        this.add(botonVerAgenda);
        botonVerAgenda.addActionListener(this);
    }

    private void generarBotonVerPacientes() {
        String textoBoton = "Ver Pacientes";
        botonVerPacientes = super.generarBoton(textoBoton, getCentro(200), 50, 200, 40);
        this.add(botonVerPacientes);
        botonVerPacientes.addActionListener(this);
    }

    private void generarMensajeMenu() {
        super.generarJLabelEncabezado("Menú Médico", 20, 30, 500, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonVerPacientes) {
            new VentanaVerPacientes(healthful, medico);
            this.dispose();
        }
        if (event.getSource() == botonVerAgenda) {
            //TODO
        }
        if (event.getSource() == botonVerCitas) {
            new VentanaVerCitas(healthful, medico);
            this.dispose();
        }
        if (event.getSource() == botonCerrarSesion) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }
}
