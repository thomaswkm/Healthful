package data;

import com.opencsv.CSVReader;
import model.*;
import utils.Mapper;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorArchivo {
    public static void guardarUsuarios(String ruta, Healthful healthful) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Usuario usuario : healthful.getUsuarios()) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de usuarios.");
        }
    }

    public static void guardarPacientes(String ruta, Healthful healthfulh) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Paciente paciente : healthfulh.getPacientes()) {
                writer.write(paciente.toString());
                for (Cita cita : paciente.getCitas()) {
                    writer.write("," + cita.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de pacientes.");
        }
    }

    public static void guardarMedicos(String ruta, Healthful healthful) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Medico medico : healthful.getMedicos()) {
                writer.write(medico.toString());
                for (Cita cita : medico.getCitas()) {
                    writer.write("," + cita.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de médicos.");
        }
    }

    public static void guardarCitas(String ruta, Healthful healthful) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Cita cita : healthful.getCitas()) {
                writer.write(cita.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de citas.");
        }
    }

    public static List<Cita> leerCitas2(String ruta) {
        List<String[]> registros = GestorArchivo.leerArchivo(ruta);
        return Mapper.toCitas(registros);
    }


    public static List<Medico> leerMedicos(String ruta) {
        List<String[]> registros = GestorArchivo.leerArchivo(ruta);
        return Mapper.toMedicos(registros);
    }

    public static List<Paciente> leerPacientes(String ruta) {
        List<String[]> registros = GestorArchivo.leerArchivo(ruta);
        return Mapper.toPacientes(registros);
    }

    public static List<Usuario> leerUsuarios(String ruta) {
        List<String[]> registros = leerArchivo(ruta);
        return Mapper.toUsuarios(registros);
    }

    private static List<String[]> leerArchivo(String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

