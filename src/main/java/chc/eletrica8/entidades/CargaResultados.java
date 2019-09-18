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

    private double correnteAtiva = 0;
    private double correnteAtivaDem = 0;
    private double tensao = 0;
    private String LigacaoReal;
    private double potAtiva = 0;
    private double potReativa = 0;
    private double potAparente = 0;
    private double potReativaDem = 0;
    private double potAparenteDem = 0;
    private double potAtivaDem = 0;

    public void limpa() {

        tensao = 0;
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

    /**
     * @return the correnteAtiva
     */
    public double getCorrenteAtiva() {
        return correnteAtiva;
    }

    /**
     * @param correnteAtiva the correnteAtiva to set
     */
    public void setCorrenteAtiva(double correnteAtiva) {
        this.correnteAtiva = correnteAtiva;
    }


    /**
     * @return the correnteAtivaDem
     */
    public double getCorrenteAtivaDem() {
        return correnteAtivaDem;
    }

    /**
     * @param correnteAtivaDem the correnteAtivaDem to set
     */
    public void setCorrenteAtivaDem(double correnteAtivaDem) {
        this.correnteAtivaDem = correnteAtivaDem;
    }
    /**
     * @return the potAtiva
     */
    public double getPotAtiva() {
        return potAtiva;
    }

    /**
     * @param potAtiva the potAtiva to set
     */
    public void setPotAtiva(double potAtiva) {
        this.potAtiva = potAtiva;
    }

    /**
     * @return the potAtivaDem
     */
    public double getPotAtivaDem() {
        return potAtivaDem;
    }

    /**
     * @param potAtivaDem the potAtivaDem to set
     */
    public void setPotAtivaDem(double potAtivaDem) {
        this.potAtivaDem = potAtivaDem;
    }

    /**
     * @return the potReativa
     */
    public double getPotReativa() {
        return potReativa;
    }

    /**
     * @param potReativa the potReativa to set
     */
    public void setPotReativa(double potReativa) {
        this.potReativa = potReativa;
    }

    /**
     * @return the potAparente
     */
    public double getPotAparente() {
        return potAparente;
    }

    /**
     * @param potAparente the potAparente to set
     */
    public void setPotAparente(double potAparente) {
        this.potAparente = potAparente;
    }

    /**
     * @return the potReativaDem
     */
    public double getPotReativaDem() {
        return potReativaDem;
    }

    /**
     * @param potReativaDem the potReativaDem to set
     */
    public void setPotReativaDem(double potReativaDem) {
        this.potReativaDem = potReativaDem;
    }

    /**
     * @return the potAparenteDem
     */
    public double getPotAparenteDem() {
        return potAparenteDem;
    }

    /**
     * @param potAparenteDem the potAparenteDem to set
     */
    public void setPotAparenteDem(double potAparenteDem) {
        this.potAparenteDem = potAparenteDem;
    }

    /**
     * @return the LigacaoReal
     */
    public String getLigacaoReal() {
        return LigacaoReal;
    }

    /**
     * @param LigacaoReal the LigacaoReal to set
     */
    public void setLigacaoReal(String LigacaoReal) {
        this.LigacaoReal = LigacaoReal;
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
