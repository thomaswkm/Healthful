package main;

import gui.VentanaMenuBienvenida;
import model.Healthful;

public class Main {
    public static void main(String[] args) {
        Healthful healthful = new Healthful();
        new VentanaMenuBienvenida(healthful);
    }
}
