package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum Usabilidade {
    EQUIPAMENTOS_ESPECIAIS,
    TOMADA_USO_GERAL,
    ILUMINACAO_INCADESCENTE,
    ILUMINACAO_FLUORESCENTE,
    ILUMINACAO_FLUORESCENTE_PERDAS,
    MOTOR,
    GERAL;

    public static List<Usabilidade> getLista() {
        List<Usabilidade> lista = new ArrayList<>();
        lista.add(Usabilidade.EQUIPAMENTOS_ESPECIAIS);
        lista.add(Usabilidade.GERAL);
        lista.add(Usabilidade.ILUMINACAO_FLUORESCENTE);
        lista.add(Usabilidade.ILUMINACAO_FLUORESCENTE_PERDAS);
        lista.add(Usabilidade.ILUMINACAO_INCADESCENTE);
        lista.add(Usabilidade.MOTOR);
        lista.add(Usabilidade.TOMADA_USO_GERAL);
        return lista;
    }
}
