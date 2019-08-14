package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum EspacamentoCabos {
    Nula("Nula"),
    _1DiametroDoCabo("1 Diametro do Cabo"),
    _0_125m("0,125 m"),
    _0_25m("0,25 m"),
    _0_50m("0,50 m"),
    MaiorQDobroDoDiametro("Maior que o dobro do diametro"),
    NaoAplicavel("Não se aplica");

    private final String texto;

    private EspacamentoCabos(String sigla) {
        this.texto = sigla;
    }

    public String getNome() {
        return texto;
    }

    public static List<EspacamentoCabos> getLista() {
        List<EspacamentoCabos> lista = new ArrayList<>();
        lista.add(EspacamentoCabos.MaiorQDobroDoDiametro);
        lista.add(EspacamentoCabos.Nula);
        lista.add(EspacamentoCabos._0_125m);
        lista.add(EspacamentoCabos._0_25m);
        lista.add(EspacamentoCabos._0_50m);
        lista.add(EspacamentoCabos._1DiametroDoCabo);
        lista.add(EspacamentoCabos.NaoAplicavel);

        return lista;
    }
}
