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
public enum Enterrado {
    Nao("Não"),
    SimCDuto("Sim com duto"),
    SimSDuto("Sim sem duto");

    private final String nome;

    private Enterrado(String sigla) {
        this.nome = sigla;
    }

    public String getNome() {
        return nome;
    }

    public static List<Enterrado> getLista() {
        List<Enterrado> lista = new ArrayList<>();
        lista.add(Enterrado.Nao);
        lista.add(Enterrado.SimCDuto);
        lista.add(Enterrado.SimSDuto);

        return lista;
    }
}
