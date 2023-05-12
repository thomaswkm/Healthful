import java.util.ArrayList;

public class Paciente {
    private String rut;
    private String contraseña;
    private String nombre;
    private ArrayList<String> horas = new ArrayList<String>();

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public Paciente(String rut, String contraseña, String nombre, ArrayList<String> horas) {
        this.rut = rut;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.horas = horas;
    }

    public Paciente() {
    }

    @Override
    public String toString() {
        return rut+","+nombre+","+horas;
    }

}
