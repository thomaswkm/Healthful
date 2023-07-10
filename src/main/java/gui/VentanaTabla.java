package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaTabla extends JFrame {
    private final String[][] datos;
    private final String[] nombreColumnas;

    public VentanaTabla(String[][] datos, String[] nombreColumnas, String titulo) {
        super(titulo);
        this.datos = datos;
        this.nombreColumnas = nombreColumnas;
        generarTabla();
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void generarTabla() {
        DefaultTableModel dtm = new DefaultTableModel(datos, nombreColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        final JTable tabla = new JTable(dtm);

        tabla.setPreferredScrollableViewportSize(new Dimension(500, 520));
        JScrollPane scrollPane = new JScrollPane(tabla);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cerrarVentana();
            }
        });
    }

    private void cerrarVentana() {
        this.dispose();
    }
}