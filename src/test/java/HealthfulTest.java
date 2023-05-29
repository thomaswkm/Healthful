import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        usuario1 = new Usuario("204036712","passw1");
        usuario2 = new Usuario("203721803","123");
        paciente = new Paciente("204036712","nombrePaciente",new ArrayList<>());
        medico = new Medico("203721803","nombreMedico","n/a",new ArrayList<>());
        cita = new Cita(paciente.getRut(),medico.getRut(),30,06,2023,14,30);
        healthful.addUsuario(usuario1);
        healthful.addUsuario(usuario2);
        healthful.addPaciente(paciente);
        healthful.addMedico(medico);
        healthful.addCita(cita);
    }

    @Test
    void addPaciente() {
        healthful.addPaciente(paciente);
        assertTrue(healthful.getPacientes().contains(paciente));
    }

    @Test
    void addMedico() {
        healthful.addMedico(medico);
        assertTrue(healthful.getMedicos().contains(medico));
    }

    @Test
    void addCita() {
        healthful.addCita(cita);
        assertTrue(healthful.getCitas().contains(cita));
    }

    @Test
    void addUsuario() {
        healthful.addUsuario(usuario1);
        assertTrue(healthful.getUsuarios().contains(usuario1));
        assertFalse(healthful.getUsuarios().contains(usuario2));
    }

    @Test
    void mostrarMedicos() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        healthful.mostrarMedicos();
        System.setOut(System.out);
        String consoleOutput = output.toString();
        assertEquals(healthful.getMedicos().toString(),consoleOutput.trim());

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
        paciente.addCita(cita);


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        healthful.mostrarCitasAgendadas(paciente);
        System.setOut(System.out);
        String consoleOutput = output.toString();


        assertEquals(paciente.getCitas().toString(),consoleOutput.trim());

    }

}