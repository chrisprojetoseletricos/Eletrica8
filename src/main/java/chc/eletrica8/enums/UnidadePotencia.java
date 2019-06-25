package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum UnidadePotencia {

    BTU("BTU"),
    CV("CV"),
    HP("HP"),
    VA("VA"),
    W("W");

    private final String sigla;

    private UnidadePotencia(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public static List<UnidadePotencia> getLista() {
        List<UnidadePotencia> lista = new ArrayList<>();
        lista.add(UnidadePotencia.BTU);
        lista.add(UnidadePotencia.CV);
        lista.add(UnidadePotencia.HP);
        lista.add(UnidadePotencia.VA);
        lista.add(UnidadePotencia.W);
        return lista;
    }
}
