package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum DisjuntorTermoMag {

    D10("10"),
    D15("15"),
    D20("20"),
    D25("25"),
    D30("30"),
    D35("35"),
    D40("40"),
    D50("50"),
    D63("63"),
    D80("80"),
    D100("100"),
    D125("125");
    

    private final String numero;

    private DisjuntorTermoMag(String sigla) {
        this.numero = sigla;
    }

    public int getNumero() {
        return Integer.parseInt(numero);
    }

    public static List<DisjuntorTermoMag> getLista() {
        List<DisjuntorTermoMag> lista = new ArrayList<>();
        lista.add(DisjuntorTermoMag.D10);
        lista.add(DisjuntorTermoMag.D15);
        lista.add(DisjuntorTermoMag.D20);
        lista.add(DisjuntorTermoMag.D25);
        lista.add(DisjuntorTermoMag.D30);
        lista.add(DisjuntorTermoMag.D35);
        lista.add(DisjuntorTermoMag.D40);
        lista.add(DisjuntorTermoMag.D50);
        lista.add(DisjuntorTermoMag.D63);
        lista.add(DisjuntorTermoMag.D80);
        lista.add(DisjuntorTermoMag.D100);
        lista.add(DisjuntorTermoMag.D125);
        return lista;
    }
}
