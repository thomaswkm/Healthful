package model;

public class Usuario {
    protected String rut;

    private String password;
    private Rol rol;

    public Usuario(String rut, String password, Rol rol) {
        this.rut = rut;
        this.password = password;
        this.rol = rol;
    }

    protected Usuario(String rut, Rol rol) {
        this.rut = rut;
        this.rol = rol;
    }

    public boolean verificacion(String password) {
        return this.password.equals(password);
    }

    public String getRut() {
        return rut;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }
}