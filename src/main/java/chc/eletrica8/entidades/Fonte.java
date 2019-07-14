/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.UnidadePotencia;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Fonte")
@TableModel
public class Fonte implements Serializable, Entidade<Fonte> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Projeto projeto;
    @Column(colName = "Concessionária", colPosition = 2)
    private String concessionaria;
    @OneToMany(mappedBy = "fonte", targetEntity = Quadro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quadro> quadros = new ArrayList<>();
    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    @Column(colName = "TensãoFN", colPosition = 1)
    private double tensaoFN;
    @Lob
    private String descricao;

    public void Fonte() {
    }

    public double getPotenciaInstalada(UnidadePotencia unidadeDestino) {
        double total = 0;
        for (Quadro quadro : this.getQuadros()) {
            total += quadro.getPotenciaInstalada(unidadeDestino);
        }
        return total;
    }

    public double getPotenciaDemandada(UnidadePotencia unidadeDestino) {
        double total = 0;
        List<Quadro> lista = this.getQuadros();
        for (int i = 0; i < lista.size(); i++) {
            total += lista.get(i).getPotenciaDemandada(unidadeDestino);
        }
        return total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConcessionaria() {
        return concessionaria;
    }

    public void setConcessionaria(String concessionaria) {
        this.concessionaria = concessionaria;
    }

    public List<Quadro> getQuadros() {
        return quadros;
    }

    public void setQuadros(List<Quadro> quadros) {
        this.quadros = quadros;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTensaoFN() {
        return tensaoFN;
    }

    public void setTensaoFN(double tensaoFN) {
        this.tensaoFN = tensaoFN;
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
        if (!(object instanceof Fonte)) {
            return false;
        }
        Fonte other = (Fonte) object;
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
    public Fonte clonarSemID() {
        Fonte f = copiar();
        f.setId(null);
        return f;
    }

    @Override
    public Fonte copiar() {
        Fonte f = new Fonte();
        f.setId(id);
        f.setNome(nome);
        f.setConcessionaria(concessionaria);
        f.setTensaoFN(tensaoFN);
        f.setProjeto(projeto);
        f.setDescricao(descricao);

        List<Quadro> lista = new ArrayList<>();
        for (int i = 0; i < quadros.size(); i++) {
            Quadro qua = new Quadro();
            qua = quadros.get(i).clonarSemID();
            qua.setFonte(f);
            lista.add(qua);
        }
        f.setQuadros(lista);

        return f;
    }

    public void apagar() {

        id = 0;
        concessionaria = "";
        quadros.clear();
        nome = "";
        tensaoFN = 0;
    }
}
