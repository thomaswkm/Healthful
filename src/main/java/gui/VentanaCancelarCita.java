package gui;

import model.Cita;
import model.Healthful;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class VentanaCancelarCita extends Ventana {
    private Healthful healthful;
    private Usuario usuario;
    private JButton botonCancelar;
    private JComboBox<String> comboBoxCitas;

    protected VentanaCancelarCita(Healthful healthful, Usuario usuario) {
        super("Cancelar Cita", 450, 550);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarComboBoxCitas();
        generarBotonCancelar();
    }

    private void generarComboBoxCitas() {
        ArrayList<Cita> citas = new ArrayList<>();
        try {
            citas = healthful.devolverCitasPaciente(usuario.getRut());
        }catch (Exception e){}

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
        Cita c = new Cita(LocalDate.parse(descomposicionCita[0]), LocalTime.parse(descomposicionCita[1]),rutPaciente,rutMedico);
        return healthful.removeCita(rutPaciente,rutMedico,c);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCancelar) {
            if(cancelarCita()){
                JOptionPane.showMessageDialog(this,"Cita cancelada correctamente.");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"No se pudo agendar la cita");
                this.dispose();
            }
        }
    }
}
