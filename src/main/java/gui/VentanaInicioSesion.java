package gui;

import model.Healthful;
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
            ingresarUsuario();
        }
        if (event.getSource() == botonCancelar) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }

    private void ingresarUsuario() {
        try {
            Usuario usuario = healthful.login(campoRut.getText(), String.valueOf(campoPassword.getPassword()));
            nuevaVentana(usuario, healthful);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        this.dispose();
    }

    private void nuevaVentana(Usuario usuario, Healthful healthful) {
        switch (usuario.getRol()) {
            case PACIENTE -> new VentanaMenuPaciente(healthful, healthful.obtenerPaciente(usuario.getRut()));
            case MEDICO -> new VentanaMenuMedico(healthful, healthful.obtenerMedico(usuario.getRut()));
            case ADMIN -> new VentanaMenuAdmin(healthful);
        }
    }
}
