package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum TiposCondutores {
    CABO_DE_COBRE_CONCENTRICO_OU_DUPLEX("Cabo de cobre concêntrico ou duplex"),
    CABO_DE_COBRE_MULTIPLEXADO("Cabo de cobre multiplexado"),
    CABO_DE_ALUMINIO_MULTIPLEXADO_CONCENTRICO_OU_DUPLEX("Cabo de alumínio multiplexado concêntrico ou duplex"),
    CABO_DE_ALUMINIO_MULTIPLEXADO_TRIPLEX("Cabo de alumínio multiplexado triplex"),
    CABO_DE_ALUMINIO_MULTIPLEXADO_QUADRUPLEX("Cabo de alumínio multiplexado quadruplex"),
    CONDUTOR_COBRE_ISOLADO("Condutor de cobre isolado");

    private final String texto;

    private TiposCondutores(String sigla) {
        this.texto = sigla;
    }

    public String getTexto() {
        return texto;
    }

    public static List<TiposCondutores> getLista() {
        List<TiposCondutores> lista = new ArrayList<>();
        lista.add(TiposCondutores.CONDUTOR_COBRE_ISOLADO);
        lista.add(TiposCondutores.CABO_DE_COBRE_CONCENTRICO_OU_DUPLEX);
        lista.add(TiposCondutores.CABO_DE_COBRE_MULTIPLEXADO);
        lista.add(TiposCondutores.CABO_DE_ALUMINIO_MULTIPLEXADO_CONCENTRICO_OU_DUPLEX);
        lista.add(TiposCondutores.CABO_DE_ALUMINIO_MULTIPLEXADO_QUADRUPLEX);
        lista.add(TiposCondutores.CABO_DE_ALUMINIO_MULTIPLEXADO_TRIPLEX);

        return lista;
    }
}
