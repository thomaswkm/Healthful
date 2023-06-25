import data.GestorArchivo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorArchivoTest {

    @Test
    void leerArchivo(@TempDir Path tempRuta) throws IOException {
        String contenidoEsperado = "Contenido de prueba\nNueva Linea";

        Path archivo = tempRuta.resolve("prueba.txt");
        Files.write(archivo, contenidoEsperado.getBytes());

        String contenidoActual = GestorArchivo.leerArchivo(archivo.toString());

        assertEquals(contenidoEsperado, contenidoActual);
        assertTrue(Files.exists(archivo));
    }
}