/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author chris
 */
@Entity
public class Fonte implements Serializable, Entidade<Fonte> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Projeto projeto;
    private String concessionaria;
    @Embedded
    private FonteResultados resultados = new FonteResultados();
    @OneToMany(mappedBy = "fonte", targetEntity = Quadro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quadro> quadros = new ArrayList<>();
    private String nome;
    private double tensaoFN;
    @Lob
    private String descricao;
    private TiposFornecimento tipo = TiposFornecimento.TRIFASICO;

    public void potAparenteDemKVA() {
        double pot = 0;
        for (Quadro quadro : quadros) {
            if(quadro.getQuadroGeral() == null){
            pot += quadro.getResultados().getPotAparenteDem();
            }
        }
        resultados.setPotAparenteDemKVA(pot / 1000);
    }

    public void potAparenteKVA() {
        double pot = 0;
        for (Quadro quadro : quadros) {
            if(quadro.getQuadroGeral() == null){
            pot += quadro.getResultados().getPotAparente();
            }
        }
        resultados.setPotAparenteKVA(pot / 1000);
    }

    public void fatorPotenciaMed() {
        double fp = 0;
        int i = 0;
        for (Quadro quadro : quadros) {
            for (Circuito circuito : quadro.getCircuitos()) {
                for (Carga carga : circuito.getCargas()) {
                    fp += carga.getFp();
                    i++;
                }
            }
        }
        if (i == 0) {
            resultados.setFp(0);
        } else {
            resultados.setFp(fp / i);
        }
    }

    public void correnteAtiva() {
        double correnteAtiva = 0;
        if (quadros.isEmpty()) {
        } else {
            for (Quadro quadro : quadros) {
                if (quadro.getQuadroGeral() == null) {
                    correnteAtiva += quadro.getResultados().getCorrenteAtiva();
                }
            }
        }
        resultados.setCorrenteAtiva(correnteAtiva);
    }

    public void correnteAtivaDem() {
        double correnteAtivaDem = 0;
        if (quadros.isEmpty()) {
        } else {
            for (Quadro quadro : quadros) {
                if (quadro.getQuadroGeral() == null) {
                    correnteAtivaDem += quadro.getResultados().getCorrenteAtivaDem();
                }
            }
        }
        resultados.setCorrenteAtivaDem(correnteAtivaDem);
    }

    public void correnteReativa() {
        double correnteReativa = 0;
        if (quadros.isEmpty()) {

        } else {
            for (Quadro quadro : quadros) {
                if (quadro.getQuadroGeral() == null) {
                    correnteReativa += quadro.getResultados().getCorrenteReativa();
                }
            }
        }
        resultados.setCorrenteReativa(correnteReativa);
    }

    public void correnteReativaDem() {
        double correnteReativaDem = 0;
        if (quadros.isEmpty()) {

        } else {
            for (Quadro quadro : quadros) {
                if (quadro.getQuadroGeral() == null) {
                    correnteReativaDem += quadro.getResultados().getCorrenteReativaDem();
                }
            }
        }
        resultados.setCorrenteReativaDem(correnteReativaDem);
    }

    public void correnteAparente() {
        resultados.setCorrenteAparente((Math.sqrt(Math.pow(resultados.getCorrenteAtiva(), 2) + Math.pow(resultados.getCorrenteReativa(), 2))));

    }

    public void correnteAparenteDem() {
        resultados.setCorrenteAparenteDem((Math.sqrt(Math.pow(resultados.getCorrenteAtivaDem(), 2) + Math.pow(resultados.getCorrenteReativaDem(), 2))));

    }

    public List<Carga> todasCargas() {
        List<Carga> cargas = new ArrayList<>();
        for (Quadro quadros : quadros) {
            for (Circuito circuito : quadros.getCircuitos()) {
                for (Carga carga : circuito.getCargas()) {
                    cargas.add(carga);
                }
            }
        }
        return cargas;
    }

    public void defineTipo() {
        TiposFornecimento tipo = TiposFornecimento.MONOFASICO;
        for (Quadro quadro : quadros) {
            if (quadro.getTipo() == TiposFornecimento.TRIFASICO && (tipo == TiposFornecimento.BIFASICO || tipo == TiposFornecimento.BIFASICO)) {
                tipo = TiposFornecimento.TRIFASICO;
            } else if (quadro.getTipo() == TiposFornecimento.BIFASICO && (tipo == TiposFornecimento.MONOFASICO)) {
                tipo = TiposFornecimento.BIFASICO;
            } else if (quadro.getTipo() == TiposFornecimento.MONOFASICO && (tipo == TiposFornecimento.MONOFASICO)) {
                tipo = TiposFornecimento.MONOFASICO;
            }
        }
    }

    public void tensao() {

        try {
            if (resultados.getLigacao().equals(Ligacao.FN)) {
                resultados.setTensao(getTensaoFN());
            } else {
                resultados.setTensao(Math.sqrt(3) * getTensaoFN());
            }
        } catch (Exception e) {

        }
    }

    public void ligacaoCondutor() {
        Ligacao temp = Ligacao.FFF;
        String neutro = "Nao";

        if (!todasCargas().isEmpty()) {
            for (Carga carga : todasCargas()) {
                if (carga.getLigacao() == Ligacao.FF || carga.getLigacao() == Ligacao.FFF) {
                } else {
                    neutro = "Sim";
                }
            }
            switch (getTipo()) {
                case TRIFASICO:
                    if (neutro.equals("Sim")) {
                        temp = Ligacao.FFFN;
                    } else {
                        temp = Ligacao.FFF;
                    }
                    break;
                case BIFASICO:
                    if (neutro.equals("Sim")) {
                        temp = Ligacao.FFN;
                    } else {
                        temp = Ligacao.FF;
                    }
                    break;
                case MONOFASICO:
                    temp = Ligacao.FN;
                    break;
                default:
                    break;
            }
        } else {
            int num = 0;
            String n = "Não";
            for (Quadro quadro : quadros) {

                if (quadro.getResultados().getLigacao().getNumeroFases() >= num) {
                    num = quadro.getResultados().getLigacao().getNumeroFases();
                    if (!(quadro.getResultados().getLigacao() == Ligacao.FF || quadro.getResultados().getLigacao() == Ligacao.FFF)) {
                        n = "Sim";
                    }
                }
            }
            switch (num) {
                case 1:
                    if (n.equals("Sim")) {
                        temp = Ligacao.FN;
                    } else {
                        temp = Ligacao.FN;
                    }

                    break;
                case 2:
                    if (n.equals("Sim")) {
                        temp = Ligacao.FFN;
                    } else {
                        temp = Ligacao.FF;
                    }

                    break;
                case 3:
                    if (n.equals("Sim")) {
                        temp = Ligacao.FFFN;
                    } else {
                        temp = Ligacao.FFF;
                    }

                    break;
                default:
                    temp = Ligacao.FFF;
                    break;
            }
        }
        resultados.setLigacao(temp);
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

    /**
     * @return the tipo
     */
    public TiposFornecimento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TiposFornecimento tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the resultados
     */
    public FonteResultados getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(FonteResultados resultados) {
        this.resultados = resultados;
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
        f.setTipo(tipo);

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
