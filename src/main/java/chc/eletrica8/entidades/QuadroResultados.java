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

    private double correnteIB = 0;
    private double correnteCorr = 0;
    private double fase = 0;
    private double neutro = 0;
    private double terra = 0;
    private String bitola = "";
    private Ligacao ligacao = Ligacao.FN;
    private TiposFornecimento tipo = TiposFornecimento.MONOFASICO;

    public void limpa() {
        correnteIB = 0;
        correnteCorr = 0;
        fase = 0;
        neutro = 0;
        terra = 0;
        setBitola("");
    }
    
    public int numCirCarregados(){
        int num = 0;
        if(ligacao.getNumeroFases() == 1 || ligacao.getNumeroFases() == 2){
            num = 2;
        }else{
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
     * @return the correnteIB
     */
    public double getCorrenteIB() {
        return correnteIB;
    }

    /**
     * @param correnteIB the correnteIB to set
     */
    public void setCorrenteIB(double correnteIB) {
        this.correnteIB = correnteIB;
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

}
