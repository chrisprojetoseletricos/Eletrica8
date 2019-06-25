package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum MetodoCalculo {
    CARGA_INSTALADA,
    DEMANDA;


    public static List<MetodoCalculo> getLista() {
        List<MetodoCalculo> lista = new ArrayList<>();
        lista.add(MetodoCalculo.CARGA_INSTALADA);
        lista.add(MetodoCalculo.DEMANDA);
        return lista;
    }
}
