package gui;

import model.Cita;
import model.Healthful;
import model.Rol;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaVerCitas extends Ventana {
    private final Healthful healthful;
    private final Usuario usuario;
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
        List<Cita> citas = getCitas();

        if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay citas agendadas.");
            new VentanaMenuPaciente(healthful, healthful.obtenerPaciente(usuario.getRut()));
            this.dispose();
        }

        String[] citasArray = citas.stream().map(Cita::toString).toArray(String[]::new);

        comboBoxCitas = new JComboBox<>(citasArray);
        comboBoxCitas.setBounds(20, 20, 400, 30);
        this.add(comboBoxCitas);
    }

    private List<Cita> getCitas() {
        Rol rol = usuario.getRol();

        if (rol == Rol.PACIENTE) {
            return healthful.devolverCitasPaciente(usuario.getRut());
        }
        if (rol == Rol.MEDICO) {
            return healthful.devolverCitasMedico(usuario.getRut());
        }
        return new ArrayList<>();
    }

    private void generarBotonVolver() {
        botonVolver = generarBoton("Volver", 20, 80, 150, 30);
        this.add(botonVolver);
        botonVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            new VentanaMenuPaciente(healthful, healthful.obtenerPaciente(usuario.getRut()));
            this.dispose();
        }
    }
}
