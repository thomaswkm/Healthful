package gui;

import model.Healthful;
import model.Usuario;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class VentanaMenuAdmin extends Ventana {
    private final Healthful healthful;
    private final Usuario usuario;
    private JButton botonCerrarSesion;
    private JButton botonAgregarUsuario;
    private JButton botonEliminarUsuario;

    public VentanaMenuAdmin(Healthful healthful, Usuario usuario) {
        super("Menú Admin", 500, 520);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonCerrarSesion();
        generarBotonAgregarUsuario();
        generarBotonEliminarUsuario();
    }

    private void generarBotonEliminarUsuario() {
        String textoBoton = "Eliminar Usuario";
        botonEliminarUsuario = super.generarBoton(textoBoton, getCentro(150), 100, 150, 20);
        this.add(botonEliminarUsuario);
        botonEliminarUsuario.addActionListener(this);
    }

    private int getCentro(int largo) {
        return this.getWidth() / 2 - largo;
    }

    private void generarBotonAgregarUsuario() {
        String textoBoton = "Agregar Usuario";
        botonAgregarUsuario = super.generarBoton(textoBoton, getCentro(150), 50, 150, 20);
        this.add(botonAgregarUsuario);
        botonAgregarUsuario.addActionListener(this);
    }

    private void generarBotonCerrarSesion() {
        String textoBoton = "Cerrar Sesión";
        botonCerrarSesion = super.generarBoton(textoBoton, getCentro(150), 200, 150, 20);
        this.add(botonCerrarSesion);
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.addActionListener(this);
    }

    private void generarEncabezado() {
        String textoCabecera = "Menú Administrador";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonAgregarUsuario) {
            VentanaAgregarUsuario ventanaAgregarUsuario = new VentanaAgregarUsuario(healthful);
        }

        if (e.getSource() == botonEliminarUsuario) {
            VentanaEliminarUsuario ventanaEliminarUsuario = new VentanaEliminarUsuario(healthful);
        }

        if (e.getSource() == botonCerrarSesion) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }
}
