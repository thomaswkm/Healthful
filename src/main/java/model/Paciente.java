package model;

import java.util.ArrayList;

public class Paciente {
    private String rut;
    private String nombre;
    private ArrayList<Cita> citas = new ArrayList<>();

    public Paciente(String rut, String nombre, ArrayList<Cita> citas) {
        this.rut = rut;
        this.nombre = nombre;
        this.citas = citas;
    }

    public Paciente() {
    }

    public String getRut() {
        return rut;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void addCita(Cita c) {
        citas.add(c);
    }

    public void removeCita(Cita c) {
        citas.remove(c);
    }
}
