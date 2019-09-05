/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.calculos.ConversorPotencia;
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
    private double fp = 1;
    private double fm = 1;
    @Enumerated(EnumType.STRING)
    private Usabilidade usabilidade;
    private String nome;
    private int nPolos = 2;
    private double perdasW = 0;
    private double potencia = 0;
    private double rendimento = 1;
    @Enumerated(EnumType.STRING)
    private UnidadePotencia unidade;
    private double comprimentoInstal;

    public void correnteAtiva() {
        double corrente = fs * (getPotenciaAtiva() / (resultados.getTensao() * fp));

        switch (ligacao) {
            case FFF:
            case FFFN:
                resultados.setCorrenteAtiva(corrente / (Math.sqrt(3)));
                break;
            default:
                resultados.setCorrenteAtiva(corrente);
                break;
        }
    }

    public void correnteAtivaDem() {
        resultados.setCorrenteAtivaDem(resultados.getCorrenteAtiva() * fd * fSimu);
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

    public double getPotenciaAtiva() {
        double valor = 0;
        switch (usabilidade) {
            case ILUMINACAO_FLUORESCENTE_PERDAS:
                valor = fm * quantidade * (convertePotencia(UnidadePotencia.W) + perdasW);
                break;
            default:
                valor = fm * quantidade * (convertePotencia(UnidadePotencia.W) + perdasW);
                break;
        }
        return valor;
    }

    public double getPotenciaAparente() {
        double valor = 0;
        switch (usabilidade) {
            case ILUMINACAO_FLUORESCENTE_PERDAS:
                valor = fm * quantidade * (convertePotencia(UnidadePotencia.W) + perdasW / fp);
                break;
            default:
                valor = getPotenciaAtiva() / fp;
                break;
        }

        return valor;
    }

    public double getPotenciaReativa() {
        double valor = 0;

        valor = getPotenciaAparente() * Math.sin(Math.acos(fp));
        return valor;
    }

    public double getPotenciaAtivaDem() {
        double valor = 0;
        valor = getPotenciaAtiva() * fd * fSimu;
        return valor;
    }

    public double getPotenciaReativaDem() {
        double valor = 0;
        valor = getPotenciaReativa() * fd * fSimu;
        return valor;
    }

    public double getPotenciaAparenteDem() {
        double valor = 0;
        valor = getPotenciaAparente() * fd * fSimu;
        return valor;
    }

    private double convertePotencia(UnidadePotencia destino) {
        return new ConversorPotencia()//
                .withFatorPotencia(fp)//
                .withPotencia(potencia)//
                .withUnidadeOrigem(unidade)//
                .withFu(fu)//
                .withRendimento(rendimento)//
                .withUnidadeDestino(destino)//
                .converte();
    }

    public void potAtivaKW() {
        resultados.setPotAtiva(getPotenciaAtiva());
    }

    public void potAtivaDemKW() {
        resultados.setPotAtivaDem(getPotenciaAtivaDem());
    }

    public void potReativaKVAr() {
        resultados.setPotReativa(getPotenciaReativa());
    }

    public void potReativaDemKVAr() {
        resultados.setPotReativaDem(getPotenciaReativaDem());
    }

    public void potAparenteKVA() {
        resultados.setPotAparente(getPotenciaAparente());
    }

    public void potAparenteDemKVA() {
        resultados.setPotAparenteDem(getPotenciaAparenteDem());
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
     * @return the fm
     */
    public double getFm() {
        return fm;
    }

    /**
     * @param fm the fm to set
     */
    public void setFm(double fm) {
        this.fm = fm;
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
    public double getPerdasW() {
        return perdasW;
    }

    /**
     * @param perdasW the perdasReator to set
     */
    public void setPerdasW(double perdasW) {
        this.perdasW = perdasW;
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
        e.setPerdasW(perdasW);
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
