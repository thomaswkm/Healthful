import java.io.*;

public class GestorArchivo {


    public GestorArchivo(){
    }

    public void agregarUsuario(String ruta, String rut, String contraseña){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));
            writer.write(rut+","+contraseña);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
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

    public void agregarPersonal(String ruta, Personal p){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));
            writer.write(p.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
    }

}
