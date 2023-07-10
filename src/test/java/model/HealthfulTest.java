package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HealthfulTest {
    Healthful healthful;
    Usuario usuario1;
    Usuario usuario2;
    Paciente paciente;
    Medico medico;
    Cita cita;

    @BeforeEach
    void setUp() {
        healthful = new Healthful(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        usuario1 = new Usuario("204036712", "passw1", Rol.PACIENTE);
        usuario2 = new Usuario("203721803", "123", Rol.PACIENTE);
        paciente = new Paciente("204036712", "nombrePaciente", "apellido", LocalDate.now(), Sexo.HOMBRE, EstadoCivil.CASADO_A, new ArrayList<>());
        medico = new Medico("203721803", "nombreMedico", "apellido", LocalDate.now(), Sexo.HOMBRE, EstadoCivil.CASADO_A, "n/a", new ArrayList<>());
        cita = new Cita(LocalDate.now(), LocalTime.now(), paciente.getRut(), medico.getRut());
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