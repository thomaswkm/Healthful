package model;

import java.util.ArrayList;

public class Medico {
    private String rut;
    private String nombre;
    private String especialidad;
    private ArrayList<Cita> citas = new ArrayList<>();

    public Medico(String rut, String nombre, String especialidad, ArrayList<Cita> citas) {
        this.rut = rut;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public Medico() {
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
