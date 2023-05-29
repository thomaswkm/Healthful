import java.util.ArrayList;
import java.util.Scanner;

public class Healthful {
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Cita> citas;

    public Healthful() {
    }

    public Healthful(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Cita> citas) {
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

    public void addPaciente(Paciente p) {
        pacientes.add(p);
    }

    public void addMedico(Medico m) {
        medicos.add(m);
    }

    public void addCita(Cita c) {
        citas.add(c);
    }

    public void removeCita(Cita c) {
        if (citas.indexOf(c) == 1) {
            System.out.println("La cita elegida no está registrada.");
            return;
        }
        citas.remove(c);

    }

    public void mostrarMedicos() {
        System.out.println(medicos);
    }

    public void solicitarCita(Paciente p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el día: ");
        int dia = sc.nextInt();
        System.out.println("Ingrese el número del mes: ");
        int mes = sc.nextInt();
        System.out.println("Ingrese el año: ");
        int year = sc.nextInt();
        System.out.println("Ingrese la hora del día: ");
        int hora = sc.nextInt();
        System.out.println("Ingrese los minutos: ");
        int minutos = sc.nextInt();
        System.out.println("Ingrese el rut del médico: ");
        String rut = sc.next();
        agregarCita(obtenerMedico(rut), p, dia, mes, year, hora, minutos);
    }

    public void agregarCita(Medico medico, Paciente p, int dia, int mes, int year, int hora, int minutos) {
        if (medico != null) {
            Cita c = new Cita(p, medico, dia, mes, year, hora, minutos);
            if (!citas.contains(c)) {
                citas.add(c);
            } else {
                System.out.println("La cita ya fue registrada.");
            }
        } else {
            System.out.println("No se encontró al médico con el rut ingresado.");
        }
    }


    public Medico obtenerMedico(String rut) {
        for (Medico m : medicos) {
            if (m.getRut().equals(rut)) {
                return m;
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
        removeCita(p.getCitas().get(new Scanner(System.in).nextInt()));
    }

    public void mostrarFichaPacientes() {
        System.out.println("Ingresa el rut del paciente");
        System.out.println(new GestorArchivo().devolverFicha("pacientes.txt", new Scanner(System.in).nextLine()));

    }

    public void mostrarHorasDisponibles() {
    }

}
