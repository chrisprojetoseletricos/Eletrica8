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
    private Quadro quadro;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Column(colName = "Condutor", colPosition = 1)
    private Condutor condutor;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Column(colName = "Dados CC", colPosition = 2)
    private Curto curto;
    @OneToMany(mappedBy = "circuito", targetEntity = Carga.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carga> cargas = new ArrayList<>();
    @Column(colName = "Circuito", colPosition = 0)
    private String nome;

    public double getCorrenteIB() {
        double correnteIB ;
        correnteIB = new CorrenteIB()//
                .withCarga(cargas)//
                .valor();
        return correnteIB;
    }

    /**
     * @return the quadroParcial
     */
    public Quadro getQuadro() {
        return quadro;
    }

    /**
     * @param quadro the quadroParcial to set
     */
    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }


    public List<Carga> getListaCarga() {
        return cargas;

    }

    /**
     * @param listaCarga the listaCarga to set
     */
    public void setListaCarga(List<Carga> listaCarga) {
        this.cargas = listaCarga;
    }

    public void excluiCarga(Carga carga) {

        List<Carga> lista = getListaCarga();
        if (lista.contains(carga)) {
            lista.remove(carga);
        }
        setListaCarga(lista);
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
        c.setQuadro(quadro);
        c.setCondutor(condutor);
        c.setCurto(curto);
        
        List<Carga> lista = new ArrayList<>();
        for(int i = 0;i<cargas.size();i++){
            Carga ca = new Carga();
            ca = cargas.get(i).clonarSemID();
            ca.setCircuito(c);
            lista.add(ca);
        }
        c.setListaCarga(lista);
        return c;
    }

    @Override
    public void apagar() {
        id = 0;
        quadro = null;
        condutor = null;
        curto = null;
        cargas.clear();
        nome = "";
    }
}
