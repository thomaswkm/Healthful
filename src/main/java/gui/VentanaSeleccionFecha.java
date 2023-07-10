package gui;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;
import model.Cita;
import model.Healthful;
import model.Medico;
import model.Paciente;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class VentanaSeleccionFecha extends Ventana {
    private final Healthful healthful;
    private final Paciente paciente;
    private final Medico medico;
    private JTextField campoFecha;
    private JSpinField campoHora;
    private JSpinField campoMinutos;
    private JButton botonContinuar;
    private JButton botonCancelar;
    private JCalendar calendar;

    public VentanaSeleccionFecha(Healthful healthful, Paciente paciente, Medico medico) {
        super("Agendar Cita", 450, 550);
        this.healthful = healthful;
        this.paciente = paciente;
        this.medico = medico;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCalendario();
        generarCampoHora();
        generarCampoMinutos();
        generarBotonContinuar();
        generarBotonCancelar();
        generarLabelMedico();
    }

    private void generarLabelMedico() {
        super.generarJLabel("Médico:", 20, 20, 200, 20);
        super.generarJLabel(medico.getNombreCompleto(), 230, 20, 200, 20);
    }

    private void generarCalendario() {
        JLabel etiquetaFecha = new JLabel("Fecha:");
        etiquetaFecha.setBounds(20, 60, 150, 20);
        this.add(etiquetaFecha);

        campoFecha = new JTextField(10);
        campoFecha.setEditable(false);
        campoFecha.setBounds(200, 60, 200, 20);
        this.add(campoFecha);

        calendar = new JCalendar();
        calendar.setBounds(20, 100, 400, 200);
        this.add(calendar);

        JButton botonSeleccionarFecha = new JButton("Seleccionar");
        botonSeleccionarFecha.setBounds(20, 320, 150, 30);
        botonSeleccionarFecha.addActionListener(e -> seleccionarFecha());
        this.add(botonSeleccionarFecha);
    }

    private void seleccionarFecha() {
        Date fechaSeleccionada = calendar.getDate();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaSeleccionada);
        campoFecha.setText(fechaFormateada);
    }

    private void generarCampoHora() {
        JLabel etiquetaHora = new JLabel("Hora:");
        etiquetaHora.setBounds(20, 360, 150, 20);
        this.add(etiquetaHora);

        campoHora = new JSpinField();
        campoHora.setMinimum(0);
        campoHora.setMaximum(23);
        campoHora.setValue(8);
        campoHora.setBounds(70, 360, 60, 20);
        this.add(campoHora);
    }

    private void generarCampoMinutos() {
        JLabel etiquetaMinutos = new JLabel("Minutos:");
        etiquetaMinutos.setBounds(300, 360, 150, 20);
        this.add(etiquetaMinutos);

        campoMinutos = new JSpinField();
        campoMinutos.setMinimum(0);
        campoMinutos.setMaximum(59);
        campoMinutos.setValue(0);
        campoMinutos.setBounds(350, 360, 60, 20);
        this.add(campoMinutos);
    }

    private void generarBotonContinuar() {
        botonContinuar = generarBoton("Continuar", 20, 400, 100, 30);
        this.add(botonContinuar);
        botonContinuar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar", 200, 400, 100, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonContinuar) {
            if (campoFecha.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha");
                return;
            }
            LocalDate fecha = LocalDate.parse(campoFecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime hora = LocalTime.of(campoHora.getValue(), campoMinutos.getValue());

            if (parametrosNoValidos(fecha, hora)) return;

            new VentanaConfirmacionCita(healthful, paciente, medico, fecha, hora);
            this.dispose();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuPaciente(healthful, paciente);
            this.dispose();
        }
    }

    private boolean parametrosNoValidos(LocalDate fecha, LocalTime hora) {
        try {
            validarFechaYHora(fecha, hora);
            validarCitaDisponible(fecha, hora);
            return false;
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return true;
        }
    }

    private void validarCitaDisponible(LocalDate fecha, LocalTime hora) {
        List<Cita> horasMedico = healthful.devolverCitasMedico(medico.getRut());

        boolean horaNoDisponible = horasMedico.stream()
                .anyMatch(cita -> cita.getFecha().isEqual(fecha) && cita.getHora().equals(hora));

        if (horaNoDisponible) {
            throw new RuntimeException("La Fecha: " + fecha + " y Hora: " + hora + " Ya está reservada");
        }
    }

    private void validarFechaYHora(LocalDate fecha, LocalTime hora) {
        validarFecha(fecha);
        validarHora(hora);
    }

    private void validarHora(LocalTime hora) {
        if (hora.isBefore(LocalTime.of(8, 30)) || hora.isAfter(LocalTime.of(18, 0))) {
            throw new RuntimeException("El horario es de 8:30 a 18:00 hrs.");
        }
    }

    private void validarFecha(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        if (fecha.isBefore(hoy) || fecha.isEqual(hoy)) {
            String format = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy"));
            throw new RuntimeException("Debe seleccionar una fecha posterior a hoy: " + format);
        }

        DayOfWeek dia = fecha.getDayOfWeek();
        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Debe ser un día de Lunes a Viernes");
        }
    }
}
