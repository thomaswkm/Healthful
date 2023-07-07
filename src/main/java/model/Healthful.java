package model;

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

    public List<Medico> getMedicos() {
        return medicos;
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

}
