import java.util.ArrayList;

public class Paciente {
    private String rut;
    private String nombre;
    private ArrayList<Cita> citas = new ArrayList<Cita>();

    public Paciente(String rut, String nombre, ArrayList<Cita> citas) {
        this.rut = rut;
        this.nombre = nombre;
        this.citas = citas;
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

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setHoras(ArrayList<Cita> horas) {
        this.citas = horas;
    }

    public void addCita(Cita c){
        citas.add(c);
    }

    public void removeCita(Cita c){
        citas.remove(c);
    }

    @Override
    public String toString() {
        return rut + "," + nombre;
    }

}
