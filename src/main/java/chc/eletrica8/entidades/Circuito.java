/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;


import chc.eletrica8.calculos.CorrenteIB;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Circuito")
@TableModel
@SuppressWarnings("serial")
public class Circuito implements Serializable, Entidade<Circuito> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private QuadroParcial quadroParcial;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private QuadroFinal quadroFinal;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private QuadroGeral quadroGeral;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Column(colName = "Condutor", colPosition = 1)
    private Condutor condutor;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Column(colName = "Dados CC", colPosition = 2)
    private Curto curto;
    @OneToMany(mappedBy = "circuito", targetEntity = Carga.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carga> listaCarga = new ArrayList<>();
    @Column(colName = "Circuito", colPosition = 0)
    private String nome;

    public double getCorrenteIB() {
        double correnteIB ;
        correnteIB = new CorrenteIB()//
                .withCarga(listaCarga)//
                .valor();
        return correnteIB;
    }

    /**
     * @return the quadroParcial
     */
    public QuadroParcial getQuadroParcial() {
        return quadroParcial;
    }

    /**
     * @param quadroParcial the quadroParcial to set
     */
    public void setQuadroParcial(QuadroParcial quadroParcial) {
        this.quadroParcial = quadroParcial;
    }

    /**
     * @return the quadroFinal
     */
    public QuadroFinal getQuadroFinal() {
        return quadroFinal;
    }

    /**
     * @param quadroFinal the quadroFinal to set
     */
    public void setQuadroFinal(QuadroFinal quadroFinal) {
        this.quadroFinal = quadroFinal;
    }

    /**
     * @return the quadroGeral
     */
    public QuadroGeral getQuadroGeral() {
        return quadroGeral;
    }

    /**
     * @param quadroGeral the quadroGeral to set
     */
    public void setQuadroGeral(QuadroGeral quadroGeral) {
        this.quadroGeral = quadroGeral;
    }

    public List<Carga> getListaCarga() {
        return listaCarga;

    }

    public void excluiCarga(Carga carga) {

        List<Carga> lista = getListaCarga();
        if (lista.contains(carga)) {
            lista.remove(carga);
        }
        listaCarga = lista;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Curto getCurto() {
        return curto;
    }

    public void setCurto(Curto curto) {
        this.curto = curto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
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
        if (!(object instanceof Circuito)) {
            return false;
        }
        Circuito other = (Circuito) object;
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
    public Circuito clonarSemID() {
        Circuito c = copiar();
        c.setId(null);
        return c;
    }

    @Override
    public Circuito copiar() {
        Circuito c = new Circuito();
        c.setId(id);
        c.setNome(nome);
        c.setQuadroParcial(getQuadroParcial());
        c.setQuadroFinal(getQuadroFinal());
        c.setQuadroGeral(getQuadroGeral());
        c.setCondutor(condutor);
        c.setCurto(curto);
        return c;
    }

    @Override
    public void apagar() {
        id = 0;
        quadroFinal = null;
        quadroGeral = null;
        quadroParcial = null;
        condutor = null;
        curto = null;
        listaCarga.clear();
        nome = "";
    }
}
