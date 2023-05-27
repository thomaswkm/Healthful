import java.time.LocalDateTime;

public class Cita {
    private LocalDateTime fechaHora;
    private Paciente p;
    private Medico m;

    public Cita() {
        fechaHora = LocalDateTime.now();
    }

    public Cita(Paciente p, Medico m, int dia, int mes, int year, int hora, int minuto) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraPropuesta = LocalDateTime.of(year, mes, dia, hora, minuto);

        if (fechaHoraPropuesta.isBefore(fechaHoraActual)) {
            throw new IllegalArgumentException("No puedes crear una cita en una fecha pasada.");
        }
        fechaHora = fechaHoraPropuesta;
        this.p = p;
        this.m = m;
    }

    public int getDia() {
        return fechaHora.getDayOfMonth();
    }

    public int getMes() {
        return fechaHora.getMonthValue();
    }

    public int getYear() {
        return fechaHora.getYear();
    }

    public int getHora() {
        return fechaHora.getHour();
    }

    public int getMinuto() {
        return fechaHora.getMinute();
    }
    @Override
    public String toString() {
        return "Fecha y hora: " + fechaHora + "Paciente: "+p+" Medico: "+m;
    }
}