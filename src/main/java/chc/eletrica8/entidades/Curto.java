/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author chris
 */
@Entity
@Table(name = "Curto")
@TableModel
public class Curto implements Serializable, Entidade<Curto> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double correnteCurto;
    private double tempAdmissRegime;
    private double tempMaxCurto;
    private double tempoElimDef;

    public double getCorrenteCurto() {
        return correnteCurto;
    }

    public void setCorrenteCurto(double correnteCurto) {
        this.correnteCurto = correnteCurto;
    }

    public double getTempAdmissRegime() {
        return tempAdmissRegime;
    }

    public void setTempAdmissRegime(double tempAdmissRegime) {
        this.tempAdmissRegime = tempAdmissRegime;
    }

    public double getTempMaxCurto() {
        return tempMaxCurto;
    }

    public void setTempMaxCurto(double tempMaxCurto) {
        this.tempMaxCurto = tempMaxCurto;
    }

    public double getTempoElimDef() {
        return tempoElimDef;
    }

    public void setTempoElimDef(double tempoElimDef) {
        this.tempoElimDef = tempoElimDef;
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
        if (!(object instanceof Curto)) {
            return false;
        }
        Curto other = (Curto) object;
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
    public Curto clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curto copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apagar() {
        id = 0;
        correnteCurto = 0;
        tempAdmissRegime = 0;
        tempMaxCurto = 0;
        tempoElimDef = 0;
    }
}
