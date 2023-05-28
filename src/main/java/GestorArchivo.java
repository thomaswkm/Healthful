import java.io.*;
import java.util.ArrayList;

public class GestorArchivo {


    public GestorArchivo(){
    }

    public void agregarPaciente(String ruta, Paciente p){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));
            writer.write(p.toString());
            for (Cita c: p.getCitas()) {
                writer.write(","+c.toString());
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
    }

    public void agregarMedico(String ruta, Medico m){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));
            writer.write(m.toString());
            for (Cita c: m.getCitas()){
                writer.write(","+c.toString());
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
    }

    public String devolverFicha(String ruta, String rut){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if(line.split(",")[0].equals(rut)){
                    return line.split(",")[0]+line.split(",")[1];
                }
            }
        }catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        }
        return "El paciente no está registrado";
    }

    public ArrayList<Paciente> leerPacientes(String ruta) {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                ArrayList<Cita> citas = new ArrayList<>();

                for (int i = 2; i < data.length; i+=7) {

                     citas.add(new Cita(data[i],data[i+1],Integer.parseInt(data[i+2]),Integer.parseInt(data[i+3]),Integer.parseInt(data[i+4]),Integer.parseInt(data[i+5]),Integer.parseInt(data[i+6])));
                }

                Paciente paciente = new Paciente(data[0], data[1], citas);
                pacientes.add(paciente);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo.");
        }
        return pacientes;
    }

    public ArrayList<Medico> leerMedicos(String ruta) {
        ArrayList<Medico> medicos = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                ArrayList<Cita> citas = new ArrayList<>();

                for (int i = 3; i < data.length; i+=7) {

                    citas.add(new Cita(data[i],data[i+1],Integer.parseInt(data[i+2]),Integer.parseInt(data[i+3]),Integer.parseInt(data[i+4]),Integer.parseInt(data[i+5]),Integer.parseInt(data[i+6])));
                }

                Medico medico = new Medico(data[0], data[1], data[2],citas);
                medicos.add(medico);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo.");
        }
        return medicos;
    }


}
