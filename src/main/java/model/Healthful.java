package model;


import java.util.ArrayList;
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
        if (!this.pacientes.contains(paciente)) {
            pacientes.add(paciente);
            return true;
        } else {
            System.out.println("El paciente ya está registrado");
            return false;
        }
    }

    public boolean addMedico(Medico medico) {
        if (!this.medicos.contains(medico)) {
            medicos.add(medico);
            return true;
        } else {
            System.out.println("El medico ya está registrado");
            return false;
        }
    }

    public boolean addCita(Cita cita) {
        if (!this.citas.contains(cita)) {
            citas.add(cita);
            return true;
        } else {
            System.out.println("La cita ya está registrada");
            return false;
        }
    }

    public boolean addUsuario(Usuario usuario) {
        if (!this.usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return true;
        } else {
            System.out.println("El usuario ya está registrado");
            return false;
        }
    }

    public boolean removeUsuario(String rut) {
        Usuario usuario = buscarUsuario(rut);
        if (this.usuarios.contains(usuario)) {
            usuarios.remove(usuario);
            return true;
        } else {
            System.out.println("El usuario no está registrado");
            return false;
        }
    }

    public void mostrarMedicos() {
        System.out.println(medicos);
    }

    public boolean agregarCita(Cita cita) {
        if (addCita(cita)) {
            obtenerPaciente(cita.getRutPaciente()).getCitas().add(cita);
            obtenerMedico(cita.getRutMedico()).getCitas().add(cita);
            return true;
        } else {
            return false;
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


    public ArrayList<Cita> devolverCitasPaciente(String rut) {
        for (Paciente paciente: pacientes) {
            if(paciente.getRut().equals(rut)){
                return (ArrayList<Cita>) paciente.getCitas();
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<Cita> devolverCitasMedico(String rut) {
        ArrayList<Cita> citas = new ArrayList<>();

        for (Cita cita : this.citas) {
            if (cita.getRutMedico().equals(rut)) {
                citas.add(cita);
            }
        }
        return citas;
    }

    public ArrayList<Paciente> devolverPacientesAsociadosAMedico(String rut) {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        for (Cita cita : this.citas) {
            if (cita.getRutMedico().equals(rut)) {
                pacientes.add(obtenerPaciente(cita.getRutPaciente()));
            }
        }
        return pacientes;
    }
}
