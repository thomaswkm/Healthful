package model;

import java.util.Objects;

public class Usuario {
    private final Rol rol;
    protected String rut;
    private String password;

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

    public String[] toCSV() {
        return new String[]{rut, password, rol.toString()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!Objects.equals(rut, usuario.rut)) return false;
        if (!Objects.equals(password, usuario.password)) return false;
        return rol == usuario.rol;
    }
}