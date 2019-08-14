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
    private double correnteAparente = 0;
    private double correnteReativa = 0;
    private double correnteAtivaDem = 0;
    private double correnteAparenteDem = 0;
    private double correnteReativaDem = 0;
    private double tensao = 0;
    private String LigacaoReal;
    private double potAtiva = 0;
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
     * @return the correnteAparente
     */
    public double getCorrenteAparente() {
        return correnteAparente;
    }

    /**
     * @param correnteAparente the correnteAparente to set
     */
    public void setCorrenteAparente(double correnteAparente) {
        this.correnteAparente = correnteAparente;
    }

    /**
     * @return the correnteReativa
     */
    public double getCorrenteReativa() {
        return correnteReativa;
    }

    /**
     * @param correnteReativa the correnteReativa to set
     */
    public void setCorrenteReativa(double correnteReativa) {
        this.correnteReativa = correnteReativa;
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
     * @return the correnteAparenteDem
     */
    public double getCorrenteAparenteDem() {
        return correnteAparenteDem;
    }

    /**
     * @param correnteAparenteDem the correnteAparenteDem to set
     */
    public void setCorrenteAparenteDem(double correnteAparenteDem) {
        this.correnteAparenteDem = correnteAparenteDem;
    }

    /**
     * @return the correnteReativaDem
     */
    public double getCorrenteReativaDem() {
        return correnteReativaDem;
    }

    /**
     * @param correnteReativaDem the correnteReativaDem to set
     */
    public void setCorrenteReativaDem(double correnteReativaDem) {
        this.correnteReativaDem = correnteReativaDem;
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