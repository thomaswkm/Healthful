import java.io.*;

public class GestorArchivo {


    public GestorArchivo(){
    }

    public void agregarPaciente(String ruta, Paciente p){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));
            writer.write(p.toString());
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
                    return line;
                }
            }
        }catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        }
        return "El paciente no está registrado";
    }


}
