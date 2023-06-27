package data;

import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorArchivoTest {
    private final String rutaUsuarios = "src/test/resources/usuarios.txt";
    private final String rutaPacientes = "src/test/resources/pacientes.txt";
    private final String rutaMedicos = "src/test/resources/medicos.txt";
    private final String rutaCitas = "src/test/resources/citas.txt";

    @Test
    void leerUsuarios() {
        List<Usuario> usuarios = GestorArchivo.leerUsuarios(rutaUsuarios);

        Usuario usuario = usuarios.get(2);

        assertAll("Lectura Usuarios",
                () -> assertEquals(3, usuarios.size()),
                () -> assertEquals("252797556", usuario.getRut()),
                () -> assertEquals("sjg8", usuario.getPassword()),
                () -> assertEquals(Rol.ADMIN, usuario.getRol())
        );
    }

    @Test
    void leerPacientes() {
        List<Paciente> pacientes = GestorArchivo.leerPacientes(rutaPacientes);

        int size = pacientes.size();
        Paciente paciente = pacientes.get(size - 1);
        LocalDate date = LocalDate.of(1959, 11, 22);

        assertAll("Lectura Pacientes",
                () -> assertEquals(20, size),
                () -> assertEquals("464962468", paciente.getRut()),
                () -> assertEquals(date, paciente.getFechaNacimiento()),
                () -> assertEquals(Sexo.MUJER, paciente.getSexo())
        );
    }


    @Test
    void leerMedicos() {
        List<Medico> medicos = GestorArchivo.leerMedicos(rutaMedicos);

        int size = medicos.size();
        Medico medico = medicos.get(size - 1);
        LocalDate date = LocalDate.of(1965, 1, 6);

        assertAll("Lectura Pacientes",
                () -> assertEquals(20, size),
                () -> assertEquals("482557090", medico.getRut()),
                () -> assertEquals(date, medico.getFechaNacimiento()),
                () -> assertEquals(Sexo.MUJER, medico.getSexo()),
                () -> assertEquals("GENERAL", medico.getEspecialidad())
        );
    }

    @Test
    void leerCitas() {
        List<Cita> citas = GestorArchivo.leerCitas2(rutaCitas);

        int size = citas.size();
        Cita cita = citas.get(size - 1);
        LocalDate fecha = LocalDate.of(2024, 4, 9);
        LocalTime hora = LocalTime.of(12, 56);

        assertAll("Lectura Citas",
                () -> assertEquals(30, size),
                () -> assertEquals("48764681", cita.getRutPaciente()),
                () -> assertEquals("374933647", cita.getRutMedico()),
                () -> assertEquals(fecha, cita.getFecha()),
                () -> assertEquals(hora, cita.getHora())
        );
    }


}