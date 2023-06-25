package model;

public class Usuario {
    private String rut;
    private String password;

    public Usuario(String rut, String password) {
        this.rut = rut;
        this.password = password;
    }

    public Usuario() {

    }

    public boolean verificacion(String rut, String password) {
        return this.rut.equals(rut) && this.password.equals(password);
    }
}