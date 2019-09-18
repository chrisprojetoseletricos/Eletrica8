package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum TiposFornecimento {
    MONOFASICO("Monofásico"),
    BIFASICO("Bifásico"),
    TRIFASICO("Trifásico");

    private final String texto;

    private TiposFornecimento(String sigla) {
        this.texto = sigla;
    }

    public String getTexto() {
        return texto;
    }

    public static List<TiposFornecimento> getLista() {
        List<TiposFornecimento> lista = new ArrayList<>();
        lista.add(TiposFornecimento.MONOFASICO);
        lista.add(TiposFornecimento.BIFASICO);
        lista.add(TiposFornecimento.TRIFASICO);
        return lista;
    }

    public static TiposFornecimento getTipo(Ligacao ligacao) {
        TiposFornecimento tipo = MONOFASICO;
        switch (ligacao) {
            case FFF:
            case FFFN:
                tipo = TRIFASICO;
                break;
            case FF:
            case FFN:
                tipo = BIFASICO;
                break;
            case FN:
                tipo = MONOFASICO;
                break;
            default:
                break;
        }
        return tipo;
    }
}
