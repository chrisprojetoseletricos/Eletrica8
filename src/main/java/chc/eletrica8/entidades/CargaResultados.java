/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class CargaResultados implements Serializable, Entidade<CargaResultados> {

    private double corrente = 0;
    private double tensao = 0;

    
    public void limpa(){
        corrente = 0;
        tensao = 0;
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

    /**
     * @return the tensao
     */
    public double getTensao() {
        return tensao;
    }

    /**
     * @param tensao the tensao to set
     */
    public void setTensao(double tensao) {
        this.tensao = tensao;
    }

    @Override
    public CargaResultados clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargaResultados copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
