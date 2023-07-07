package gui;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class VentanaAgendarCita extends Ventana {
    private Healthful healthful;
    private Usuario usuario;
    private JTextField campoRutMedico, campoFecha;
    private JSpinField campoHora, campoMinutos;
    private JButton botonGuardar;
    private JButton botonCancelar;
    private JCalendar calendar;

    protected VentanaAgendarCita(Healthful healthful, Usuario usuario) {
        super("Agendar Cita", 450, 550);
        this.healthful = healthful;
        this.usuario = usuario;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCampoRutMedico();
        generarCalendario();
        generarCampoHora();
        generarCampoMinutos();
        generarBotonGuardar();
        generarBotonCancelar();
    }

    private void generarCampoRutMedico() {
        String textoRut = "Rut Medico (Sin puntos ni guión):";
        super.generarJLabel(textoRut, 20, 20, 200, 20); // Aumenta el ancho de la etiqueta
        campoRutMedico = super.generarJTextField(230, 20, 200, 20); // Ajusta la posición y el ancho del campo
        this.add(campoRutMedico);
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
        botonSeleccionarFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarFecha();
            }
        });
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

    private void generarBotonGuardar() {
        botonGuardar = generarBoton("Guardar", 20, 400, 100, 30);
        this.add(botonGuardar);
        botonGuardar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        botonCancelar = generarBoton("Cancelar", 200, 400, 100, 30);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private boolean agregarCita() {
        if (campoRutMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Rut del Médico.");
            return false;
        }
        LocalDate fecha = LocalDate.parse(campoFecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime hora = LocalTime.of(campoHora.getValue(), campoMinutos.getValue());
        return healthful.agregarCita(new Cita(fecha, hora, usuario.getRut(), campoRutMedico.getText()));
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            if(agregarCita()){
                JOptionPane.showMessageDialog(this,"Cita agendada correctamente.");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"No se pudo agendar la cita");
                this.dispose();
            }
        }
        if (e.getSource() == botonCancelar) {
            this.dispose();
        }
    }
}
