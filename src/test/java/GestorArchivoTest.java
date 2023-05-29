import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorArchivoTest {
    private GestorArchivo gestorArchivo;

    @BeforeEach
    void setUp() {
        gestorArchivo = new GestorArchivo();
    }

    @Test
    void leerArchivo(@TempDir Path tempRuta) throws IOException {
        String contenidoEsperado = "Contenido de prueba\nNueva Linea";

        Path archivo = tempRuta.resolve("prueba.txt");
        Files.write(archivo, contenidoEsperado.getBytes());

        String contenidoActual = gestorArchivo.leerArchivo(archivo.toString());

        assertEquals(contenidoEsperado, contenidoActual);
        assertTrue(Files.exists(archivo));
    }
}