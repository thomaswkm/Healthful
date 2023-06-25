package model;

import java.time.LocalDateTime;

public class Cita {
    private final LocalDateTime fechaHora;
    private String rutPaciente;
    private String rutMedico;

    public Cita() {
        fechaHora = LocalDateTime.now();
    }

    public Cita(String rutPaciente, String rutMedico, int dia, int mes, int year, int hora, int minuto) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraPropuesta = LocalDateTime.of(year, mes, dia, hora, minuto);

        if (fechaHoraPropuesta.isBefore(fechaHoraActual)) {
            throw new IllegalArgumentException("No puedes crear una cita en una fecha pasada.");
        }
        fechaHora = fechaHoraPropuesta;
        this.rutPaciente = rutPaciente;
        this.rutMedico = rutMedico;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public String getRutMedico() {
        return rutMedico;
    }
}