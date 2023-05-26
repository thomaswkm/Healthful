import java.util.ArrayList;

public class Paciente {
    private String rut;
    private String nombre;
    private ArrayList<String> horas = new ArrayList<String>();

    public Paciente(String rut, String nombre, ArrayList<String> horas) {
        this.rut = rut;
        this.nombre = nombre;
        this.horas = horas;
    }

    public Paciente() {
    }


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getHoras() {
        return horas;
    }

    public void setHoras(ArrayList<String> horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return rut+","+nombre+","+horas;
    }

    public void mostrarHorasDisponibles() {
    }

    public void mostrarMedicos() {
    }

    public void solicitarHora() {
    }

    public void cancelarHora() {
    }

    public void mostrarHorasAgendadas() {
    }
}
