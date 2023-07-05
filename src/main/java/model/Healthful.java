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

        if (!usuario.verificacion(password)) throw new RuntimeException("Rut y/o Contraseña incorrectos");

        return usuario;
    }

    private Usuario buscarUsuario(String rut) {
        return usuarios.stream()
                .filter(usuario -> usuario.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rut y/o Contraseña incorrectos"));
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

    public boolean addPaciente(Paciente paciente) {
        if(!this.pacientes.contains(paciente)) {
            pacientes.add(paciente);
            return true;
        }else{
            System.out.println("El paciente ya está registrado");
            return false;
        }
    }

    public boolean addMedico(Medico medico) {
        if(!this.medicos.contains(medico)) {
            medicos.add(medico);
            return true;
        }else{
            System.out.println("El medico ya está registrado");
            return false;
        }
    }

    public boolean addCita(Cita cita) {
        if(!this.citas.contains(cita)) {
            citas.add(cita);
            return true;
        }else{
            System.out.println("La cita ya está registrada");
            return false;
        }
    }

    public boolean addUsuario(Usuario usuario) {
        if(!this.usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return true;
        }else{
            System.out.println("El usuario ya está registrado");
            return false;
        }
    }

    public boolean removeUsuario(String rut){
        Usuario usuario = buscarUsuario(rut);
        if(this.usuarios.contains(usuario)){
            usuarios.remove(usuario);
            return true;
        }else{
            System.out.println("El usuario no está registrado");
            return false;
        }
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

        if(addCita(cita)) {
            paciente.getCitas().add(cita);
            medico.getCitas().add(cita);
        }
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
        if(citas.contains(cita)) {
            citas.remove(cita);
            paciente.getCitas().remove(cita);
            medico.getCitas().remove(cita);
        }else{
            System.out.println("La cita no está registrada");
        }
    }
}
