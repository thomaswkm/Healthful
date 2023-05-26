import java.io.*;

public class Usuario {
    private String rut;
    private String password;

    public Usuario(String rut, String password) {
        this.rut = rut;
        this.password = password;
    }

    public Usuario(){

    }

    /*como password no deberia ser utilizado de ninguna manera(getter y setters incluidos) fuera de la clase, se realizaría el registro de usuario
     y login dentro de la misma clase.*/

    public boolean comprobarUsuario() {
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

    public void registrarUsuario() {
        if (!comprobarUsuario()) {
            System.out.println("Usuario ya registrado");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(rut + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo.");
        }
    }


    public boolean login() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(rut) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        }
        System.out.println("Contraseña incorrecta.");
        return false;
    }


}