package gui;

import model.Healthful;
import model.Rol;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class VentanaInicioSesion extends Ventana {
    private final Healthful healthful;
    private JTextField campoRut;
    private JPasswordField campoPassword;
    private JButton botonIngresar;
    private JButton botonCancelar;

    public VentanaInicioSesion(Healthful healthful) {
        super("Inicio de Sesión", 500, 520);
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonIngresar();
        generarCampoRut();
        generarCampoPassword();
    }

    private void generarEncabezado() {
        String textoCabecera = "Iniciar Sesión";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    private void generarCampoRut() {
        String textoNombre = "Rut (Sin puntos ni guión):";
        super.generarJLabel(textoNombre, 20, 50, 150, 20);
        campoRut = super.generarJTextField(200, 50, 250, 20);
        this.add(campoRut);
    }

    private void generarCampoPassword() {
        String textoNombre = "Contraseña:";
        super.generarJLabel(textoNombre, 20, 100, 150, 20);
        campoPassword = new JPasswordField();
        campoPassword.setBounds(200, 100, 250, 20);
        this.add(campoPassword);
    }

    private void generarBotonIngresar() {
        String textoBoton = "Ingresar";
        botonIngresar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(botonIngresar);
        botonIngresar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonIngresar) {
            ingresarUsuario(healthful);
        }
        if (event.getSource() == botonCancelar) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }

    private void ingresarUsuario(Healthful healthful) {
        Usuario usuario;

        try {
            usuario = this.healthful.login(campoRut.getText(), String.valueOf(campoPassword.getPassword()));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        nuevaVentana(usuario.getRol(), healthful);
        this.dispose();
    }

    private void nuevaVentana(Rol rol, Healthful healthful) {
        switch (rol) {
            case PACIENTE -> new VentanaMenuPaciente(healthful);
            case MEDICO -> new VentanaMenuMedico(healthful);
            case ADMIN -> new VentanaMenuAdmin(healthful);
        }
    }
}
