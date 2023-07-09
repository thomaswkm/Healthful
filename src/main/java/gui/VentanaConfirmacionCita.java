package gui;

import model.Cita;
import model.Healthful;
import model.Medico;
import model.Paciente;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VentanaConfirmacionCita extends Ventana {
    private final Healthful healthful;
    private final Medico medico;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final Paciente paciente;
    private JButton botonAgendar;
    private JButton botonCancelar;

    public VentanaConfirmacionCita(Healthful healthful, Paciente paciente, Medico medico, LocalDate fecha, LocalTime hora) {
        super("Agendar Cita", 450, 550);
        this.healthful = healthful;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonAgendar();
        generarBotonCancelar();
        generarLabelInfo();
    }

    private void generarLabelInfo() {
        String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy"));
        super.generarJLabel("Fecha:", 20, 50, 200, 20);
        super.generarJLabel(fechaFormateada, 230, 50, 200, 20);

        super.generarJLabel("Hora:", 20, 100, 200, 20);
        super.generarJLabel(hora.toString(), 230, 100, 200, 20);

        super.generarJLabel("Paciente:", 20, 150, 200, 20);
        super.generarJLabel(paciente.getNombreCompleto(), 230, 150, 200, 20);

        super.generarJLabel("Médico:", 20, 200, 200, 20);
        super.generarJLabel(medico.getNombreCompleto(), 230, 200, 200, 20);

        super.generarJLabel("Especialidad:", 20, 250, 200, 20);
        super.generarJLabel(medico.getEspecialidad(), 230, 250, 200, 20);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 75, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarBotonAgendar() {
        String textoBotonCancelar = "Agendar";
        botonAgendar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(botonAgendar);
        botonAgendar.addActionListener(this);
    }

    private void generarEncabezado() {
        String textoCabecera = "Confirmación Cita";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonAgendar) {
            healthful.addCita(new Cita(fecha, hora, paciente.getRut(), medico.getRut()));
            JOptionPane.showMessageDialog(this, "Cita agendada!");
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }
        if (event.getSource() == botonCancelar) {
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }
    }
}
