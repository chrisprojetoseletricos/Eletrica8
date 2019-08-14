/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chris
 */
@Embeddable
public class ProjetoResultados implements Serializable, Entidade<ProjetoResultados> {

    public void limpa() {

    }

    @Override
    public ProjetoResultados clonarSemID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProjetoResultados copiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
