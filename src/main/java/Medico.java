import java.util.ArrayList;

public class Medico {

    private String rut;

    private String nombre;
    private String especialidad;
    private ArrayList<String> horas = new ArrayList<String>();

    public Medico(String rut, String nombre, String especialidad, ArrayList<String> horas) {
        this.rut = rut;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horas = horas;
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

    public ArrayList<String> getHoras() {
        return horas;
    }

    public void setHoras(ArrayList<String> horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return rut+","+nombre+","+especialidad+","+horas;
    }


}
