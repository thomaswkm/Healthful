package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HealthfulTest {

    Healthful healthful;
    Usuario usuario1;
    Usuario usuario2;
    Paciente paciente;
    Medico medico;
    Cita cita;
    @BeforeEach
    void setUp(){
        healthful = new Healthful(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
        usuario1 = new Usuario("204036712","passw1", Rol.PACIENTE);
        usuario2 = new Usuario("203721803","123", Rol.PACIENTE);
        paciente = new Paciente("204036712","nombrePaciente", "apellido", LocalDate.now(), Sexo.HOMBRE, EstadoCivil.CASADO_A, new ArrayList<>());
        medico = new Medico("203721803","nombreMedico", "apellido", LocalDate.now(), Sexo.HOMBRE, EstadoCivil.CASADO_A, "n/a",new ArrayList<>());
        cita = new Cita(LocalDate.now(), LocalTime.now(), paciente.getRut(),medico.getRut());

        healthful.addUsuario(usuario1);
        healthful.addUsuario(usuario2);
        healthful.addPaciente(paciente);
        healthful.addMedico(medico);
        healthful.addCita(cita);
    }

    @Test
    void addPaciente() {
//        healthful.addPaciente(paciente);
//        assertTrue(healthful.getPacientes().contains(paciente));
    }

    @Test
    void addMedico() {
//        healthful.addMedico(medico);
//        assertTrue(healthful.getMedicos().contains(medico));
    }

    @Test
    void addCita() {
        healthful.addCita(cita);
//        assertTrue(healthful.getCitas().contains(cita));
    }

    @Test
    void addUsuario() {
        healthful.addUsuario(usuario1);
//        assertTrue(healthful.getUsuarios().contains(usuario1));
    }

    @Test
    void obtenerMedico() {
        assertEquals(healthful.obtenerMedico(medico.getRut()), medico);
    }

    @Test
    void obtenerPaciente() {
        assertEquals(healthful.obtenerPaciente(paciente.getRut()), paciente);
    }

    @Test
    void mostrarCitasAgendadas() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        healthful.getPacientes().get(0).getCitas().add(cita);
        healthful.mostrarCitasAgendadas(paciente);

        String consoleOutput = output.toString().trim();

        assertEquals(paciente.getCitas().toString(),consoleOutput);
    }

    @Test
    void login_debeRetornarElUsuario_cuandoExiste() {
        Usuario usuario = healthful.login("204036712", "passw1");

        assertEquals("204036712", usuario.getRut());
        assertEquals("passw1", usuario.getPassword());
    }

    @Test
    void login_debeArrojarUnaExcepcion_cuandoElRutNoExiste() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> healthful.login("111111111", "passw1"));

        assertEquals("Rut y/o Contraseña incorrectos", exception.getMessage());
    }
    @Test
    void login_debeArrojarUnaExcepcion_cuandoElPasswordEsIncorrecto() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> healthful.login("204036712", "1234"));

        assertEquals("Rut y/o Contraseña incorrectos", exception.getMessage());
    }
}