package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Paciente extends Usuario {
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaNacimiento;
    private final Sexo sexo;
    private final EstadoCivil estadoCivil;
    private final List<Cita> citas;

    public Paciente(String rut,
                    String nombre,
                    String apellido,
                    LocalDate fechaNacimiento,
                    Sexo sexo,
                    EstadoCivil estadoCivil,
                    List<Cita> citas) {
        super(rut, Rol.PACIENTE);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.citas = citas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return rut + "," + nombre + "," + apellido + "," + fechaNacimiento + "," + sexo + "," + estadoCivil;
    }

    @Override
    public String[] toCSV() {
        return new String[]{super.rut,
                nombre,
                apellido,
                fechaNacimiento.toString(),
                sexo.toString(),
                estadoCivil.toString()};
    }
}
