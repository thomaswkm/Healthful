package gui;

import model.Healthful;
import model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaAgendar extends Ventana {
    private final Paciente paciente;
    private final Healthful healthful;
    private JButton botonBuscar;
    private JButton botonRegresar;
    private JComboBox listaCarreras;

    public VentanaAgendar(Paciente paciente, Healthful healthful) {
        super("Agendar Cita", 500, 520);
        this.paciente = paciente;
        this.healthful = healthful;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
//        generarBotonCancelar();
//        generarBotonIngresar();
//        generarCampoRut();
//        generarCampoPassword();
    }

    private void generarEncabezado() {

    }
}
