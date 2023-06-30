package gui;

import model.Healthful;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class VentanaMenuPaciente extends Ventana {
    private final Healthful healthful;
    private JButton botonAgendar;
    private JButton botonCancelar;
    private JButton botonVerCitas;
    private JButton botonCerrarSesion;

    public VentanaMenuPaciente(Healthful healthful) {
        super("Menú Paciente", 500, 520);
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonAgendar();
        generarBotonCancelar();
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

    private void generarBotonCancelar() {
        String textoBoton = "Cancelar Cita";
        botonCancelar = super.generarBoton(textoBoton, getCentro(200), 100, 200, 40);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarBotonAgendar() {
        String textoBoton = "Agendar Cita";
        botonAgendar = super.generarBoton(textoBoton, getCentro(200), 50, 200, 40);
        this.add(botonAgendar);
        botonAgendar.addActionListener(this);
    }

    private void generarMensajeMenu() {
        super.generarJLabelEncabezado("Menú Paciente", 20, 30, 500, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonAgendar) {
            //TODO
        }
        if (event.getSource() == botonCancelar) {
            //TODO
        }
        if (event.getSource() == botonVerCitas) {
            //TODO
        }
        if (event.getSource() == botonCerrarSesion) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }
}
