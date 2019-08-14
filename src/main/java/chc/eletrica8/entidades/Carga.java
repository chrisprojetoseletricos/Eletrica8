/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.calculos.PotenciaAtivaDemCarga;
import chc.eletrica8.calculos.PotenciaAtivaCarga;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author chris
 */
@Entity
//@Table(name = "Carga")
//@TableModel
public class Carga implements Comparable<Carga>, Serializable, Entidade<Carga> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Circuito circuito;
    @Embedded
    private CargaResultados resultados = new CargaResultados();
    @Enumerated(EnumType.STRING)
    private Ligacao ligacao;
    private int quantidade = 1;
    private String descricao;
    private String localizacao;
    private double fd = 1;
    private double fs = 1;
    private double fu = 1;
    private double fSimu = 1;
    @Enumerated(EnumType.STRING)
    private Usabilidade usabilidade;
    //@Column(colName = "Nome", colPosition = 1)
    private String nome;
    private int nPolos = 2;
    private double perdasReator = 0;

    private double potencia = 0;
    private double rendimento = 1;
    private double fp = 1;
    @Enumerated(EnumType.STRING)
    private UnidadePotencia unidade;
    private double comprimentoInstal;

    public void correnteAtiva() {
        if (ligacao == Ligacao.FFF || ligacao == Ligacao.FFFN) {
            resultados.setCorrenteAtiva(getPotenciaAtiva(UnidadePotencia.VA) / (Math.sqrt(3) * resultados.getTensao()));

        } else {
            resultados.setCorrenteAtiva(getPotenciaAtiva(UnidadePotencia.VA) / (resultados.getTensao()));
        }

    }

    public void correnteReativa() {
        if (fp != 1) {
            if (ligacao == Ligacao.FFF || ligacao == Ligacao.FFFN) {
                resultados.setCorrenteReativa(getPotenciaAtiva(UnidadePotencia.VA) / (Math.sqrt(3) * resultados.getTensao() * (Math.sin(Math.acos(fp)))));

            } else {

                resultados.setCorrenteReativa(getPotenciaAtiva(UnidadePotencia.VA) / (resultados.getTensao() * (Math.sin(Math.acos(fp)))));

            }
        }else{
            resultados.setCorrenteReativa(0);
        }
        
    }

    public void correnteAparente() {
        resultados.setCorrenteAparente((Math.sqrt(Math.pow(resultados.getCorrenteAtiva(), 2) + Math.pow(resultados.getCorrenteReativa(), 2))));

    }

    public void correnteAtivaDem() {
        if (ligacao == Ligacao.FFF || ligacao == Ligacao.FFFN) {
            resultados.setCorrenteAtivaDem(getPotenciaAtivaDem(UnidadePotencia.VA) / (Math.sqrt(3) * resultados.getTensao()));

        } else {
            resultados.setCorrenteAtivaDem(getPotenciaAtivaDem(UnidadePotencia.VA) / (resultados.getTensao()));
        }
    }

    public void correnteReativaDem() {
        if (fp != 1) {
            if (ligacao == Ligacao.FFF || ligacao == Ligacao.FFFN) {
                resultados.setCorrenteReativaDem(getPotenciaAtivaDem(UnidadePotencia.VA) / (Math.sqrt(3) * resultados.getTensao() * (Math.sin(Math.acos(fp)))));

            } else {
                resultados.setCorrenteReativaDem(getPotenciaAtivaDem(UnidadePotencia.VA) / (resultados.getTensao() * (Math.sin(Math.acos(fp)))));
            }
        }else{
            resultados.setCorrenteReativaDem(0);
        }
        
    }

    public void correnteAparenteDem() {
        double cad = Math.pow(resultados.getCorrenteAtivaDem(), 2);
        double crd = Math.pow(resultados.getCorrenteReativaDem(), 2);
        double res = Math.sqrt(cad + crd);
        resultados.setCorrenteAparenteDem(res);
    }

    public void tensao() {

        try {
            if (ligacao.equals(Ligacao.FN)) {
                resultados.setTensao(getCircuito().getQuadro().getFonte().getTensaoFN());
            } else {
                resultados.setTensao(Math.sqrt(3) * getCircuito().getQuadro().getFonte().getTensaoFN());
            }
        } catch (Exception e) {

        }
    }

    public double getPotenciaAtivaDem(UnidadePotencia unidadeDestino) {
        double demanda = 0;
        try {
            demanda = new PotenciaAtivaDemCarga()//
                    .withFd(fd)//
                    .withFp(fp)//
                    .withFu(fu)//
                    .withSimu(fSimu)//
                    .withRendimento(rendimento)//
                    .withFatorCompensacaoPerdas(1.8)//
                    .withPerdasReator(perdasReator)//
                    .withPotencia(potencia)//
                    .withUnidadeOrigem(unidade)//
                    .withUnidadeDestino(unidadeDestino)//
                    .withUsabilidade(usabilidade)//
                    .valor();
        } catch (Exception e) {

        }
        return demanda;
    }

    public double getPotenciaAtiva(UnidadePotencia unidadeDestino) {
        double pot = 0;
        try {
            pot = new PotenciaAtivaCarga()//
                    .withFp(fp)//
                    .withFu(fu)//
                    .withRendimento(rendimento)//
                    .withFatorCompensacaoPerdas(1.8)//
                    .withPerdasReator(perdasReator)//
                    .withPotencia(potencia)//
                    .withUnidadeOrigem(unidade)//
                    .withUnidadeDestino(unidadeDestino)//
                    .withUsabilidade(usabilidade)//
                    .valor();
        } catch (Exception e) {

        }
        return pot;
    }

    public void potAtivaKVA() {
        resultados.setPotAtiva(getPotenciaAtiva(UnidadePotencia.VA) / 1000);
    }

    public void potAtivaDemKVA() {
        resultados.setPotAtivaDem(getPotenciaAtivaDem(UnidadePotencia.VA) / 1000);
    }

    /**
     * @return the circuito
     */
    public Circuito getCircuito() {
        return circuito;
    }

    /**
     * @param circuito the circuito to set
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    /**
     * @return the comprimentoInstal
     */
    public double getComprimentoInstal() {
        return comprimentoInstal;
    }

    /**
     * @param comprimentoInstal the comprimentoInstal to set
     */
    public void setComprimentoInstal(double comprimentoInstal) {
        this.comprimentoInstal = comprimentoInstal;
    }

    /**
     * @return the resultados
     */
    public CargaResultados getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(CargaResultados resultados) {
        this.resultados = resultados;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the nPolos
     */
    public int getnPolos() {
        return nPolos;
    }

    /**
     * @param nPolos the nPolos to set
     */
    public void setnPolos(int nPolos) {
        this.nPolos = nPolos;
    }

    /**
     * @return the perdasReator
     */
    public double getPerdasReator() {
        return perdasReator;
    }

    /**
     * @param perdasReator the perdasReator to set
     */
    public void setPerdasReator(double perdasReator) {
        this.perdasReator = perdasReator;
    }

    /**
     * @return the potencia
     */
    public double getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * @return the rendimento
     */
    public double getRendimento() {
        return rendimento;
    }

    /**
     * @param rendimento the rendimento to set
     */
    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    /**
     * @return the fp
     */
    public double getFp() {
        return fp;
    }

    /**
     * @param fp the fp to set
     */
    public void setFp(double fp) {
        this.fp = fp;
    }

    /**
     * @return the unidade
     */
    public UnidadePotencia getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(UnidadePotencia unidade) {
        this.unidade = unidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ligacao getLigacao() {
        return ligacao;
    }

    public void setLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
    }

    public double getfSimu() {
        return fSimu;
    }

    public void setfSimu(double fSimu) {
        this.fSimu = fSimu;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getFd() {
        return fd;
    }

    public void setFd(double fd) {
        this.fd = fd;
    }

    public double getFs() {
        return fs;
    }

    public void setFs(double fs) {
        this.fs = fs;
    }

    public double getFu() {
        return fu;
    }

    public void setFu(double fu) {
        this.fu = fu;
    }

    public Usabilidade getUsabilidade() {
        return usabilidade;
    }

    public void setUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
    }

    @Override
    public Carga clonarSemID() {
        Carga e = copiar();
        e.setId(null);
        return e;
    }

    @Override
    public Carga copiar() {
        Carga e = new Carga();
        e.setId(id);
        e.setDescricao(descricao);
        e.setFd(fd);
        e.setFu(fu);
        e.setfSimu(fSimu);
        e.setLigacao(ligacao);
        e.setQuantidade(quantidade);
        e.setUsabilidade(usabilidade);
        e.setLocalizacao(localizacao);
        e.setNome(nome);
        e.setnPolos(nPolos);
        e.setPerdasReator(perdasReator);
        e.setPotencia(potencia);
        e.setRendimento(rendimento);
        e.setFp(fp);
        e.setUnidade(unidade);
        e.setCircuito(circuito);
        e.setComprimentoInstal(comprimentoInstal);
        e.setResultados(resultados);

        return e;
    }

    @Override
    public int compareTo(Carga outraCarga) {
        if (resultados.getCorrenteAtiva() > outraCarga.getResultados().getCorrenteAtiva()) {
            return -1;
        }
        if (resultados.getCorrenteAtiva() < outraCarga.getResultados().getCorrenteAtiva()) {
            return 1;
        }
        return 0;
    }
}
