import java.util.ArrayList;

public class Personal {

    private String rut;
    private String contraseña;
    private String nombre;
    private String especialidad;
    private ArrayList<String> horas = new ArrayList<String>();

    public Personal(String rut, String contraseña, String nombre, String especialidad, ArrayList<String> horas) {
        this.rut = rut;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horas = horas;
    }

    public Personal() {
    }

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

    public void mostrarFichaPaciente(Paciente p){
        GestorArchivo ga = new GestorArchivo();
        System.out.println(ga.devolverFicha("pacientes.txt",p.getRut()));
    }

    @Override
    public String toString() {
        return rut+","+nombre+","+especialidad+","+horas;
    }
}
