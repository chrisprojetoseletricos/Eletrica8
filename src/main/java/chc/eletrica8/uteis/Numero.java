package chc.eletrica8.uteis;

import java.text.DecimalFormat;

public class Numero {

    public static String decimal(double valor, String formato) {

        DecimalFormat df = null;
        try {
            df = new DecimalFormat(formato);
        } catch (IllegalArgumentException e) {
            System.out.println("Formatação incorreta em decimal(): " + e);
        }
        String dfString = df.format(valor);
        return dfString;
    }

    public static double stringToDouble(String valor, double valorPadrao) {
        double dfDouble = 0;

        String pv = valor.replaceFirst(",", ".");
        if (valor.equals("")) {
            dfDouble = valorPadrao;
        } else {
            try {
                dfDouble = Double.parseDouble(pv);
            } catch (Exception e) {
                dfDouble = valorPadrao;
            }
        }

        return dfDouble;
    }

    public static Integer stringToInteger(String valor, int valorPadrao) {
        Integer inteiro = 0;
        String pv = valor.replaceAll(",", ".");
        if (valor.equals("")) {
            inteiro = valorPadrao;
        } else {
            inteiro = Integer.parseInt(pv);
        }
        return inteiro;
    }

    public static boolean ePrimo(double numero) {
        numero = Math.abs(numero);
        // se o número é par, não pode ser primo
        if (numero % 2 == 0) {
            return false;
        }
        // verificar se este número ímpar é primo
        for (long i = 3; i * i <= numero; i += 2) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }
}
