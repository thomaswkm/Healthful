package gui;

import model.Healthful;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaMenuBienvenida extends Ventana {
    private final Healthful healthful;
    private JButton botonIniciarSesion;

    public VentanaMenuBienvenida(Healthful healthful) {
        super("Healthful", 800, 520);
        this.healthful = healthful;
        generarElementos();
    }

    private void generarElementos() {
        generarBotonIniciarSesion();
        generarImagen();
        generarIconos();
        generarLogo();
        generarLabelFrase();
        this.repaint();
    }

    private void generarBotonIniciarSesion() {
        String textoBoton = "Iniciar Sesión";
        botonIniciarSesion = super.generarBoton(textoBoton, 550, 100, 200, 40);
        botonIniciarSesion.setForeground(Color.WHITE);
        botonIniciarSesion.setBackground(Color.RED);
        this.add(botonIniciarSesion);
        botonIniciarSesion.addActionListener(this);
    }

    private void generarImagen() {
        try {
            BufferedImage imagen = ImageIO.read(new File("src/main/resources/images/fondo1.png"));
            JLabel labelImagen = new JLabel(new ImageIcon(imagen));
            labelImagen.setBounds(400, 50, imagen.getWidth(), imagen.getHeight()); // Ajustar tamaño automáticamente
            this.add(labelImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarIconos() {
        ImageIcon icono1 = new ImageIcon("src/main/resources/images/icon1.png");
        ImageIcon icono2 = new ImageIcon("src/main/resources/images/icon2.png");
        ImageIcon icono3 = new ImageIcon("src/main/resources/images/icon3.png");

        JLabel labelIcono1 = new JLabel(icono1);
        JLabel labelIcono2 = new JLabel(icono2);
        JLabel labelIcono3 = new JLabel(icono3);

        labelIcono1.setBounds(10, 10, 50, 50);
        labelIcono2.setBounds(70, 10, 50, 50);
        labelIcono3.setBounds(130, 10, 50, 50);

        this.add(labelIcono1);
        this.add(labelIcono2);
        this.add(labelIcono3);
    }

    private void generarLogo() {
        try {
            BufferedImage logo = ImageIO.read(new File("src/main/resources/images/logo.png"));
            JLabel labelLogo = new JLabel(new ImageIcon(logo));
            labelLogo.setBounds(10, 70, 100, 100);
            this.add(labelLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarLabelFrase() {
        String frase = "Tu bienestar en tus manos";
        super.generarJLabel(frase, 10, 200, 300, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonIniciarSesion) {
            // Acciones al hacer clic en el botón "Iniciar Sesión"
        }
    }
}
