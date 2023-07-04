import java.io.*;
import java.util.ArrayList;

public class GestorArchivo {


    public GestorArchivo(){
    }

    public boolean comprobarUsuario(String rut) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(rut)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        }
        return true;
    }

    public boolean registrarUsuario(Usuario u) {
        if (!comprobarUsuario(u.toString().split(",")[0])) {
            System.out.println("Usuario ya registrado");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(u.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
        return true;
    }


    public boolean login(Usuario u) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (u.verificacion(parts[0],parts[1])) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        }
        System.out.println("Contraseña incorrecta.");
        return false;
    }

    public void guardarUsuarios(String ruta, Healthful h) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Usuario u : h.getUsuarios()) {
                writer.write(u.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de usuarios.");
        }
    }

    public void guardarPacientes(String ruta, Healthful h) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true) )) {
            for (Paciente p : h.getPacientes()) {
                writer.write(p.toString());
                for (Cita c : p.getCitas()) {
                    writer.write("," + c.toString());
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de pacientes.");
        }
    }

    public void guardarMedicos(String ruta, Healthful h) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Medico m : h.getMedicos()) {
                writer.write(m.toString());
                for (Cita c : m.getCitas()) {
                    writer.write("," + c.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de médicos.");
        }
    }

    public void guardarCitas(String ruta, Healthful h) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Cita c : h.getCitas()) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de citas.");
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

    public ArrayList<Cita> leerCitas(String ruta){
        ArrayList<Cita> citas = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();
            while(line != null){
                String[] data = line.split(",");
                citas.add(new Cita(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6])));
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            System.out.println("No se pudo leer el archivo.");
        }
        return citas;
    }


    public ArrayList<Usuario> leerUsuarios(String ruta) {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();
            while(line != null){
                String[] data = line.split(",");
                usuarios.add(new Usuario(data[0],data[1]));
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            System.out.println("No se pudo leer el archivo.");
        }
        return usuarios;

    }
}
