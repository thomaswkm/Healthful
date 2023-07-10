package gui;

import model.Healthful;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaMenuBienvenida extends Ventana {
    private final Healthful healthful;
    private JButton botonIniciarSesion;
    private JButton botonSalir;

    public VentanaMenuBienvenida(Healthful healthful) {
        super("Healthful", 1040, 702);
        this.healthful = healthful;
        generarElementos();
    }

    private void generarElementos() {
        generarBotonIniciarSesion();
        generarBotonSalir();
        generarImagen();
        generarIconos();
        generarLogo();
        generarLabelFrase();
        this.getContentPane().setBackground(Color.WHITE);
        this.repaint();
    }

    private void generarBotonSalir() {
        String textoBoton = "Salir";
        botonSalir = super.generarBoton(textoBoton, 200, 413, 200, 40);
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBackground(Color.RED);
        this.add(botonSalir);
        botonSalir.addActionListener(this);
    }

    private void generarBotonIniciarSesion() {
        String textoBoton = "Iniciar Sesión";
        botonIniciarSesion = super.generarBoton(textoBoton, 200, 330, 200, 40);
        botonIniciarSesion.setForeground(Color.WHITE);
        botonIniciarSesion.setBackground(Color.GREEN);
        this.add(botonIniciarSesion);
        botonIniciarSesion.addActionListener(this);
    }

    private void generarImagen() {
        try {
            BufferedImage imagen = ImageIO.read(new File("src/main/resources/images/fondo1.png"));
            JLabel labelImagen = new JLabel(new ImageIcon(imagen));
            labelImagen.setBounds(550, 0, imagen.getWidth(), imagen.getHeight());
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

        labelIcono1.setBounds(30, 20, 50, 50);
        labelIcono2.setBounds(105, 20, 50, 50);
        labelIcono3.setBounds(180, 20, 50, 50);

        labelIcono1.setIcon(new ImageIcon(resizeIcon(icono1.getImage(), labelIcono1.getWidth(), labelIcono1.getHeight())));

        labelIcono2.setIcon(new ImageIcon(resizeIcon(icono2.getImage(), labelIcono2.getWidth(), labelIcono2.getHeight())));

        labelIcono3.setIcon(new ImageIcon(resizeIcon(icono3.getImage(), labelIcono3.getWidth(), labelIcono3.getHeight())));

        this.add(labelIcono1);
        this.add(labelIcono2);
        this.add(labelIcono3);
    }

    private Image resizeIcon(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void generarLogo() {
        try {
            BufferedImage logo = ImageIO.read(new File("src/main/resources/images/logo.png"));
            JLabel labelLogo = new JLabel(new ImageIcon(logo));
            labelLogo.setBounds(170, 162, 259, 94);
            this.add(labelLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarLabelFrase() {
        String frase = "Tu bienestar en tus manos";
        super.generarJLabel(frase, 220, 270, 300, 30);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonIniciarSesion) {
            new VentanaInicioSesion(healthful);
            this.dispose();
        }

        if (event.getSource() == botonSalir) {
            JOptionPane.showMessageDialog(this, "Hasta Luego! 😉");
            this.dispose();
        }
    }
}
