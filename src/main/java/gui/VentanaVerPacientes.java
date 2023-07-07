package gui;

import model.*;
import model.Healthful;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaVerPacientes extends Ventana {
    private Healthful healthful;
    private Usuario usuario;
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
        ArrayList<Paciente> pacientes = new ArrayList<>();

            try {
                pacientes = healthful.devolverPacientesAsociadosAMedico(usuario.getRut());
            } catch (Exception e) {
            }

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes pacientes con citas agendadas.");
            this.dispose();
        } else {
            String[] citasArray = new String[pacientes.size()];
            for (int i = 0; i < pacientes.size(); i++) {
                citasArray[i] = pacientes.get(i).toString();
            }

            comboBoxCitas = new JComboBox<>(citasArray);
            comboBoxCitas.setBounds(20, 20, 400, 30);
            this.add(comboBoxCitas);
        }
    }

    private void generarBotonVolver() {
        botonVolver = generarBoton("Volver", 20, 80, 150, 30);
        this.add(botonVolver);
        botonVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            this.dispose();
        }
    }
}
