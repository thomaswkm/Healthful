package gui;

import model.Healthful;
import model.Medico;
import model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class VentanaSeleccionMedico extends Ventana {
    private final Healthful healthful;
    private final Paciente paciente;
    private JComboBox<Medico> listaMedicos;
    private JLabel labelEspecialidad;
    private JButton botonContinuar;
    private JButton botonCancelar;

    public VentanaSeleccionMedico(Healthful healthful, Paciente paciente) {
        super("Agendar Cita", 450, 550);
        this.healthful = healthful;
        this.paciente = paciente;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarListaMedicos();
        generarBotonContinuar();
        generarBotonCancelar();
        generarLabelEspecialidad();
    }

    private void generarLabelEspecialidad() {
        super.generarJLabel("Especialidad:", 20, 200, 200, 20);
        labelEspecialidad = new JLabel("-");
        labelEspecialidad.setBounds(230, 200, 200, 20);
        this.add(labelEspecialidad);
    }

    private void actualizarLabelEspecialidad(String especialidad) {
        labelEspecialidad.setText(especialidad);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarBotonContinuar() {
        String textoBoton = "Continuar";
        botonContinuar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(botonContinuar);
        botonContinuar.addActionListener(this);
    }

    private void generarListaMedicos() {
        super.generarJLabel("Médico:", 20, 100, 200, 20);
        Medico[] arreglo = healthful.getMedicos().toArray(Medico[]::new);
        listaMedicos = super.generarListaDesplegable(arreglo, 230, 100, 200, 20);
        listaMedicos.setSelectedIndex(-1);
        listaMedicos.addActionListener(this);
        this.add(listaMedicos);
    }

    private void generarEncabezado() {
        String textoCabecera = "Seleccione un médico para su atención";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == listaMedicos) {
            if (listaMedicos.getSelectedItem() != null) {
                String especialidad = ((Medico) (listaMedicos.getSelectedItem())).getEspecialidad();
                actualizarLabelEspecialidad(especialidad);
            }
            return;
        }
        if (event.getSource() == botonContinuar) {
            if (listaMedicos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un médico");
                return;
            }
            new VentanaSeleccionFecha(healthful, paciente, (Medico) (listaMedicos.getSelectedItem()));
            this.dispose();
        }
        if (event.getSource() == botonCancelar) {
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }
    }
}
