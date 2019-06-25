package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum EspacamentoCabos {
    teste1,
    teste2;

    public static List<EspacamentoCabos> getLista() {
        List<EspacamentoCabos> lista = new ArrayList<>();
        lista.add(EspacamentoCabos.teste1);
        lista.add(EspacamentoCabos.teste2);
        return lista;
    }
}
