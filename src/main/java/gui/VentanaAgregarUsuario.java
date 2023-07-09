package gui;

import model.Healthful;
import model.Rol;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class VentanaAgregarUsuario extends Ventana {
    private final Healthful healthful;
    private JTextField campoRut, campoPassword, campoRol;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public VentanaAgregarUsuario(Healthful healthful) {
        super("Agregar Usuario", 450, 350);
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCampoRut();
        generarCampoPassword();
        generarCampoRol();
        generarBotonGuardar();
        generarBotonCancelar();
    }

    private void generarCampoRut() {
        String textoRut = "Rut (Sin puntos ni guión):";
        super.generarJLabel(textoRut, 20, 50, 150, 20);
        campoRut = super.generarJTextField(200, 50, 250, 20);
        this.add(campoRut);
    }

    private void generarCampoPassword() {
        String textoPassword = "Contraseña:";
        super.generarJLabel(textoPassword, 20, 100, 150, 20);
        campoPassword = new JPasswordField();
        campoPassword.setBounds(200, 100, 250, 20);
        this.add(campoPassword);
    }

    private void generarCampoRol() {
        String textoRol = "Rol:";
        super.generarJLabel(textoRol, 20, 150, 150, 20);
        campoRol = super.generarJTextField(200, 150, 250, 20);
        this.add(campoRol);
    }

    private void generarBotonGuardar() {
        botonGuardar = generarBoton("Guardar", 50, 200, 100, 30);
        this.add(botonGuardar);
        botonGuardar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar", 200, 200, 100, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private boolean agregarUsuario() {
        return healthful.addUsuario(new Usuario(campoRut.getText(), campoPassword.getText(), Rol.valueOf(campoRol.getText())));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            if (agregarUsuario()) {
                JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar al usuario");
                this.dispose();
            }
        }
        if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }
}
