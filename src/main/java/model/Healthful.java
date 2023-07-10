package model;

import data.GestorDeArchivos;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public Healthful() {
        this.usuarios = GestorDeArchivos.leerUsuarios("src/main/resources/data/usuarios.txt");
        this.pacientes = GestorDeArchivos.leerPacientes("src/main/resources/data/pacientes.txt");
        this.medicos = GestorDeArchivos.leerMedicos("src/main/resources/data/medicos.txt");
        this.citas = GestorDeArchivos.leerCitas("src/main/resources/data/citas.txt");
    }

    public Usuario login(String rut, String password) {
        Usuario usuario = buscarUsuario(rut);

        if (!usuario.verificacion(password)) throw new RuntimeException("Rut y/o Contraseña incorrectos");

        return usuario;
    }

    public Usuario buscarUsuario(String rut) {
        return usuarios.stream()
                .filter(usuario -> usuario.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rut y/o Contraseña incorrectos"));
    }

    public List<String> getEspecialidades() {
        return medicos.stream().map(Medico::getEspecialidad).distinct().collect(toList());
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
        GestorDeArchivos.guardarRegistro(paciente.toCSV(), "src/main/resources/data/pacientes.txt");
    }

    public void addMedico(Medico medico) {
        medicos.add(medico);
        GestorDeArchivos.guardarRegistro(medico.toCSV(), "src/main/resources/data/medicos.txt");
    }

    public void addCita(Cita cita) {
        citas.add(cita);
        GestorDeArchivos.guardarRegistro(cita.toCSV(), "src/main/resources/data/citas.txt");
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        GestorDeArchivos.guardarRegistro(usuario.toCSV(), "src/main/resources/data/usuarios.txt");
    }

    public void removeUsuario(String rut) {
        usuarios.removeIf(usuario -> usuario.getRut().equals(rut));
        GestorDeArchivos.borrarRegistro("src/main/resources/data/usuarios.txt", rut, 0);
    }

    public Medico obtenerMedico(String rut) {
        return medicos.stream()
                .filter(medico -> medico.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Médico " + rut + " No Encontrado"));
    }

    public Paciente obtenerPaciente(String rut) {
        return pacientes.stream()
                .filter(paciente -> paciente.getRut().equals(rut))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Paciente " + rut + " No Encontrado"));
    }

    public boolean removeCita(String rutPaciente, String rutMedico, Cita cita) {
        if (citas.contains(cita)) {
            citas.remove(cita);
            obtenerPaciente(rutPaciente).getCitas().remove(cita);
            obtenerMedico(rutMedico).getCitas().remove(cita);
            return true;
        } else {
            System.out.println("La cita no está registrada");
            return false;
        }
    }

    public List<Cita> devolverCitasPaciente(String rut) {
        return citas.stream()
                .filter(cita -> cita.getRutPaciente().equals(rut))
                .collect(toList());
    }

    public List<Cita> devolverCitasMedico(String rut) {
        return citas.stream()
                .filter(cita -> cita.getRutMedico().equals(rut))
                .collect(toList());
    }

    public void removePaciente(String rut) {
        pacientes.removeIf(paciente -> paciente.getRut().equals(rut));
        GestorDeArchivos.borrarRegistro("src/main/resources/data/pacientes.txt", rut, 0);
    }

    public void removeMedico(String rut) {
        medicos.removeIf(medico -> medico.getRut().equals(rut));
        GestorDeArchivos.borrarRegistro("src/main/resources/data/medicos.txt", rut, 0);
    }
}
