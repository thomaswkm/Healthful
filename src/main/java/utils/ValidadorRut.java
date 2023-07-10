package utils;

import java.util.Arrays;

public class ValidadorRut {

    private static boolean esRutValido(String digitoVerificadorCalculado, String digitoVerificadorIngresado) {
        return digitoVerificadorIngresado.equalsIgnoreCase(digitoVerificadorCalculado);
    }

    public static boolean validar(String rut) {
        String digitoVerificadorCalculado = ValidadorRut.calcularDigitoVerificador(rut);
        String digitoVerificadorIngresado = extraerDigitoVerificador(rut);

        return esRutValido(digitoVerificadorCalculado, digitoVerificadorIngresado);
    }

    public static String extraerDigitoVerificador(String rut) {
        return rut.substring(rut.length() - 1);
    }

    public static String calcularDigitoVerificador(String rut) {
        rut = quitarPuntosYGuion(rut);
        rut = quitarDigitoVerificador(rut);
        rut = invertirCadena(rut);

        int[] multiplicacionDigitos = multiplicarDigitosRut(rut);
        int sumaMultiplicaciones = sumarMultiplicaciones(multiplicacionDigitos);

        double division = dividirSuma(sumaMultiplicaciones);
        int divisionSinDecimales = quitarDecimales(division);
        int multiplicacion = multiplicarDivision(divisionSinDecimales);
        int restaPaso7 = restar(sumaMultiplicaciones, multiplicacion);
        int restaPaso8 = restar(11, restaPaso7);

        return asignarDigitoVerificador(restaPaso8);
    }

    private static String asignarDigitoVerificador(int restaPaso8) {
        if (restaPaso8 == 10) return "k";
        if (restaPaso8 == 11) return "0";
        return String.valueOf(restaPaso8);
    }

    private static int restar(int numero1, int numero2) {
        return numero1 - numero2;
    }

    private static int multiplicarDivision(int division) {
        return division * 11;
    }

    private static int quitarDecimales(double division) {
        return (int) division;
    }

    private static double dividirSuma(int sumaMultiplicaciones) {
        return (double) sumaMultiplicaciones / 11;
    }

    private static int sumarMultiplicaciones(int[] multiplicacionDigitos) {
        return Arrays.stream(multiplicacionDigitos).sum();
    }

    static int[] multiplicarDigitosRut(String rut) {
        int[] multiplicaciones = new int[rut.length()];
        int factorMultiplicacion = 2;

        for (int i = 0; i < rut.length(); i++) {
            int digito = Integer.parseInt(String.valueOf(rut.charAt(i)));
            multiplicaciones[i] = digito * factorMultiplicacion;
            factorMultiplicacion++;
            if (factorMultiplicacion == 8) factorMultiplicacion = 2;
        }
        return multiplicaciones;
    }

    public static String invertirCadena(String rut) {
        return new StringBuilder(rut).reverse().toString();
    }

    public static String quitarDigitoVerificador(String rut) {
        return rut.substring(0, rut.length() - 1);
    }

    public static String quitarPuntosYGuion(String rut) {
        return rut.replaceAll("[.-]", "");
    }
}
