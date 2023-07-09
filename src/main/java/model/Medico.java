package model;

import java.time.LocalDate;
import java.util.List;

public class Medico extends Usuario {
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaNacimiento;
    private final Sexo sexo;
    private final EstadoCivil estadoCivil;
    private final String especialidad;
    private final List<Cita> citas;

    public Medico(String rut,
                  String nombre,
                  String apellido,
                  LocalDate fechaNacimiento,
                  Sexo sexo,
                  EstadoCivil estadoCivil,
                  String especialidad,
                  List<Cita> citas) {
        super(rut, Rol.MEDICO);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String[] toCSV() {
        return new String[]{
                rut,
                nombre,
                apellido,
                fechaNacimiento.toString(),
                sexo.toString(),
                estadoCivil.toString(),
                especialidad
        };
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
