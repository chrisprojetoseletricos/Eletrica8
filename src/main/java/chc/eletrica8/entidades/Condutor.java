/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.AgrupaConduto;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.EspacamentoCabos;
import chc.eletrica8.enums.EspacamentoEletrodutos;
import chc.eletrica8.enums.Instalacao;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class Condutor implements Serializable, Entidade<Condutor> {

    private String bitolaSucessiva;
    private Enterrado enterrado;
    private EspacamentoCabos espacoCabos;
    private EspacamentoEletrodutos espacoEletrodutos;
    private AgrupaConduto formaAgrupa;
    private String isolacao;
    private String material;
    private Instalacao modoInstalacao;
    private String multipolar;
    private int nCamadas;
    private int nCirAgrupa;
    private double quedaTensao = 2;
    private double resistiTermica;
    private int temperatura = 30;
    private double comprim;

    public String getBitolaSucessiva() {
        return bitolaSucessiva;
    }

    public void setBitolaSucessiva(String bitolaSucessiva) {
        this.bitolaSucessiva = bitolaSucessiva;
    }

    /**
     * @return the comprimento
     */
    public double getComprimento() {
        return comprim;
    }

    /**
     * @param comprimento the comprimento to set
     */
    public void setComprimento(double comprimento) {
        this.comprim = comprimento;
    }

    public Enterrado getEnterrado() {
        return enterrado;
    }

    public void setEnterrado(Enterrado enterrado) {
        this.enterrado = enterrado;
    }

    /**
     * @return the espacoEletrodutos
     */
    public EspacamentoEletrodutos getEspacoEletrodutos() {
        return espacoEletrodutos;
    }

    /**
     * @param espacoEletrodutos the espacoEletrodutos to set
     */
    public void setEspacoEletrodutos(EspacamentoEletrodutos espacoEletrodutos) {
        this.espacoEletrodutos = espacoEletrodutos;
    }

    public EspacamentoCabos getEspacoCabos() {
        return espacoCabos;
    }

    public void setEspacoCabos(EspacamentoCabos espacoCabos) {
        this.espacoCabos = espacoCabos;
    }

    public AgrupaConduto getFormaAgrupa() {
        return formaAgrupa;
    }

    public void setFormaAgrupa(AgrupaConduto formaAgrupa) {
        this.formaAgrupa = formaAgrupa;
    }

    public String getIsolacao() {
        return isolacao;
    }

    public void setIsolacao(String isolacao) {
        this.isolacao = isolacao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Instalacao getModoInstalacao() {
        return modoInstalacao;
    }

    public void setModoInstalacao(Instalacao modoInstalacao) {
        this.modoInstalacao = modoInstalacao;
    }

    public String getMultipolar() {
        return multipolar;
    }

    public void setMultipolar(String multipolar) {
        this.multipolar = multipolar;
    }

    public int getnCamadas() {
        return nCamadas;
    }

    public void setnCamadas(int nCamadas) {
        this.nCamadas = nCamadas;
    }

    public int getnCirAgrupa() {
        return nCirAgrupa;
    }

    public void setnCirAgrupa(int nCirAgrupa) {
        this.nCirAgrupa = nCirAgrupa;
    }

    public double getQuedaTensao() {
        return quedaTensao;
    }

    public void setQuedaTensao(double quedaTensao) {
        this.quedaTensao = quedaTensao;
    }

    public double getResistiTermica() {
        return resistiTermica;
    }

    public void setResistiTermica(double resistiTermica) {
        this.resistiTermica = resistiTermica;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public Condutor clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condutor copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
