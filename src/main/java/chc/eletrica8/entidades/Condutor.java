/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.EspacamentoCabos;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.TempAmbiente;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



/**
 *
 * @author chris
 */
@Entity
public class Condutor implements Serializable, Entidade<Condutor> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
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

    public String getBitolaSucessiva() {
        return bitolaSucessiva;
    }

    public void setBitolaSucessiva(String bitolaSucessiva) {
        this.bitolaSucessiva = bitolaSucessiva;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condutor)) {
            return false;
        }
        Condutor other = (Condutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id="+id;
    }

    @Override
    public Condutor clonarSemID() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Condutor copiar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void apagar() {
        id = 0;
        bitolaSucessiva = "";
        enterrado = "";
        espacoCabos = null;
        formaAgrupa = "";
        isolacao = "";
        material = "";
        modoInstalacao = null;
        multipolar = "";
        nCamadas = 0;
        nCirAgrupa = 0;
        quedaTensao = 0;
        resistiTermica = 0;
        temperatura = null;
        comprimento = 0;
    }
}
