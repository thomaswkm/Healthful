package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Paciente extends Usuario {
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaNacimiento;
    private final Sexo sexo;
    private final EstadoCivil estadoCivil;

    private final List<Cita> citas;

    public Paciente(String rut,
                    String nombre,
                    String apellido,
                    LocalDate fechaNacimiento,
                    Sexo sexo,
                    EstadoCivil estadoCivil,
                    List<Cita> citas) {
        super(rut, Rol.PACIENTE);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.citas = citas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void addCita(Cita cita) {
        citas.add(cita);
    }

    public void removeCita(Cita cita) {
        citas.remove(cita);
    }

    public void solicitarCita(Healthful healthful) {
        int dia = 0;
        int mes = 0;
        int year = 0;
        int hora = 0;
        int minuto = 0;
        String rut = "";
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ingrese el día: ");
            dia = sc.nextInt();
            System.out.println("Ingrese el número del mes: ");
            mes = sc.nextInt();
            System.out.println("Ingrese el año: ");
            year = sc.nextInt();
            System.out.println("Ingrese la hora del día: ");
            hora = sc.nextInt();
            System.out.println("Ingrese los minutos: ");
            minuto = sc.nextInt();
            System.out.println("Ingrese el rut del médico: ");
            rut = sc.next();
        } catch (Exception e) {
            System.out.println("Error al ingresar los datos");
        }
        healthful.agregarCita(healthful.obtenerMedico(rut), this, dia, mes, year, hora, minuto);
    }

    public void cancelarCita(Healthful healthful) {
        System.out.println(getCitas());
        System.out.println("Elige el numero de cita a borrar");
        int ans = new Scanner(System.in).nextInt();
        Cita c = getCitas().get(ans);
        healthful.removeCita(this, healthful.obtenerMedico(getCitas().get(ans).getRutMedico()), c);
    }

    public Sexo getSexo() {
        return sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
