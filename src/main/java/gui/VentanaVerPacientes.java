package gui;

import model.Healthful;
import model.Paciente;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaVerPacientes extends Ventana {
    private final Healthful healthful;
    private final Usuario usuario;
    private JButton botonVolver;
    private JComboBox<String> comboBoxCitas;

    protected VentanaVerPacientes(Healthful healthful, Usuario usuario) {
        super("Ver Pacientes", 450, 550);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarComboBoxPacientes();
        generarBotonVolver();
    }

    private void generarComboBoxPacientes() {
        List<Paciente> pacientes = healthful.devolverPacientesAsociadosAMedico(usuario.getRut());

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes pacientes con citas agendadas.");
            new VentanaMenuMedico(healthful, healthful.obtenerMedico(usuario.getRut()));
            this.dispose();
        }
        String[] citasArray = pacientes.stream().map(Paciente::toString).toArray(String[]::new);

        comboBoxCitas = new JComboBox<>(citasArray);
        comboBoxCitas.setBounds(20, 20, 400, 30);
        this.add(comboBoxCitas);
    }

    private void generarBotonVolver() {
        botonVolver = generarBoton("Volver", 20, 80, 150, 30);
        this.add(botonVolver);
        botonVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            new VentanaMenuMedico(healthful, healthful.obtenerMedico(usuario.getRut()));
            this.dispose();
        }
    }
}
