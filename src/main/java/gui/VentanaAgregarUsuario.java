package gui;

import model.Healthful;
import model.Rol;
import model.Usuario;
import utils.ValidadorRut;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.InternationalFormatter;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class VentanaAgregarUsuario extends Ventana {
    private final Healthful healthful;
    private JPasswordField campoPassword;
    private JButton botonContinuar;
    private JButton botonCancelar;
    private JComboBox listaRoles;
    private JFormattedTextField campoRut;

    public VentanaAgregarUsuario(Healthful healthful) {
        super("Agregar Usuario", 500, 520);
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCampoRut();
        generarCampoPassword();
        generarBotonGuardar();
        generarBotonCancelar();
        generarListaRoles();
    }

    private void generarListaRoles() {
        Rol[] roles = Arrays.stream(Rol.values())
                .filter(rol -> rol != Rol.ADMIN)
                .toArray(Rol[]::new);

        super.generarJLabel("Rol:", 20, 150, 150, 20);
        listaRoles = super.generarListaDesplegable(roles, 200, 150, 250, 20);
        listaRoles.setSelectedIndex(-1);
        listaRoles.addActionListener(this);
        this.add(listaRoles);
    }

    private void generarCampoRut() {
        String textoRut = "Rut (Sin puntos ni guión):";
        super.generarJLabel(textoRut, 20, 50, 150, 20);

        InternationalFormatter formato = super.generarFormato(1_0, 99_999_999_9);
        campoRut = super.generarJFormattedTextField(formato, 200, 50, 250, 20);

        this.add(campoRut);
    }

    private void generarCampoPassword() {
        String textoPassword = "Contraseña:";
        super.generarJLabel(textoPassword, 20, 100, 150, 20);
        campoPassword = new JPasswordField();
        campoPassword.setBounds(200, 100, 250, 20);
        this.add(campoPassword);
    }

    private void generarBotonGuardar() {
        botonContinuar = generarBoton("Continuar", 50, 400, 100, 30);
        this.add(botonContinuar);
        botonContinuar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar", 200, 400, 100, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private Usuario crearUsuario() {
        return new Usuario(campoRut.getText(), new String(campoPassword.getPassword()), (Rol) (listaRoles.getSelectedItem()));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonContinuar) {
            if (camposNoValidos()) return;
            if (usuarioExistente()) return;
            nuevaVentana();
            this.dispose();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuAdmin(healthful);
            this.dispose();
        }
    }

    private void nuevaVentana() {
        if (listaRoles.getSelectedItem() == Rol.PACIENTE) {
            new VentanaFormularioPaciente(healthful, crearUsuario());
        } else {
            new VentanaFormularioMedico(healthful, crearUsuario());
        }
    }

    private boolean camposNoValidos() {
        try {
            validarCampos();
            return false;
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return true;
        }
    }

    private boolean usuarioExistente() {
        String rut = campoRut.getText();
        try {
            healthful.buscarUsuario(rut);
            JOptionPane.showMessageDialog(this, "El rut: " + rut + ". Ya se encuentra registrado");
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private void validarCampos() {
        String password = new String(campoPassword.getPassword());
        String rut = campoRut.getText();

        if (rut.isBlank() || listaRoles.getSelectedIndex() == -1 || password.isBlank()) {
            throw new RuntimeException("Debe completar todos los campos");
        }
        if (!ValidadorRut.validar(rut)) {
            throw new RuntimeException(rut + " No es un rut válido.");
        }
        if (password.length() != 4) {
            throw new RuntimeException("La contraseña debe contener 4 caracteres");
        }
    }
}
