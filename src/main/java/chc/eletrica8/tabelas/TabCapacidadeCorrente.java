/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.tabelas;

import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.servico.tableModel.TableModel;
import javax.persistence.Table;

/**
 *
 * @author chris
 */

@Table(name = "Tabela Capacidade Corrente")
@TableModel
public class TabCapacidadeCorrente {
    
    private Instalacao instalacao;
    private String isolacao;
    private int condutores;
    private BitolasMili bitola;
    private double corrente;
    
    public TabCapacidadeCorrente(String arquivo){
        
    }

    /**
     * @return the instalacao
     */
    public Instalacao getInstalacao() {
        return instalacao;
    }

    /**
     * @param instalacao the instalacao to set
     */
    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    /**
     * @return the isolacao
     */
    public String getIsolacao() {
        return isolacao;
    }

    /**
     * @param isolacao the isolacao to set
     */
    public void setIsolacao(String isolacao) {
        this.isolacao = isolacao;
    }

    /**
     * @return the condutores
     */
    public int getCondutores() {
        return condutores;
    }

    /**
     * @param condutores the condutores to set
     */
    public void setCondutores(int condutores) {
        this.condutores = condutores;
    }

    /**
     * @return the bitola
     */
    public BitolasMili getBitola() {
        return bitola;
    }

    /**
     * @param bitola the bitola to set
     */
    public void setBitola(BitolasMili bitola) {
        this.bitola = bitola;
    }

    /**
     * @return the corrente
     */
    public double getCorrente() {
        return corrente;
    }

    /**
     * @param corrente the corrente to set
     */
    public void setCorrente(double corrente) {
        this.corrente = corrente;
    }
   
}
