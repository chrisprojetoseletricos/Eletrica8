/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.Tabelas;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_15;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_16;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_17;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_18;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_19;
import chc.eletrica8.uteis.LerCSV;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;

/**
 *
 * @author chris
 */
public class Fator {

    Condutor condutor;

    public Fator(Condutor condutor) {

        this.condutor = condutor;
    }

    public double FCT() {

        // double correnteIB = resultados.getCorrenteAtivaDem();
        double fatorTemp = Numero.stringToDouble(//
                Matriz.pegarValorMatrizEspecial(new LerCSV(tabelaCorrecaoTemp(condutor).getNome()).toMatriz(),//
                        Integer.toString(condutor.getTemperatura()),//
                        condutor.getIsolacao()), 0);
        return fatorTemp;
    }

    public double FCT(int tempQuadro) {

        // double correnteIB = resultados.getCorrenteAtivaDem();
        double fatorTemp = Numero.stringToDouble(//
                Matriz.pegarValorMatrizEspecial(new LerCSV(tabelaCorrecaoTemp(condutor).getNome()).toMatriz(),//
                        Integer.toString(tempQuadro),//
                        condutor.getIsolacao()), 0);
        return fatorTemp;
    }

    public double FCA() {
        double fatorAgrupa = 0;

        switch (tabelaCorrecaoAgrupa(condutor)) {
            case CorrecaoAgruT3_15:
                fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_15.getNome()).toMatriz(),//
                        condutor.getFormaAgrupa().getNome(),//
                        Integer.toString(condutor.getnCirAgrupa())), 0);
                break;
            case CorrecaoAgruT3_16:
                fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_16.getNome()).toMatriz(),//
                        Integer.toString(condutor.getnCamadas()),//
                        Integer.toString(condutor.getnCirAgrupa())), 0);
                break;
            case CorrecaoAgruT3_17:
                fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_17.getNome()).toMatriz(),//
                        Integer.toString(condutor.getnCirAgrupa()),//
                        condutor.getEspacoCabos().getNome()), 0);
                break;
            case CorrecaoAgruT3_18:
                fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_18.getNome()).toMatriz(),//
                        Integer.toString(condutor.getnCirAgrupa()),//
                        condutor.getEspacoEletrodutos().getNome()), 0);
                break;
            case CorrecaoAgruT3_19:
                fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_19.getNome()).toMatriz(),//
                        Integer.toString(condutor.getnCirAgrupa()),//
                        condutor.getEspacoEletrodutos().getNome()), 0);
                break;
            default:
                break;

        }
        return fatorAgrupa;
    }

    private Tabelas tabelaCorrecaoTemp(Condutor condutor) {
        Tabelas tab;
        if (condutor.getEnterrado().equals("Sim")) {
            tab = Tabelas.CorrencaoTempE;
        } else {
            tab = Tabelas.CorrecaoTempNE;
        }
        return tab;
    }

    private Tabelas tabelaCorrecaoAgrupa(Condutor condutor) {
        Tabelas tab = null;
        if (condutor.getnCamadas() > 1 && condutor.getEnterrado().equals(Enterrado.Nao)) {
            tab = Tabelas.CorrecaoAgruT3_16;
        } else if (condutor.getnCamadas() <= 1 && condutor.getEnterrado().equals(Enterrado.Nao)) {
            tab = Tabelas.CorrecaoAgruT3_15;
        } else if (condutor.getEnterrado().equals(Enterrado.SimSDuto)) {
            tab = Tabelas.CorrecaoAgruT3_17;
        } else if (condutor.getEnterrado().equals(Enterrado.SimCDuto) && condutor.getMultipolar().equals("Sim")) {
            tab = Tabelas.CorrecaoAgruT3_18;
        } else if (condutor.getEnterrado().equals(Enterrado.SimCDuto) && condutor.getMultipolar().equals("Não")) {
            tab = Tabelas.CorrecaoAgruT3_19;
        }
        return tab;
    }
}
