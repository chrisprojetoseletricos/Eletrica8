package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum EspacamentoEletrodutos {
    Nula("Nula"),
    _0_25m("0,25 m"),
    _0_50m("0,50 m"),
    _1_0m("1,0 m"),
    MaiorQ1m("Maior que 1 m"),
    NaoAplicavel("Não se aplica");

    private final String texto;

    private EspacamentoEletrodutos(String sigla) {
        this.texto = sigla;
    }

    public String getNome() {
        return texto;
    }

    public static List<EspacamentoEletrodutos> getLista() {
        List<EspacamentoEletrodutos> lista = new ArrayList<>();
        lista.add(EspacamentoEletrodutos.MaiorQ1m);
        lista.add(EspacamentoEletrodutos.Nula);
        lista.add(EspacamentoEletrodutos._0_25m);
        lista.add(EspacamentoEletrodutos._0_50m);
        lista.add(EspacamentoEletrodutos._1_0m);
        lista.add(EspacamentoEletrodutos.NaoAplicavel);

        return lista;
    }
}
