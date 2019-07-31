package chc.eletrica8.enums;

import chc.eletrica8.uteis.Numero;
import java.util.ArrayList;
import java.util.List;

public enum BitolasMili {

    B0_5("0,5"),
    B0_75("0,75"),
    B1("1"),
    B1_5("1,5"),
    B2_5("2,5"),
    B4("4"),
    B6("6"),
    B10("10"),
    B16("16"),
    B25("25"),
    B35("35"),
    B50("50"),
    B70("70"),
    B95("95"),
    B120("120"),
    B150("150");

    private final String numero;

    private BitolasMili(String sigla) {
        this.numero = sigla;
    }

    public double getNumero() {
        return Numero.stringToDouble(numero, 0);
    }

    public static List<BitolasMili> getLista() {
        List<BitolasMili> lista = new ArrayList<>();
        lista.add(BitolasMili.B0_5);
        lista.add(BitolasMili.B0_75);
        lista.add(BitolasMili.B1);
        lista.add(BitolasMili.B1_5);
        lista.add(BitolasMili.B2_5);
        lista.add(BitolasMili.B4);
        lista.add(BitolasMili.B6);
        lista.add(BitolasMili.B10);
        lista.add(BitolasMili.B16);
        lista.add(BitolasMili.B25);
        lista.add(BitolasMili.B35);
        lista.add(BitolasMili.B50);
        lista.add(BitolasMili.B70);
        lista.add(BitolasMili.B95);
        lista.add(BitolasMili.B120);
        lista.add(BitolasMili.B150);
        return lista;
    }

    public static BitolasMili converte(double valor) {
        BitolasMili con = null;
        for (BitolasMili b : getLista()) {
            if (b.getNumero() == valor) {
                con = b;
            }

        }
        return con;
    }
}
