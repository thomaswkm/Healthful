import java.util.ArrayList;

public class Medico {

    private String rut;

    private String nombre;
    private String especialidad;
    private ArrayList<Cita> citas = new ArrayList<Cita>();

    public Medico(String rut, String nombre, String especialidad, ArrayList<Cita> citas) {
        this.rut = rut;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }


    public Medico() {
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setHoras(ArrayList<String> horas) {
        this.citas = citas;
    }

    public void addCita(Cita c){
        citas.add(c);
    }

    public void removeCita(Cita c){
        citas.remove(c);
    }

    @Override
    public String toString() {
        return rut+","+nombre+","+especialidad;
    }


}
