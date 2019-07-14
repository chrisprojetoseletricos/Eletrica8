/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.TiposCondutores;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author chris
 */
@Entity
public class RamalLigacao implements Serializable, Entidade<RamalLigacao> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private BitolasMili bitola;
    @Enumerated(EnumType.STRING)
    private TiposCondutores condutor;
    private String distanciaOrla;
    private String material;

    public BitolasMili getBitola() {
        return bitola;
    }

    public void setBitola(BitolasMili bitola) {
        this.bitola = bitola;
    }

    public TiposCondutores getCondutor() {
        return condutor;
    }

    public void setCondutor(TiposCondutores condutor) {
        this.condutor = condutor;
    }

    public String getDistanciaOrla() {
        return distanciaOrla;
    }

    public void setDistanciaOrla(String distanciaOrla) {
        this.distanciaOrla = distanciaOrla;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        if (!(object instanceof RamalLigacao)) {
            return false;
        }
        RamalLigacao other = (RamalLigacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

    @Override
    public RamalLigacao clonarSemID() {
        RamalLigacao q = copiar();
        q.setId(null);
        return q;
    }

    @Override
    public RamalLigacao copiar() {
        RamalLigacao r = new RamalLigacao();
        r.setId(id);
        r.setBitola(bitola);
        r.setCondutor(condutor);
        r.setDistanciaOrla(distanciaOrla);
        r.setMaterial(material);
        return r;
    }


    public void apagar() {
        id = 0;
        bitola = null;
        condutor = null;
        distanciaOrla = "";
        material = null;
    }
}
