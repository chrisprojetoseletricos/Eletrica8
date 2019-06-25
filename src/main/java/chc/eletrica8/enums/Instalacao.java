package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum Instalacao {
    A1,
    A2,
    B1,
    B2,
    F,
    G;

    public static List<Instalacao> getLista() {
        List<Instalacao> lista = new ArrayList<>();
        lista.add(Instalacao.A1);
        lista.add(Instalacao.A2);
        lista.add(Instalacao.B1);
        lista.add(Instalacao.B2);
        lista.add(Instalacao.F);
        lista.add(Instalacao.G);
        return lista;
    }
}
