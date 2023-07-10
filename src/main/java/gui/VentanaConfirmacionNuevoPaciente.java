package gui;

import model.Healthful;
import model.Paciente;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;

public class VentanaConfirmacionNuevoPaciente extends Ventana {
    private final Healthful healthful;
    private final Usuario usuario;
    private final Paciente paciente;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public VentanaConfirmacionNuevoPaciente(Healthful healthful, Usuario usuario, Paciente paciente) {
        super("Nuevo Paciente", 450, 550);
        this.healthful = healthful;
        this.paciente = paciente;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonAgendar();
        generarBotonCancelar();
        generarLabelInfo();
    }

    private void generarLabelInfo() {
        super.generarJLabel("Rut:", 20, 50, 200, 20);
        super.generarJLabel(usuario.getRut(), 230, 50, 200, 20);

        super.generarJLabel("Contraseña:", 20, 100, 200, 20);
        super.generarJLabel(usuario.getPassword(), 230, 100, 200, 20);

        super.generarJLabel("Rol:", 20, 150, 200, 20);
        super.generarJLabel(usuario.getRol().toString(), 230, 150, 200, 20);

        super.generarJLabel("Nombre:", 20, 200, 200, 20);
        super.generarJLabel(paciente.getNombreCompleto(), 230, 200, 200, 20);

        String fecha = paciente.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        super.generarJLabel("Fecha de Nacimiento:", 20, 250, 200, 20);
        super.generarJLabel(fecha, 230, 250, 200, 20);

        super.generarJLabel("Sexo:", 20, 300, 200, 20);
        super.generarJLabel(paciente.getSexo().toString(), 230, 300, 200, 20);

        super.generarJLabel("Estado Civil:", 20, 350, 200, 20);
        super.generarJLabel(paciente.getEstadoCivil().toString(), 230, 350, 200, 20);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 75, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarBotonAgendar() {
        String textoBotonCancelar = "Guardar";
        botonGuardar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(botonGuardar);
        botonGuardar.addActionListener(this);
    }

    private void generarEncabezado() {
        String textoCabecera = "Confirmación Nuevo Paciente";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonGuardar) {
            healthful.addUsuario(usuario);
            healthful.addPaciente(paciente);
            JOptionPane.showMessageDialog(this, "Paciente creado exitosamente");
            new VentanaMenuAdmin(healthful);
            this.dispose();
        }
        if (event.getSource() == botonCancelar) {
            new VentanaMenuAdmin(healthful);
            this.dispose();
        }
    }
}
