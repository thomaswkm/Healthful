package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private final LocalDate fecha;
    private final LocalTime hora;
    private final String rutPaciente;
    private final String rutMedico;

    public Cita(LocalDate fecha, LocalTime hora, String rutPaciente, String rutMedico) {
        this.fecha = fecha;
        this.hora = hora;
        this.rutPaciente = rutPaciente;
        this.rutMedico = rutMedico;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public String getRutMedico() {
        return rutMedico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
}