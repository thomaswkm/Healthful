package gui;

import model.Cita;
import model.Healthful;
import model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaCancelarCita extends Ventana {
    private final Healthful healthful;
    private final Paciente paciente;
    private JButton botonCancelar;
    private JButton botonVolver;
    private JComboBox<String> comboBoxCitas;

    protected VentanaCancelarCita(Healthful healthful, Paciente paciente) {
        super("Cancelar Cita", 450, 550);
        this.healthful = healthful;
        this.paciente = paciente;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarComboBoxCitas();
        generarBotonCancelar();
        generarBotonVolver();
    }

    private void generarBotonVolver() {
        botonVolver = generarBoton("Volver al Menú", 20, 200, 150, 30);
        this.add(botonVolver);
        botonVolver.addActionListener(this);
    }

    private void generarComboBoxCitas() {
        List<Cita> citas = healthful.devolverCitasPaciente(paciente.getRut());

        if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay citas agendadas.");
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }

        String[] citasArray = citas.stream().map(Cita::toString).toArray(String[]::new);

        comboBoxCitas = new JComboBox<>(citasArray);
        comboBoxCitas.setBounds(20, 20, 400, 30);
        this.add(comboBoxCitas);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar Cita", 20, 80, 150, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private boolean cancelarCita() {
        String citaSeleccionada = (String) comboBoxCitas.getSelectedItem();
        String[] descomposicionCita = citaSeleccionada.split(",");
        String rutPaciente = descomposicionCita[2];
        String rutMedico = descomposicionCita[3];
        Cita c = new Cita(LocalDate.parse(descomposicionCita[0]), LocalTime.parse(descomposicionCita[1]), rutPaciente, rutMedico);
        return healthful.removeCita(rutPaciente, rutMedico, c);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCancelar) {
            if (cancelarCita()) {
                JOptionPane.showMessageDialog(this, "Cita cancelada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agendar la cita");
            }
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }

        if (e.getSource() == botonVolver) {
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }
    }
}
