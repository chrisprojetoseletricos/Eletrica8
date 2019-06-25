/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.DiametroPolegadas;
import chc.eletrica8.enums.DisjuntorTermoMag;
import chc.eletrica8.enums.MetodoCalculo;
import chc.eletrica8.enums.TiposCondutores;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author chris
 */
@Entity
@Table(name = "Concessionaria")
@TableModel
public class Concessionaria implements Serializable, Entidade<Concessionaria> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    private String localizacao;
    @Column(colName = "Tensão", colPosition = 1)
    private double tensaoFN;
    @Column(colName = "Método", colPosition = 2)
    @Enumerated(EnumType.STRING)
    private MetodoCalculo metodoCalculo;
    @Column(colName = "Rede", colPosition = 3)
    @Enumerated(EnumType.STRING)
    private TiposFornecimento tiposFornecimento;
    @Column(colName = "Carga", colPosition = 4)
    private String carga;
    @Column(colName = "DisjTM", colPosition = 5)
    @Enumerated(EnumType.STRING)
    private DisjuntorTermoMag disjuntorTermoMag;
    @Column(colName = "Condutor", colPosition = 6)
    @Enumerated(EnumType.STRING)
    private TiposCondutores tipoCondutor;
    @Column(colName = "Fase mm²", colPosition = 7)
    @Enumerated(EnumType.STRING)
    private BitolasMili faseMinimaCobreIsolado;
    @Column(colName = "Neutro mm²", colPosition = 8)
    @Enumerated(EnumType.STRING)
    private BitolasMili neutroMinimoCobreIsolado;
    @Column(colName = "Neutro mm²", colPosition = 11)
    @Enumerated(EnumType.STRING)
    private BitolasMili condutorAterramentoAco;
    @Column(colName = "DiamEletro", colPosition = 9)
    @Enumerated(EnumType.STRING)
    private DiametroPolegadas diametroEletroAcoGalva;
    @Column(colName = "DiamEletroEnt", colPosition = 10)
    @Enumerated(EnumType.STRING)
    private DiametroPolegadas diametroEletroAterramento;
    @ElementCollection
    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
    private RamalLigacao ramalLigacao;

    public RamalLigacao getRamalLigacao() {
        return ramalLigacao;
    }

    public void setRamalLigacao(RamalLigacao ramalLigacao) {
        this.ramalLigacao = ramalLigacao;
    }
    
    

    public BitolasMili getCondutorAterramentoAco() {
        return condutorAterramentoAco;
    }

    public void setCondutorAterramentoAco(BitolasMili condutorAterramentoAco) {
        this.condutorAterramentoAco = condutorAterramentoAco;
    }

    
    
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getTensaoFN() {
        return tensaoFN;
    }

    public void setTensaoFN(double tensaoFN) {
        this.tensaoFN = tensaoFN;
    }

    public MetodoCalculo getMetodoCalculo() {
        return metodoCalculo;
    }

    public void setMetodoCalculo(MetodoCalculo metodoCalculo) {
        this.metodoCalculo = metodoCalculo;
    }

    public TiposFornecimento getTiposFornecimento() {
        return tiposFornecimento;
    }

    public void setTiposFornecimento(TiposFornecimento tiposFornecimento) {
        this.tiposFornecimento = tiposFornecimento;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public DisjuntorTermoMag getDisjuntorTermoMag() {
        return disjuntorTermoMag;
    }

    public void setDisjuntorTermoMag(DisjuntorTermoMag disjuntorTermoMag) {
        this.disjuntorTermoMag = disjuntorTermoMag;
    }

    public TiposCondutores getTipoCondutor() {
        return tipoCondutor;
    }

    public void setTipoCondutor(TiposCondutores tipoCondutor) {
        this.tipoCondutor = tipoCondutor;
    }

    public BitolasMili getFaseMinimaCobreIsolado() {
        return faseMinimaCobreIsolado;
    }

    public void setFaseMinimaCobreIsolado(BitolasMili faseMinimaCobreIsolado) {
        this.faseMinimaCobreIsolado = faseMinimaCobreIsolado;
    }

    public BitolasMili getNeutroMinimoCobreIsolado() {
        return neutroMinimoCobreIsolado;
    }

    public void setNeutroMinimoCobreIsolado(BitolasMili neutroMinimoCobreIsolado) {
        this.neutroMinimoCobreIsolado = neutroMinimoCobreIsolado;
    }

    public DiametroPolegadas getDiametroEletroAcoGalva() {
        return diametroEletroAcoGalva;
    }

    public void setDiametroEletroAcoGalva(DiametroPolegadas diametroEletroAcoGalva) {
        this.diametroEletroAcoGalva = diametroEletroAcoGalva;
    }

    public DiametroPolegadas getDiametroEletroAterramento() {
        return diametroEletroAterramento;
    }

    public void setDiametroEletroAterramento(DiametroPolegadas diametroEletroAterramento) {
        this.diametroEletroAterramento = diametroEletroAterramento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof Concessionaria)) {
            return false;
        }
        Concessionaria other = (Concessionaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public Concessionaria clonarSemID() {
        Concessionaria p = copiar();
        p.setId(null);
        return p;
    }

    @Override
    public Concessionaria copiar() {
        Concessionaria c = new Concessionaria();
        c.setId(id);
        c.setNome(nome);
        c.setLocalizacao(localizacao);
        c.setTensaoFN(tensaoFN);
        c.setMetodoCalculo(metodoCalculo);
        c.setTiposFornecimento(tiposFornecimento);
        c.setCarga(carga);
        c.setDisjuntorTermoMag(disjuntorTermoMag);
        c.setTipoCondutor(tipoCondutor);
        c.setFaseMinimaCobreIsolado(faseMinimaCobreIsolado);
        c.setNeutroMinimoCobreIsolado(neutroMinimoCobreIsolado);
        c.setDiametroEletroAcoGalva(diametroEletroAcoGalva);
        c.setDiametroEletroAterramento(diametroEletroAterramento);
        c.setRamalLigacao(ramalLigacao);
        c.setCondutorAterramentoAco(condutorAterramentoAco);
        return c;
    }

    @Override
    public void apagar() {

        id = null;
        nome = "";
        localizacao = "";
        tensaoFN = 0;
        metodoCalculo = null;
        tiposFornecimento = null;
        carga = null;
        disjuntorTermoMag = null;
        tipoCondutor = null;
        faseMinimaCobreIsolado = null;
        neutroMinimoCobreIsolado = null;
        diametroEletroAcoGalva = null;
        diametroEletroAterramento = null;
        ramalLigacao = null;
        condutorAterramentoAco = null;
    }
}
