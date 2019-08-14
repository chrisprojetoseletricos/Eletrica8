/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.uteis;

import chc.eletrica8.enums.Tabelas;

/**
 *
 * @author chris
 */
public class Tabela {

    public static String nome(Tabelas nome) {
        String arquivo = "";

        switch (nome) {
            case CorrecaoTempNE:
                arquivo = nome.getNome();
                break;
            case CorrencaoTempE:
                arquivo = nome.getNome();
                break;
            case CapaciCorrBaixaAluminio:
                arquivo = nome.getNome();
                break;
            case CapaciCorrBaixaCobre:
                arquivo = nome.getNome();
                break;
            case CorrecaoAgruT3_15:
                arquivo = nome.getNome();
                break;
            case CorrecaoAgruT3_16:
                arquivo = nome.getNome();
                break;
            case CorrecaoAgruT3_17:
                arquivo = nome.getNome();
                break;
            case CorrecaoAgruT3_18:
                arquivo = nome.getNome();
                break;
            case CorrecaoAgruT3_19:
                arquivo = nome.getNome();
                break;
            case CorrencaoResisTerm:
                arquivo = nome.getNome();
                break;
            default:
                break;
        }
        return arquivo;
    }
}
