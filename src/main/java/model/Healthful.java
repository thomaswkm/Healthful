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
        if (!pacientes.contains(paciente)) {
            pacientes.add(paciente);
        } else {
            System.out.println("El paciente ya está registrado");
        }
    }

    public void addMedico(Medico medico) {
        if (!medicos.contains(medico)) {
            medicos.add(medico);
        } else {
            System.out.println("El medico ya está registrado");
        }
    }

    public void addCita(Cita cita) {
        citas.add(cita);
        GestorDeArchivos.guardarRegistro(cita.toCSV(), "src/main/resources/data/citas.txt");
    }

    public boolean addUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return true;
        } else {
            System.out.println("El usuario ya está registrado");
            return false;
        }
    }

    public boolean removeUsuario(String rut) {
        Usuario usuario = buscarUsuario(rut);
        if (usuarios.contains(usuario)) {
            usuarios.remove(usuario);
            return true;
        } else {
            System.out.println("El usuario no está registrado");
            return false;
        }
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

    public List<Paciente> devolverPacientesAsociadosAMedico(String rut) {
        return citas.stream()
                .filter(cita -> cita.getRutMedico().equals(rut))
                .map(cita -> obtenerPaciente(cita.getRutPaciente()))
                .collect(toList());
    }
}
