package utils;

import model.Cita;
import model.EstadoCivil;
import model.Medico;
import model.Paciente;
import model.Rol;
import model.Sexo;
import model.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Mapper {
    public static List<Cita> toCitas(List<String[]> registros) {
        return registros.stream().map(Mapper::getCita).collect(toList());
    }

    public static List<Usuario> toUsuarios(List<String[]> registros) {
        return registros.stream().map(Mapper::getUsuario).collect(toList());
    }

    public static List<Paciente> toPacientes(List<String[]> registros) {
        return registros.stream().map(Mapper::getPaciente).collect(toList());
    }

    public static List<Medico> toMedicos(List<String[]> registros) {
        return registros.stream().map(Mapper::getMedico).collect(toList());
    }

    private static Medico getMedico(String[] registro) {
        LocalDate fechaNacimiento = getFecha(registro[3]);

        Sexo sexo = Sexo.valueOf(registro[4]);
        EstadoCivil estadoCivil = EstadoCivil.valueOf(registro[5]);

        return new Medico(registro[0], registro[1], registro[2], fechaNacimiento, sexo, estadoCivil, registro[6], new ArrayList<>());
    }

    private static Usuario getUsuario(String[] registro) {
        return new Usuario(registro[0], registro[1], Rol.valueOf(registro[2]));
    }

    private static Paciente getPaciente(String[] registro) {
        LocalDate fechaNacimiento = getFecha(registro[3]);
        Sexo sexo = Sexo.valueOf(registro[4]);
        EstadoCivil estadoCivil = EstadoCivil.valueOf(registro[5]);

        return new Paciente(registro[0], registro[1], registro[2], fechaNacimiento, sexo, estadoCivil, new ArrayList<>());
    }

    private static Cita getCita(String[] registro) {
        LocalDate fecha = getFecha(registro[0]);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime tiempo = LocalTime.parse(registro[1], dateTimeFormatter);

        return new Cita(fecha, tiempo, registro[2], registro[3]);
    }

    private static LocalDate getFecha(String registro) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(registro, formato);
    }
}
