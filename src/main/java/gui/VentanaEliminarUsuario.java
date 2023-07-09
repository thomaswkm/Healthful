package gui;

import model.Healthful;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class VentanaEliminarUsuario extends Ventana {
    private final Healthful healthful;
    private JTextField campoRut;
    private JButton botonEliminar;
    private JButton botonCancelar;

    public VentanaEliminarUsuario(Healthful healthful) {
        super("Eliminar Usuario", 400, 200);
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCampoRut();
        generarBotonEliminar();
        generarBotonCancelar();
    }

    private void generarCampoRut() {
        String textoRut = "Rut del Usuario:";
        super.generarJLabel(textoRut, 50, 50, 150, 20);
        campoRut = super.generarJTextField(200, 50, 150, 20);
        this.add(campoRut);
    }

    private void generarBotonEliminar() {
        botonEliminar = generarBoton("Eliminar", 50, 100, 100, 30);
        this.add(botonEliminar);
        botonEliminar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar", 200, 100, 100, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private boolean eliminarUsuario() {
        return healthful.removeUsuario(campoRut.getText());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEliminar) {
            if (eliminarUsuario()) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar al usuario");
                this.dispose();
            }
        }
        if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }
}
