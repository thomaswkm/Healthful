package gui;

import model.EstadoCivil;
import model.Healthful;
import model.Paciente;
import model.Sexo;
import model.Usuario;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class VentanaFormularioPaciente extends Ventana {
    private final Healthful healthful;
    private final Usuario usuario;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JXDatePicker datePicker;
    private JComboBox listaSexo;
    private JComboBox listaEstadoCivil;
    private JButton botonContinuar;
    private JButton botonCancelar;


    public VentanaFormularioPaciente(Healthful healthful, Usuario usuario) {
        super("Formulario Paciente", 500, 520);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarCampoNombre();
        generarCampoApellido();
        generarDatePicker();
        generarListaSexo();
        generarListaEstadoCivil();
        generarBotonCancelar();
        generarBotonContinuar();
    }

    private void generarListaEstadoCivil() {
        super.generarJLabel("Estado Civil:", 20, 250, 150, 20);
        listaEstadoCivil = super.generarListaDesplegable(EstadoCivil.values(), 200, 250, 250, 20);
        listaEstadoCivil.setSelectedIndex(-1);
        listaEstadoCivil.addActionListener(this);
        this.add(listaEstadoCivil);
    }

    private void generarListaSexo() {
        super.generarJLabel("Sexo:", 20, 200, 150, 20);
        listaSexo = super.generarListaDesplegable(Sexo.values(), 200, 200, 250, 20);
        listaSexo.setSelectedIndex(-1);
        listaSexo.addActionListener(this);
        this.add(listaSexo);
    }

    private void generarDatePicker() {
        String texto = "Fecha de Nacimiento:";
        super.generarJLabel(texto, 20, 150, 150, 20);
        UIManager.put(CalendarHeaderHandler.uiControllerID, SpinningCalendarHeaderHandler.class.getName());
        datePicker = new JXDatePicker();
        datePicker.setBounds(200, 150, 200, 20);
        datePicker.getMonthView().setZoomable(true);
        datePicker.setFormats("dd-MM-yyyy");
        this.add(datePicker);
    }

    private void generarCampoApellido() {
        String textoNombre = "Apellido:";
        super.generarJLabel(textoNombre, 20, 100, 150, 20);
        campoApellido = super.generarJTextField(200, 100, 250, 20);
        this.add(campoApellido);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre:";
        super.generarJLabel(textoNombre, 20, 50, 150, 20);
        campoNombre = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombre);
    }

    private void generarBotonContinuar() {
        String textoBoton = "Continuar";
        botonContinuar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(botonContinuar);
        botonContinuar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBoton = "Cancelar";
        botonCancelar = super.generarBoton(textoBoton, 275, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarEncabezado() {
        String textoCabecera = "Complete Ficha del Paciente";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    private Paciente crearPaciente() {
        Date date = datePicker.getDate();
        LocalDate fechaNacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Paciente(usuario.getRut(),
                campoNombre.getText(),
                campoApellido.getText(),
                fechaNacimiento,
                (Sexo) (listaSexo.getSelectedItem()),
                ((EstadoCivil) (listaEstadoCivil.getSelectedItem())),
                new ArrayList<>()
        );
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonContinuar) {
            if (camposNoValidos()) return;
            new VentanaConfirmacionNuevoPaciente(healthful, usuario, crearPaciente());
            this.dispose();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuAdmin(healthful);
            this.dispose();
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

    private void validarCampos() {
        String nombre = campoNombre.getText().trim();
        String apellido = campoApellido.getText();
        String regex = "^[a-zA-Z]{2,}$";
        Date fecha = datePicker.getDate();
        int sexoSelectedIndex = listaSexo.getSelectedIndex();
        int estadoCivilSelectedIndex = listaEstadoCivil.getSelectedIndex();

        if (nombre.isBlank() || apellido.isBlank() || fecha == null || sexoSelectedIndex == -1 || estadoCivilSelectedIndex == -1) {
            throw new RuntimeException("Debe completar todos los campos");
        }
        if (!nombre.matches(regex)) {
            throw new RuntimeException(nombre + " => No es un nombre válido ");
        }
        if (!apellido.matches(regex)) {
            throw new RuntimeException(apellido + " => No es un apellido válido ");
        }
        if (fecha.after(Date.from(Instant.now()))) {
            throw new RuntimeException("No puede seleccionar una fecha posterior a hoy");
        }
    }
}
