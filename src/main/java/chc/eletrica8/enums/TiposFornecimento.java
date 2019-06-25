package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum TiposFornecimento {
    MONOFASICO,
    BIFASICO,
    TRIFASICO;


    public static List<TiposFornecimento> getLista() {
        List<TiposFornecimento> lista = new ArrayList<>();
        lista.add(TiposFornecimento.MONOFASICO);
        lista.add(TiposFornecimento.BIFASICO);
        lista.add(TiposFornecimento.TRIFASICO);
        return lista;
    }
}
