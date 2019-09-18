/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class QuadroResultados implements Serializable, Entidade<QuadroResultados> {

     private double correnteProjeto = 0;
    private double correnteCorr = 0;
    private double correnteAtiva = 0;
    private double correnteAtivaDem = 0;
    private double fase = 0;
    private double neutro = 0;
    private double terra = 0;
    private String bitola = "";
    private Ligacao ligacao = Ligacao.FN;
    private TiposFornecimento tipo = TiposFornecimento.MONOFASICO;
    private double fp = 0;
    private double tensao = 0;
    private double potAtiva = 0;
    private double potAtivaDem = 0;
    private double potReativa = 0;
    private double potReativaDem = 0;
    private double potAparente = 0;
    private double potAparenteDem = 0;
    private String disjuntorTM = "";
    private double fusivel = 0;


    public void limpa() {

        correnteCorr = 0;
        fase = 0;
        setNeutro(0);
        terra = 0;
        setBitola("");
    }

    public int numCirCarregados() {
        int num = 0;
        if (ligacao.getNumeroFases() == 1 || ligacao.getNumeroFases() == 2) {
            num = 2;
        } else {
            num = 3;
        }

        return num;
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

    /**
     * @return the bitola
     */
    public String getBitola() {
        return bitola;
    }

    /**
     * @param bitola the bitola to set
     */
    public void setBitola(String bitola) {
        this.bitola = bitola;
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
     * @return the correnteCorr
     */
    public double getCorrenteCorr() {
        return correnteCorr;
    }

    /**
     * @param correnteCorr the correnteCorr to set
     */
    public void setCorrenteCorr(double correnteCorr) {
        this.correnteCorr = correnteCorr;
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
     * @return the disjuntorTM
     */
    public String getDisjuntorTM() {
        return disjuntorTM;
    }

    /**
     * @param disjuntorTM the disjuntorTM to set
     */
    public void setDisjuntorTM(String disjuntorTM) {
        this.disjuntorTM = disjuntorTM;
    }

    /**
     * @return the fusivel
     */
    public double getFusivel() {
        return fusivel;
    }

    /**
     * @param fusivel the fusivel to set
     */
    public void setFusivel(double fusivel) {
        this.fusivel = fusivel;
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
     * @return the tipo
     */
    public TiposFornecimento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TiposFornecimento tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the neutro
     */
    public double getNeutro() {
        return neutro;
    }

    /**
     * @param neutro the neutro to set
     */
    public void setNeutro(double neutro) {
        this.neutro = neutro;
    }

    /**
     * @return the terra
     */
    public double getTerra() {
        return terra;
    }

    /**
     * @param terra the terra to set
     */
    public void setTerra(double terra) {
        this.terra = terra;
    }

    /**
     * @return the bitola
     */
    public double getFase() {
        return fase;
    }

    /**
     * @param fase the bitola to set
     */
    public void setFase(double fase) {
        this.fase = fase;
    }


    @Override
    public QuadroResultados clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuadroResultados copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @return the correnteProjeto
     */
    public double getCorrenteProjeto() {
        return correnteProjeto;
    }

    /**
     * @param correnteProjeto the correnteProjeto to set
     */
    public void setCorrenteProjeto(double correnteProjeto) {
        this.correnteProjeto = correnteProjeto;
    }

}
