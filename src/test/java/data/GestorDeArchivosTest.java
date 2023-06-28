package data;

import model.Cita;
import model.EstadoCivil;
import model.Medico;
import model.Paciente;
import model.Rol;
import model.Sexo;
import model.Usuario;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorDeArchivosTest {
    private final String rutaUsuarios = "src/test/resources/usuarios.txt";
    private final String rutaPacientes = "src/test/resources/pacientes.txt";
    private final String rutaMedicos = "src/test/resources/medicos.txt";
    private final String rutaCitas = "src/test/resources/citas.txt";

    @Test
    void leerUsuarios() {
        List<Usuario> usuarios = GestorDeArchivos.leerUsuarios(rutaUsuarios);

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
        List<Paciente> pacientes = GestorDeArchivos.leerPacientes(rutaPacientes);

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
        List<Medico> medicos = GestorDeArchivos.leerMedicos(rutaMedicos);

        int size = medicos.size();
        Medico medico = medicos.get(size - 1);
        LocalDate date = LocalDate.of(1965, 1, 6);

        assertAll("Lectura Medicos",
                () -> assertEquals(20, size),
                () -> assertEquals("482557090", medico.getRut()),
                () -> assertEquals(date, medico.getFechaNacimiento()),
                () -> assertEquals(Sexo.MUJER, medico.getSexo()),
                () -> assertEquals("GENERAL", medico.getEspecialidad())
        );
    }

    @Test
    void leerCitas() {
        List<Cita> citas = GestorDeArchivos.leerCitas(rutaCitas);

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

    @Test
    void testAgregarUsuario() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Usuario usuario1 = new Usuario("123456789", "1234", Rol.PACIENTE);
        Usuario usuario2 = new Usuario("987654321", "9574", Rol.MEDICO);

        GestorDeArchivos.guardarRegistro(usuario1.toCSV(), tempFile.toString());
        GestorDeArchivos.guardarRegistro(usuario2.toCSV(), tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String registro1 = lines.get(0);
        String registro2 = lines.get(1);

        assertAll("Guardar Usuarios",
                () -> assertEquals(2, lines.size()),
                () -> assertEquals("123456789,1234,PACIENTE", registro1),
                () -> assertEquals("987654321,9574,MEDICO", registro2));
    }

    @Test
    void testAgregarPaciente() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Paciente paciente1 = new Paciente("123456789",
                "nombre",
                "apellido",
                LocalDate.of(2000, 1, 1),
                Sexo.HOMBRE,
                EstadoCivil.CASADO_A,
                new ArrayList<>());

        Paciente paciente2 = new Paciente("987654321",
                "paciente",
                "apellido2",
                LocalDate.of(2023, 1, 1),
                Sexo.MUJER,
                EstadoCivil.SOLTERO_A,
                new ArrayList<>());


        GestorDeArchivos.guardarRegistro(paciente1.toCSV(), tempFile.toString());
        GestorDeArchivos.guardarRegistro(paciente2.toCSV(), tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String registro1 = lines.get(0);
        String registro2 = lines.get(1);

        assertAll("Guardar Pacientes",
                () -> assertEquals(2, lines.size()),
                () -> assertEquals("123456789,nombre,apellido,2000-01-01,HOMBRE,CASADO_A", registro1),
                () -> assertEquals("987654321,paciente,apellido2,2023-01-01,MUJER,SOLTERO_A", registro2));
    }

    @Test
    void testAgregarMedico() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Medico medico = new Medico("123456789",
                "nombre",
                "apellido",
                LocalDate.of(1970, 12, 30),
                Sexo.HOMBRE,
                EstadoCivil.CASADO_A,
                "GENERAL",
                new ArrayList<>());

        GestorDeArchivos.guardarRegistro(medico.toCSV(), tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String registro = lines.get(0);

        assertAll("Guardar Medico",
                () -> assertEquals(1, lines.size()),
                () -> assertEquals("123456789,nombre,apellido,1970-12-30,HOMBRE,CASADO_A,GENERAL", registro));
    }

    @Test
    void testAgregarCita() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Cita cita = new Cita(LocalDate.of(2023, 6, 27),
                LocalTime.of(10, 0),
                "123456789",
                "987654321");

        GestorDeArchivos.guardarRegistro(cita.toCSV(), tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String registro = lines.get(0);

        assertAll("Guardar Medico",
                () -> assertEquals(1, lines.size()),
                () -> assertEquals("2023-06-27,10:00,123456789,987654321", registro));
    }
}