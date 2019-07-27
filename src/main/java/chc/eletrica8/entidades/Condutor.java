/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.EspacamentoCabos;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TempAmbiente;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class Condutor implements Serializable, Entidade<Condutor> {

    private String bitolaSucessiva;
    private String enterrado;
    private EspacamentoCabos espacoCabos;
    private String formaAgrupa;
    private String isolacao;
    private String material;
    private Instalacao modoInstalacao;
    private String multipolar;
    private int nCamadas;
    private int nCirAgrupa;
    private double quedaTensao;
    private double resistiTermica;
    private TempAmbiente temperatura;
    private double comprimento;
    private BitolasMili bitola;
    private Ligacao ligacao;
    


    public String getBitolaSucessiva() {
        return bitolaSucessiva;
    }

    public void setBitolaSucessiva(String bitolaSucessiva) {
        this.bitolaSucessiva = bitolaSucessiva;
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
    public BitolasMili getBitola() {
        return bitola;
    }

    /**
     * @param bitola the bitola to set
     */
    public void setBitola(BitolasMili bitola) {
        this.bitola = bitola;
    }

    public String getEnterrado() {
        return enterrado;
    }

    public void setEnterrado(String enterrado) {
        this.enterrado = enterrado;
    }

    public EspacamentoCabos getEspacoCabos() {
        return espacoCabos;
    }

    public void setEspacoCabos(EspacamentoCabos espacoCabos) {
        this.espacoCabos = espacoCabos;
    }

    public String getFormaAgrupa() {
        return formaAgrupa;
    }

    public void setFormaAgrupa(String formaAgrupa) {
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

    public TempAmbiente getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(TempAmbiente temperatura) {
        this.temperatura = temperatura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
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
