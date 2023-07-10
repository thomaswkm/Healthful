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
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class VentanaMenuMedico extends Ventana {
    private final Healthful healthful;
    private final Medico medico;
    private JButton botonVerPacientes;
    private JButton botonVerCitas;
    private JButton botonCerrarSesion;

    public VentanaMenuMedico(Healthful healthful, Medico medico) {
        super("Menú Medico", 500, 520);
        this.healthful = healthful;
        this.medico = medico;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonVerPacientes();
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
        botonVerCitas = super.generarBoton(textoBoton, getCentro(200), 100, 200, 40);
        this.add(botonVerCitas);
        botonVerCitas.addActionListener(this);
    }

    private void generarBotonVerPacientes() {
        String textoBoton = "Ver Pacientes";
        botonVerPacientes = super.generarBoton(textoBoton, getCentro(200), 50, 200, 40);
        this.add(botonVerPacientes);
        botonVerPacientes.addActionListener(this);
    }

    private void generarMensajeMenu() {
        super.generarJLabelEncabezado("Menú Médico", 20, 30, 500, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonVerPacientes) {
            String[][] datos = mapPacientes();
            String[] columnas = new String[]{"Nombre", "Sexo", "Edad", "Estado Civil", "Visitas"};
            new VentanaTabla(datos, columnas, "Pacientes");
        }
        if (event.getSource() == botonVerCitas) {
            String[][] datos = mapCitas();
            String[] columnas = new String[]{"Fecha", "Hora", "Paciente"};
            new VentanaTabla(datos, columnas, "Citas");
        }
        if (event.getSource() == botonCerrarSesion) {
            new VentanaMenuBienvenida(healthful);
            this.dispose();
        }
    }

    private String[][] mapCitas() {
        List<Cita> citas = healthful.devolverCitasMedico(medico.getRut());
        return citas.stream().map(this::mapCita).toArray(String[][]::new);
    }

    private String[] mapCita(Cita cita) {
        String rutPaciente = cita.getRutPaciente();
        Paciente paciente = healthful.obtenerPaciente(rutPaciente);

        return new String[]{
                cita.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                cita.getHora().toString(),
                paciente.getNombreCompleto()
        };
    }

    private String[][] mapPacientes() {
        List<Cita> citas = healthful.devolverCitasMedico(medico.getRut());

        Map<String, Long> conteoRutPaciente = citas.stream()
                .collect(groupingBy(Cita::getRutPaciente, counting()));

        return conteoRutPaciente.entrySet().stream()
                .map(entry -> mapPaciente(entry.getKey(), entry.getValue()))
                .toArray(String[][]::new);
    }

    private String[] mapPaciente(String rutPaciente, Long visitas) {
        Paciente paciente = healthful.obtenerPaciente(rutPaciente);
        return new String[]{paciente.getNombreCompleto(),
                paciente.getSexo().toString(),
                String.valueOf(paciente.getEdad()),
                paciente.getEstadoCivil().toString(),
                visitas.toString()
        };
    }
}
