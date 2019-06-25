package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum DiametroPolegadas {

    P1_2("1/2'"),
    P1("1'"),
    P1_1_2("1 1/2'"),
    P2("2'"),
    P3("3'");
    

    private final String numero;

    private DiametroPolegadas(String sigla) {
        this.numero = sigla;
    }

    public String getString() {
        return numero;
    }

    public static List<DiametroPolegadas> getLista() {
        List<DiametroPolegadas> lista = new ArrayList<>();
        lista.add(DiametroPolegadas.P1_2);
        lista.add(DiametroPolegadas.P1);
        lista.add(DiametroPolegadas.P1_1_2);
        lista.add(DiametroPolegadas.P2);
        lista.add(DiametroPolegadas.P3);
        return lista;
    }
}
