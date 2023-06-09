package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public String toString() {
        return fecha + "," + hora + "," + rutPaciente + "," + rutMedico;
    }

    public String[] toCSV() {
        return new String[]{fecha.toString(), hora.format(DateTimeFormatter.ofPattern("hh:mm")), rutPaciente, rutMedico};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(fecha, cita.fecha) &&
                Objects.equals(hora, cita.hora) &&
                Objects.equals(rutPaciente, cita.rutPaciente) &&
                Objects.equals(rutMedico, cita.rutMedico);
    }
}