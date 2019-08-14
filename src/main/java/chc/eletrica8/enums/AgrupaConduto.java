/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public enum AgrupaConduto {
    Agrupa1("Em feixe ao ar livre ou sobre superficie em condutores fechados"),
    Agrupa2("Cama unica sobre parede, piso, ou em bandej nao perf ou prateleira"),
    Agrupa3("Camada unica no teto"),
    Agrupa4("Camada unica em bandeja perfurada"),
    Agrupa5("Camada unica em leito, suporte etc"),
    Agrupa6("Não se aplica");

    private final String texto;

    private AgrupaConduto(String sigla) {
        this.texto = sigla;
    }
    
    public String getNome() {
        return texto;
    }

    public static List<AgrupaConduto> getLista() {
        List<AgrupaConduto> lista = new ArrayList<>();
        lista.add(AgrupaConduto.Agrupa1);
        lista.add(AgrupaConduto.Agrupa2);
        lista.add(AgrupaConduto.Agrupa3);
        lista.add(AgrupaConduto.Agrupa4);
        lista.add(AgrupaConduto.Agrupa5);
        lista.add(AgrupaConduto.Agrupa6);

        return lista;
    }

}
