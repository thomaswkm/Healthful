import java.time.LocalDateTime;

public class Cita {
    private LocalDateTime fechaHora;
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

    public String getRutPaciente(){
        return rutPaciente;
    }
    public String getRutMedico(){
        return rutMedico;
    }


    @Override
    public String toString() {
        return rutPaciente+","+rutMedico+","+fechaHora.getDayOfMonth()+","+fechaHora.getMonthValue()+","+fechaHora.getYear()+","+fechaHora.getHour()+","+fechaHora.getMinute();
    }
}