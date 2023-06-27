package model;

import main.HealthfulOld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HealthfulOldTest {
    private String[][] usuarios;

    @BeforeEach
    void setUp() {
        usuarios = new String[][]{
                {"203672403", "thomas123", "model.Medico"},
                {"21423562k", "testpassw", "model.Paciente"},
                {"185623510", "pass2", "model.Medico"}
        };
    }

    @Test
    void buscarUsuario_DebeRetornarElUsuario_CuandoEstaRegistrado() {
        String rutUsuario = "203672403";

        String[] usuarioExistenteReal = HealthfulOld.buscarUsuarioPorRut(usuarios, rutUsuario);

        String[] usuarioExistenteEsperado = {"203672403", "thomas123", "model.Medico"};

        assertArrayEquals(usuarioExistenteEsperado, usuarioExistenteReal);
    }

    @Test
    void buscarUsuario_DebeRetornarUnArregloVacio_CuandoNoExisteElUsuario() {
        String rutUsuario = "123456789";

        String[] arregloDevuelto = HealthfulOld.buscarUsuarioPorRut(usuarios, rutUsuario);

        String[] arregloEsperado = new String[3];

        assertArrayEquals(arregloEsperado, arregloDevuelto);
    }

    @Test
    void validarFormatoRutSinPuntosNiGuion_DebeRetonarElRutIngresado_CuandoEsUnRutValido() {
        String rut = "123456789";

        String rutDevuelto = HealthfulOld.validarFormatoRut(rut);

        assertEquals(rut, rutDevuelto);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "rutnovalido",
            "12.345.567-5",
            "",
    })
    void validarFormatoRutSinPuntosNiGuion_DebeArrojarExcepcion_CuandoNoUnRutValido(String rut) {
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> HealthfulOld.validarFormatoRut(rut)
        );

        assertEquals("El formato del RUT no es válido", runtimeException.getMessage());
    }
}