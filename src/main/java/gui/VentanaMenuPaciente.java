package gui;

import model.Cita;
import model.Healthful;
import model.Medico;
import model.Paciente;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaMenuPaciente extends Ventana {
    private final Healthful healthful;
    private final Paciente paciente;
    private JButton botonAgendar;
    private JButton botonCancelar;
    private JButton botonVerCitas;
    private JButton botonCerrarSesion;

    public VentanaMenuPaciente(Healthful healthful, Paciente paciente) {
        super("Menú Paciente", 500, 520);
        this.healthful = healthful;
        this.paciente = paciente;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonAgendar();
        generarBotonCancelar();
        generarBotonVerCitas();
        generarBotonCerrarSesion();
    }

    private void generarBotonCerrarSesion() {
        String textoBoton = "Cerrar Sesión";
        botonCerrarSesion = super.generarBoton(textoBoton, getCentro(150), 250, 150, 20);
        this.add(botonCerrarSesion);
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.addActionListener(this);
    }

    private int getCentro(int largo) {
        return this.getWidth() / 2 - largo;
    }

    private void generarBotonVerCitas() {
        String textoBoton = "Ver Citas";
        botonVerCitas = super.generarBoton(textoBoton, getCentro(200), 150, 200, 40);
        this.add(botonVerCitas);
        botonVerCitas.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBoton = "Cancelar Cita";
        botonCancelar = super.generarBoton(textoBoton, getCentro(200), 100, 200, 40);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarBotonAgendar() {
        String textoBoton = "Agendar Cita";
        botonAgendar = super.generarBoton(textoBoton, getCentro(200), 50, 200, 40);
        this.add(botonAgendar);
        botonAgendar.addActionListener(this);
    }

    private void generarMensajeMenu() {
        super.generarJLabelEncabezado("Menú Paciente", 20, 30, 500, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonAgendar) {
            new VentanaSeleccionMedico(healthful, paciente);
            this.dispose();
        }
        if (event.getSource() == botonCancelar) {
            new VentanaCancelarCita(healthful, paciente);
            this.dispose();
        }
        if (event.getSource() == botonVerCitas) {
            String[][] datos = mapCitas();
            String[] columnas = new String[]{"Fecha", "Hora", "Médico"};
            new VentanaTabla(datos, columnas, "Citas");
        }
        if (event.getSource() == botonCerrarSesion) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }

    private String[][] mapCitas() {
        List<Cita> citas = healthful.devolverCitasPaciente(paciente.getRut());
        return citas.stream().map(this::mapCita).toArray(String[][]::new);
    }

    private String[] mapCita(Cita cita) {
        String rutMedico = cita.getRutMedico();
        Medico medico = healthful.obtenerMedico(rutMedico);

        return new String[]{
                cita.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                cita.getHora().toString(),
                medico.getNombreCompleto()
        };
    }
}
