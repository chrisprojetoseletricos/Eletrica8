/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.Ligacao;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class FonteResultados implements Serializable, Entidade<FonteResultados> {

    private double correnteAtiva = 0;
    private double correnteAparente = 0;
    private double correnteReativa = 0;
    private double correnteAtivaDem = 0;
    private double correnteAparenteDem = 0;
    private double correnteReativaDem = 0;
    private double potAtivaKVA = 0;
    private double potAtivaDemKVA = 0;
    private Ligacao ligacao = Ligacao.FN;
    private double fp = 0;
    private double tensao = 0;


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
     * @return the potAtivaKVA
     */
    public double getPotAtivaKVA() {
        return potAtivaKVA;
    }

    /**
     * @param potAtivaKVA the potAtivaKVA to set
     */
    public void setPotAtivaKVA(double potAtivaKVA) {
        this.potAtivaKVA = potAtivaKVA;
    }

    /**
     * @return the potAtivaDemKVA
     */
    public double getPotAtivaDemKVA() {
        return potAtivaDemKVA;
    }

    /**
     * @param potAtivaDemKVA the potAtivaDemKVA to set
     */
    public void setPotAtivaDemKVA(double potAtivaDemKVA) {
        this.potAtivaDemKVA = potAtivaDemKVA;
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
    
    public void limpa() {

    }

    /**
     * @return the fp
     */
    public double getFp() {
        return fp;
    }

    /**
     * @param fp the fp to set
     */
    public void setFp(double fp) {
        this.fp = fp;
    }

    /**
     * @return the ligacao
     */
    public Ligacao getLigacao() {
        return ligacao;
    }

    /**
     * @param ligacao the ligacao to set
     */
    public void setLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
    }

    @Override
    public FonteResultados clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FonteResultados copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
