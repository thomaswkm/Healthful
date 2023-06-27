package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Healthful {
    private final List<Usuario> usuarios;
    private final List<Paciente> pacientes;
    private final List<Medico> medicos;
    private final List<Cita> citas;

    public Healthful(List<Usuario> usuarios, List<Paciente> pacientes, List<Medico> medicos, List<Cita> citas) {
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.citas = citas;
    }

    public Usuario login(String rut, String password) {
        Usuario usuario = buscarUsuario(rut);
        if (!usuario.verificacion(password)) throw new RuntimeException("Usuario y/o Contraseña incorrectos");

        return usuario;
    }

    private Usuario buscarUsuario(String rut) {
        return usuarios.stream()
                .filter(usuario -> usuario.rut.equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario y/o Contraseña incorrectos"));
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void addMedico(Medico medico) {
        medicos.add(medico);
    }

    public void addCita(Cita cita) {
        citas.add(cita);
    }

    public void addUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void mostrarMedicos() {
        System.out.println(medicos);
    }

    public void agregarCita(Medico medico, Paciente paciente, int dia, int mes, int year, int hora, int minutos) {
        if (medico == null) {
            System.out.println("No se encontró al médico con el rut ingresado.");
            return;
        }

        Cita cita = new Cita(LocalDate.now(), LocalTime.now(), paciente.getRut(), medico.getRut());

        if (citas.contains(cita)) {
            System.out.println("La cita ya fue registrada.");
        }

        citas.add(cita);
        paciente.addCita(cita);
        medico.addCita(cita);
    }

    public Medico obtenerMedico(String rut) {
        return medicos.stream()
                .filter(m -> m.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario No Encontrado"));
    }

    public Paciente obtenerPaciente(String rut) {
        return pacientes.stream()
                .filter(p -> p.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario No Encontrado"));
    }

    public void mostrarCitasAgendadas(Paciente paciente) {
        System.out.println(paciente.getCitas());
    }

    public void removeCita(Paciente paciente, Medico medico, Cita cita) {
        citas.remove(cita);
        paciente.removeCita(cita);
        medico.removeCita(cita);
    }

    public void removeCita(Medico medico, Cita cita) {
        citas.remove(cita);
        Paciente paciente = obtenerPaciente(cita.getRutPaciente());
        paciente.removeCita(cita);
        medico.removeCita(cita);
    }

    public void mostrarFichaPaciente() {
        System.out.println("Ingresa el rut del paciente");
    }

    public void mostrarHorasDisponibles() {
    }
}
