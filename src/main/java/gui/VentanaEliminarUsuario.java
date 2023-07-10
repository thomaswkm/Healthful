package gui;

import model.Healthful;
import model.Rol;
import model.Usuario;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEliminar) {
            if (usuarioNoRegistrado()) return;

            Usuario usuario = healthful.buscarUsuario(campoRut.getText());
            if (esAdmin(usuario)) return;


            borrarSegunRol(usuario);
            healthful.removeUsuario(campoRut.getText());
            JOptionPane.showMessageDialog(this, "Usuario: " + usuario.getRut() + ". Eliminado");
            new VentanaMenuAdmin(healthful);
            this.dispose();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuAdmin(healthful);
            this.dispose();
        }
    }

    private boolean esAdmin(Usuario usuario) {
        if (usuario.getRol() == Rol.ADMIN) {
            JOptionPane.showMessageDialog(this, "No se puede eliminar el ADMIN");
            return true;
        }
        return false;
    }

    private void borrarSegunRol(Usuario usuario) {
        switch (usuario.getRol()) {
            case PACIENTE -> healthful.removePaciente(usuario.getRut());
            case MEDICO -> healthful.removeMedico(usuario.getRut());
        }
    }

    private boolean usuarioNoRegistrado() {
        String rut = campoRut.getText();
        try {
            healthful.buscarUsuario(rut);
            return false;
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "El rut: " + rut + ". No está registrado");
            return true;
        }
    }
}
