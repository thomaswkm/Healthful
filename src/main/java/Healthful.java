import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Healthful {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Cita> citas;

    public Healthful() {
    }

    public Healthful(ArrayList<Usuario> usuarios, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Cita> citas) {
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.citas = citas;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }

    public void addPaciente(Paciente p){
        pacientes.add(p);
    }

    public void addMedico(Medico m){
        medicos.add(m);
    }

    public void addCita(Cita c){
        citas.add(c);
    }
    public void addUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void mostrarMedicos() {
        System.out.println(medicos);
    }

    public void solicitarCita(Paciente p) {
        int dia = 0; int mes = 0; int year = 0; int hora = 0; int minuto = 0; String rut = "";
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ingrese el día: ");
            dia = sc.nextInt();
            System.out.println("Ingrese el número del mes: ");
            mes = sc.nextInt();
            System.out.println("Ingrese el año: ");
            year = sc.nextInt();
            System.out.println("Ingrese la hora del día: ");
            hora = sc.nextInt();
            System.out.println("Ingrese los minutos: ");
            minuto = sc.nextInt();
            System.out.println("Ingrese el rut del médico: ");
            rut = sc.next();
        }catch (Exception e){
            System.out.println("Error al ingresar los datos");
        }
        agregarCita(obtenerMedico(rut),p,dia,mes,year,hora,minuto);
    }

    public void agregarCita(Medico medico, Paciente p, int dia, int mes ,int year, int hora, int minutos){
        if (medico != null) {
            Cita c = new Cita(p.getRut(), medico.getRut(), dia, mes, year, hora, minutos);
            if (!citas.contains(c)) {
                citas.add(c);
                p.addCita(c);
                medico.addCita(c);
            } else {
                System.out.println("La cita ya fue registrada.");
            }
        } else {
            System.out.println("No se encontró al médico con el rut ingresado.");
        }
    }


    public Medico obtenerMedico(String rut) {
        for (Medico m: medicos) {
            if (m.getRut().equals(rut)){
                return m;
            }
        }
        return null;
    }

    public Paciente obtenerPaciente(String rut) {
        for (Paciente p: pacientes){
            if(p.getRut().equals(rut)){
                return p;
            }
        }
        return null;
    }

    public void mostrarCitasAgendadas(Paciente p) {
        System.out.println(p.getCitas());
    }

    public void cancelarCita(Paciente p) {
        System.out.println(p.getCitas());
        System.out.println("Elige el numero de cita a borrar");
        int ans = new Scanner(System.in).nextInt();
        Cita c = p.getCitas().get(ans);
        removeCita(p,obtenerMedico(p.getCitas().get(ans).getRutMedico()),c);
    }

    public void removeCita(Paciente p, Medico m, Cita c){
        this.citas.remove(c);
        p.removeCita(c);
        m.removeCita(c);

    }

    public void removeCita(Medico m, Cita c){
        this.citas.remove(c);
        Paciente p = obtenerPaciente(c.getRutPaciente());
        p.removeCita(c);
        m.removeCita(c);

    }

    public void mostrarFichaPaciente() {
        System.out.println("Ingresa el rut del paciente");
        System.out.println(new GestorArchivo().devolverFicha("pacientes.txt",new Scanner(System.in).nextLine()));

    }

    public void mostrarHorasDisponibles() {
    }


}
