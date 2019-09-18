/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.enums;

import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.uteis.LerCSV;

/**
 *
 * @author chris
 */
public enum Tabelas {
    CorrecaoTempNE("CorrecaoTemperaturaNenterrado.csv"),
    CorrencaoTempE("CorrecaoTemperaturaEnterrado.csv"),
    CorrencaoResisTerm("CorrecaoResistividadeTermica.csv"),
    CorrecaoAgruT3_19("CorrecaoAgrupamentoT3_19.csv"),
    CorrecaoAgruT3_18("CorrecaoAgrupamentoT3_18.csv"),
    CorrecaoAgruT3_17("CorrecaoAgrupamentoT3_17.csv"),
    CorrecaoAgruT3_16("CorrecaoAgrupamentoT3_16.csv"),
    CorrecaoAgruT3_15("CorrecaoAgrupamentoT3_15.csv"),
    CapaciCorrBaixaCobre("CCBaixaCobre.csv"),
    CapaciCorrBaixaAluminio("CCBaixaAluminio.csv");

    private final String arquivo;

    private Tabelas(String sigla) {
        this.arquivo = sigla;
    }

    public String getNome() {
        return arquivo;
    }

    public String[][] getMatriz() {
        return new LerCSV(arquivo).toMatriz();
    }

}
