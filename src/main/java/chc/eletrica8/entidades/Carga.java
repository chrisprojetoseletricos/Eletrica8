/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Corrente;
import chc.eletrica8.calculos.PotenciaDemandadaCarga;
import chc.eletrica8.calculos.PotenciaInstaladaCarga;
import chc.eletrica8.controle.Ids;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.servico.FonteService;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 *
 * @author chris
 */
@Entity
@Table(name = "Carga")
@TableModel
public class Carga implements Serializable, Entidade<Carga> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(colName = "Id", colPosition = 0)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Circuito circuito;
    @Column(colName = "Ligação", colPosition = 4)
    private String ligacaoReal;
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
    @Column(colName = "Tensão (V)", colPosition = 1, formatter = "%,#.1f V")
    private double tensao = 0;

    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    private int nPolos = 2;
    private double perdasReator = 0;
    @Column(colName = "Potência", colPosition = 1)
    private double potencia = 0;
    private double rendimento = 1;
    private double fp = 1;
    @Column(colName = "Unidade", colPosition = 2)
    @Enumerated(EnumType.STRING)
    private UnidadePotencia unidade;

    public double getCorrenteA() {
        double correnteA = 0;
        try {
            correnteA = new Corrente()//
                    .withLigacao(ligacao)//
                    .withPotencia(getPotencia())//
                    .withTensao(FonteService.getById(Ids.getIdFonte()).getTensaoFN())//
                    .withUnidade(getUnidade())//
                    .withFP(getFp())//
                    .valor();
        } catch (Exception e) {

        }
        return correnteA;
    }

    public double getPotenciaDemandada(UnidadePotencia unidadeDestino) {
        double demanda = 0;
        try {
            demanda = new PotenciaDemandadaCarga()//
                    .withFd(fd)//
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
        return demanda;
    }

    public double getPotenciaInstalada(UnidadePotencia unidadeDestino) {
        double pot = 0;
        try {
            pot = new PotenciaInstaladaCarga()//
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

    public void atualizaTensao() {
        tensao = 0;
        try {
            if (ligacao.equals(Ligacao.FN)) {
                tensao = FonteService.getById(Ids.getIdFonte()).getTensaoFN();
            } else {
                tensao = Math.sqrt(3) * FonteService.getById(Ids.getIdFonte()).getTensaoFN();
            }
        } catch (Exception e) {

        }
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

    @Override
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

    public double getTensao() {
        return tensao;
    }

    public void setTensao(double tensao) {
        this.tensao = tensao;
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

    public String getLigacaoReal() {
        return ligacaoReal;
    }

    public void setLigacaoReal(String ligacaoReal) {
        this.ligacaoReal = ligacaoReal;
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

        e.setDescricao(descricao);
        e.setFd(fd);
        e.setFu(fu);
        e.setfSimu(fSimu);
        e.setLigacaoReal(ligacaoReal);
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

        return e;
    }

    @Override
    public void apagar() {
        ligacaoReal = "";
        quantidade = 1;
        descricao = "";
        fd = 1;
        fs = 1;
        fu = 1;
        fSimu = 1;
        usabilidade = null;
        localizacao = "";
        ligacao = null;
        ligacaoReal = "";
        nome = "";
        nPolos = 2;
        perdasReator = 0;
        potencia = 0;
        rendimento = 1;
        fp = 1;
        unidade = null;
    }
}
