/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Embeddable
@Table(name = "Curto")
@TableModel
public class Curto implements Serializable, Entidade<Curto> {

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

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curto clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curto copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
