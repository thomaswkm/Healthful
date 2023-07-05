package gui;

import model.Cita;
import model.Healthful;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaVerCitas extends Ventana {
    private Healthful healthful;
    private Usuario usuario;
    private JButton botonVolver;
    private JComboBox<String> comboBoxCitas;

    protected VentanaVerCitas(Healthful healthful, Usuario usuario) {
        super("Ver Citas", 450, 550);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarComboBoxCitas();
        generarBotonVolver();
    }

    private void generarComboBoxCitas() {
        ArrayList<Cita> citas = new ArrayList<>();

        if(usuario.getRol().name().equals("PACIENTE")) {
            try {
                citas = healthful.devolverCitasPaciente(usuario.getRut());
            } catch (Exception e) {
            }
        }if(usuario.getRol().name().equals("MEDICO")){
            try {
                citas = healthful.devolverCitasMedico(usuario.getRut());
            } catch (Exception e) {
            }
        }


        if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay citas agendadas.");
            this.dispose();
        } else {
            String[] citasArray = new String[citas.size()];
            for (int i = 0; i < citas.size(); i++) {
                citasArray[i] = citas.get(i).toString();
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
