package data;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import model.Cita;
import model.Medico;
import model.Paciente;
import model.Usuario;
import utils.Mapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorDeArchivos {
    public static List<Cita> leerCitas(String ruta) {
        List<String[]> registros = GestorDeArchivos.leerArchivo(ruta);
        return Mapper.toCitas(registros);
    }

    public static List<Medico> leerMedicos(String ruta) {
        List<String[]> registros = GestorDeArchivos.leerArchivo(ruta);
        return Mapper.toMedicos(registros);
    }

    public static List<Paciente> leerPacientes(String ruta) {
        List<String[]> registros = GestorDeArchivos.leerArchivo(ruta);
        return Mapper.toPacientes(registros);
    }

    public static List<Usuario> leerUsuarios(String ruta) {
        List<String[]> registros = leerArchivo(ruta);
        return Mapper.toUsuarios(registros);
    }

    public static void guardarRegistro(String[] registro, String ruta) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ruta, true))) {
            writer.writeNext(registro, false);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<String[]> leerArchivo(String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

