/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author chris
 */
@Entity
@Table(name = "Projeto")
@TableModel
@SuppressWarnings("serial")
public class Projeto implements Serializable, Entidade<Projeto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Integer id;
    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    @Column(colName = "Autor", colPosition = 1)
    private String autor;
    @Column(colName = "Data", colPosition = 2)
    @Temporal(TemporalType.DATE)
    private Date dataProjeto;
    @Lob
    private String descricao;
    @OneToMany(mappedBy = "projeto", targetEntity = Fonte.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fonte> fontes = new ArrayList<Fonte>();

    public List<Fonte> getFontes() {
        return fontes;
    }

    public void setFontes(List<Fonte> fontes) {
        this.fontes = fontes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataProjeto() {
        return dataProjeto;
    }

    public void setDataProjeto(Date dataProjeto) {
        this.dataProjeto = dataProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
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
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
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
    public Projeto clonarSemID() {
        Projeto p = copiar();
        p.setId(null);
        return p;
    }

    @Override
    public Projeto copiar() {
        Projeto p = new Projeto();
        p.setId(id);
        p.setNome(nome);
        p.setAutor(autor);
        p.setDataProjeto(dataProjeto);
        p.setDescricao(descricao);
        for (Fonte f : fontes) {
            p.fontes.add(f);
        }
        return p;
    }

    @Override
    public void apagar() {
        id = 0;
        nome = "";
        autor = "";
        descricao = "";
        fontes.clear();
    }

}
