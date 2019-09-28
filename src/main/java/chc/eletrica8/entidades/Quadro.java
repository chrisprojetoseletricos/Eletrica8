package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.calculos.CorrenteProjeto;
import chc.eletrica8.calculos.CorrenteQuadro;
import chc.eletrica8.calculos.DisjuntorTM;
import chc.eletrica8.calculos.Fator;
import chc.eletrica8.calculos.Fusivel;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.Tabelas;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.enums.UsoDr;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import chc.eletrica8.uteis.LerCSV;
import chc.eletrica8.uteis.Numero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Quadro")
@TableModel
public class Quadro implements Serializable, Entidade<Quadro> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Fonte fonte;
    private QuadroResultados resultados = new QuadroResultados();
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Quadro quadroGeral;
    @OneToMany(mappedBy = "quadroGeral", targetEntity = Quadro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quadro> quadros = new ArrayList<>();
    @OneToMany(mappedBy = "quadro", targetEntity = Circuito.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Circuito> circuitos = new ArrayList<>();
    @Embedded
    private Condutor condutor;
    @Embedded
    private Curto curto;
    @Enumerated(EnumType.STRING)
    private UsoDr usoDeDR;
    @Column(colName = "Local", colPosition = 1)
    private String localizacao;
    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    private double pot100PercDem;
    @Enumerated(EnumType.STRING)
    private Usabilidade usabilidade;
    private int tempAmbiente;

    public void disjuntorTM() {
        String disj = "";

        if (circuitos.isEmpty()) {
        } else {
            disj = new DisjuntorTM(this).valor();
        }
        resultados.setDisjuntorTM(disj);
    }
//O fusivel deve ser menor ou igual ao valor achado

    public void fusivel() {
        String fusivel = "";

        if (circuitos.isEmpty()) {
        } else {
            fusivel = new Fusivel(this).valor();
        }
        resultados.setFusivel(fusivel);
    }

    public void atualizaBitola() {
        faseCondutor();
        neutroCondutor();
        terraCondutor();
        bitolaCondutor();
    }

    public void ordenaDecrListaCircuitos() {
int num = circuitos.size();
        if (num < 2) {
        } else {
            Collections.sort(circuitos);
        }

    }

    public void correnteAtiva() {
        double correnteAtiva = 0;
        if (circuitos.isEmpty()) {
            for (Quadro quadro : quadros) {
                correnteAtiva += quadro.getResultados().getCorrenteAtiva();
            }
        } else {
            double corrente1 = new CorrenteQuadro("Ativa")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();

            double corrente2 = 0;
            switch (resultados.getLigacao()) {
                case FFF:
                case FFFN:
                    corrente2 = resultados.getPotAparente() / (Math.sqrt(3) * resultados.getTensao());
                    break;
                default:
                    corrente2 = resultados.getPotAparente() / (resultados.getTensao());
                    break;
            }
            if (corrente1 >= corrente2) {
                correnteAtiva += corrente1;
            } else {
                correnteAtiva += corrente2;
            }
        }
        resultados.setCorrenteAtiva(correnteAtiva);
    }

    public void correnteAtivaDem() {
        double correnteAtivaDem = 0;
        if (circuitos.isEmpty()) {
            for (Quadro quadro : quadros) {
                correnteAtivaDem += quadro.getResultados().getCorrenteAtivaDem();
            }
        } else {
            double corrente1 = new CorrenteQuadro("AtivaDem")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();

            double corrente2 = 0;
            switch (resultados.getLigacao()) {
                case FFF:
                case FFFN:
                    corrente2 = resultados.getPotAparenteDem() / (Math.sqrt(3) * resultados.getTensao());
                    break;
                default:
                    corrente2 = resultados.getPotAparenteDem() / (resultados.getTensao());
                    break;
            }
            if (corrente1 >= corrente2) {
                correnteAtivaDem += corrente1;
            } else {
                correnteAtivaDem += corrente2;
            }
        }
        resultados.setCorrenteAtivaDem(correnteAtivaDem);
    }

    public void potAtivaKW() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaAtiva();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaAtiva();
                    }
                }
            }
        }
        resultados.setPotAtiva(pot);
    }

    public void potReativaKVAr() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaAtiva();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaAtiva();
                    }
                }
            }
        }
        resultados.setPotAtiva(pot);
    }

    public void potAtivaDemKW() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaAtivaDem();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaAtivaDem();
                    }
                }
            }
        }
        resultados.setPotAtivaDem(pot);
    }

    public void potReativaDemKVAr() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaReativaDem();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaReativaDem();
                    }
                }
            }
        }
        resultados.setPotReativaDem(pot);
    }

    public void potAparenteKVA() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaAparente();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaAparente();
                    }
                }
            }
        }
        resultados.setPotAparente(pot);
    }

    public void potAparenteDemKVA() {
        double pot = 0;
        for (Circuito circuito : this.circuitos) {
            for (Carga carga : circuito.getCargas()) {
                pot += carga.getPotenciaAparenteDem();
            }
        }
        if (quadros.isEmpty()) {
        } else {
            List<Quadro> listaQuadros = quadros;
            for (Quadro quadro : listaQuadros) {
                for (Circuito circuito : quadro.circuitos) {
                    for (Carga carga : circuito.getCargas()) {
                        pot += carga.getPotenciaAparenteDem();
                    }
                }
            }
        }
        resultados.setPotAparenteDem(pot);
    }

    public void tensao() {

        try {
            if (resultados.getLigacao().equals(Ligacao.FN)) {
                resultados.setTensao(getFonte().getTensaoFN());
            } else {
                resultados.setTensao(Math.sqrt(3) * getFonte().getTensaoFN());
            }
        } catch (Exception e) {

        }
    }

    public void correnteProjeto() {
        new CorrenteProjeto()//
                .withQuadro(this)//
                .calcula();
    }

    public void correnteCorr() {

        double corrente = 0;

        if (usabilidade != Usabilidade.MOTOR) {
            corrente = resultados.getCorrenteAtivaDem();
        } else {
            corrente = resultados.getCorrenteProjeto();
        }

        Fator fator = new Fator(condutor);

        resultados.setCorrenteCorr(corrente / (fator.FCT() * fator.FCA()));
    }

    public void fatorPotenciaMed() {
        double fp = 0;
        int i = 0;

        for (Quadro quadro : quadros) {
            if (quadro.getQuadroGeral() != null) {
                fp += quadro.getResultados().getFp();
                i++;
            }
        }

        for (Circuito circuito : circuitos) {
            for (Carga carga : circuito.getCargas()) {
                fp += carga.getFp();
                i++;
            }
        }

        if (i == 0) {
            resultados.setFp(0);
        } else {
            resultados.setFp(fp / i);
        }
    }

    public Tabelas tabelaCapacidadeCorr(Condutor condutor) {
        Tabelas tab;
        if (condutor.getMaterial().equals("Cobre")) {
            tab = Tabelas.CapaciCorrBaixaCobre;
        } else {
            tab = Tabelas.CapaciCorrBaixaAluminio;
        }
        return tab;
    }

    public Tabelas tabelaCorrecaoTemp(Condutor condutor) {
        Tabelas tab;
        if (condutor.getEnterrado().equals("Sim")) {
            tab = Tabelas.CorrencaoTempE;
        } else {
            tab = Tabelas.CorrecaoTempNE;
        }
        return tab;
    }

    public Tabelas tabelaCorrecaoAgrupa(Condutor condutor) {
        Tabelas tab = null;
        if (condutor.getnCamadas() > 1 && condutor.getEnterrado().equals(Enterrado.Nao)) {
            tab = Tabelas.CorrecaoAgruT3_16;
        } else if (condutor.getnCamadas() <= 1 && condutor.getEnterrado().equals(Enterrado.Nao)) {
            tab = Tabelas.CorrecaoAgruT3_15;
        } else if (condutor.getEnterrado().equals(Enterrado.SimSDuto)) {
            tab = Tabelas.CorrecaoAgruT3_17;
        } else if (condutor.getEnterrado().equals(Enterrado.SimCDuto) && condutor.getMultipolar().equals("Sim")) {
            tab = Tabelas.CorrecaoAgruT3_18;
        } else if (condutor.getEnterrado().equals(Enterrado.SimCDuto) && condutor.getMultipolar().equals("Não")) {
            tab = Tabelas.CorrecaoAgruT3_19;
        }
        return tab;
    }

    public Tabelas tabelaCapacidadeCorr(String material) {
        Tabelas tab;
        if (material.equals("Cobre")) {
            tab = Tabelas.CapaciCorrBaixaCobre;
        } else {
            tab = Tabelas.CapaciCorrBaixaAluminio;
        }
        return tab;
    }

    public double LXI() {

        return condutor.getComprim() * resultados.getCorrenteAtivaDem();
    }

    public List<Carga> todasCargas() {
        List<Carga> cargas = new ArrayList<>();

        for (Circuito circuito : this.getCircuitos()) {
            for (Carga carga : circuito.getCargas()) {
                cargas.add(carga);
            }
        }

        for (Quadro quadro : quadros) {

            for (Circuito circuito : quadro.getCircuitos()) {
                for (Carga carga : circuito.getCargas()) {
                    cargas.add(carga);
                }
            }
        }
        return cargas;
    }

    public Ligacao ligacaoCargaMaior() {
        Ligacao liga = Ligacao.FN;
        double corrente = 0;

        for (Circuito circuito : circuitos) {
            for (Carga carga : circuito.getCargas()) {
                if (resultados.getCorrenteAtiva() > corrente) {
                    liga = carga.getLigacao();
                    corrente = resultados.getCorrenteAtiva();
                }
            }
        }

        for (Quadro quadro : quadros) {

            for (Circuito circuito : quadro.circuitos) {
                for (Carga carga : circuito.getCargas()) {
                    if (resultados.getCorrenteAtiva() > corrente) {
                        liga = carga.getLigacao();
                        corrente = resultados.getCorrenteAtiva();
                    }
                }
            }
        }

        return liga;
    }

    public void faseCondutor() {
        Ligacao ligacao = Ligacao.FN;

        if (quadroGeral == null) {
            ligacao = resultados.getLigacao();
        } else {
            ligacao = ligacaoCargaMaior();
        }
        String fase = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacao)//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .withFaseDefDisjuntor(condutor.getFase())//
                .withDivFase(condutor.getDivFase())//
                .fase();

        resultados.setFase(Numero.stringToDouble(fase, 0));

    }

    public void neutroCondutor() {
        Ligacao ligacao = Ligacao.FN;

        if (quadroGeral == null) {
            ligacao = resultados.getLigacao();
        } else {
            ligacao = ligacaoCargaMaior();
        }

        String neutro = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacao)//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .withFaseDefDisjuntor(condutor.getFase())//
                .withDivFase(condutor.getDivFase())//
                .neutro();

        resultados.setNeutro(Numero.stringToDouble(neutro, 0));

    }

    public void bitolaCondutor() {
        Ligacao ligacao = Ligacao.FN;

        if (quadroGeral == null) {
            ligacao = resultados.getLigacao();
        } else {
            ligacao = ligacaoCargaMaior();
        }

        String bitola = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacao)//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .withFaseDefDisjuntor(condutor.getFase())//
                .withDivFase(condutor.getDivFase())//
                .formatado();

        resultados.setBitola(bitola);

    }

    public void terraCondutor() {
        Ligacao ligacao = Ligacao.FN;

        if (quadroGeral == null) {
            ligacao = resultados.getLigacao();
        } else {
            ligacao = ligacaoCargaMaior();
        }

        String terra = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacao)//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .withFaseDefDisjuntor(condutor.getFase())//
                .withDivFase(condutor.getDivFase())//
                .terra();

        resultados.setTerra(Numero.stringToDouble(terra, 0));

    }

    //Define tipo do circuito e Ligação do condutor
    public void tipoFornCondutor() {
        TiposFornecimento tipo = TiposFornecimento.MONOFASICO;

        if (!todasCargas().isEmpty()) {
            for (Carga carga : todasCargas()) {
                if (carga.getLigacao() == Ligacao.FFFN || carga.getLigacao() == Ligacao.FFF) {
                    tipo = TiposFornecimento.TRIFASICO;
                } else if (carga.getLigacao() == Ligacao.FF || carga.getLigacao() == Ligacao.FFN) {
                    tipo = TiposFornecimento.BIFASICO;
                } else {
                    tipo = TiposFornecimento.MONOFASICO;
                }
            }
        }
        resultados.setTipo(tipo);
    }

    /**
     * @return the fonte
     */
    public Fonte getFonte() {
        return fonte;
    }

    /**
     * @param fonte the fonte to set
     */
    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
    }

    /**
     * @return the quadroGeral
     */
    public Quadro getQuadroGeral() {
        return quadroGeral;
    }

    /**
     * @param quadroGeral the quadroGeral to set
     */
    public void setQuadroGeral(Quadro quadroGeral) {
        this.quadroGeral = quadroGeral;
    }

    /**
     * @return the quadrosFinais
     */
    public List<Quadro> getQuadros() {
        return quadros;
    }

    /**
     * @param quadros the quadrosFinais to set
     */
    public void setQuadros(List<Quadro> quadros) {
        this.quadros = quadros;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @return the resultados
     */
    public QuadroResultados getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(QuadroResultados resultados) {
        this.resultados = resultados;
    }

    public int getTempAmbiente() {
        return tempAmbiente;
    }

    public void setTempAmbiente(int tempAmbiente) {
        this.tempAmbiente = tempAmbiente;
    }

    public List<Circuito> getCircuitos() {
        return circuitos;
    }

    public void setCircuitos(List<Circuito> circuitos) {
        this.circuitos = circuitos;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        if (condutor != null) {
            this.condutor = condutor;
        }
    }

    public Curto getCurto() {
        return curto;
    }

    public void setCurto(Curto curto) {
        this.curto = curto;
    }

    public UsoDr getUsoDeDR() {
        return usoDeDR;
    }

    public void setUsoDeDR(UsoDr usoDeDR) {
        this.usoDeDR = usoDeDR;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPot100PercDem() {
        return pot100PercDem;
    }

    public void setPot100PercDem(double pot100PercDem) {
        this.pot100PercDem = pot100PercDem;
    }

    public Usabilidade getUsabilidade() {
        return usabilidade;
    }

    public void setUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
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
        if (!(object instanceof Quadro)) {
            return false;
        }
        Quadro other = (Quadro) object;
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
    public Quadro clonarSemID() {
        Quadro q = copiar();
        q.setId(null);
        return q;
    }

    @Override
    public Quadro copiar() {
        Quadro q = new Quadro();
        q.setId(id);
        q.setNome(nome);
        q.setCondutor(condutor);
        q.setCurto(curto);
        q.setLocalizacao(localizacao);
        q.setPot100PercDem(pot100PercDem);
        q.setUsabilidade(usabilidade);
        q.setUsoDeDR(usoDeDR);
        q.setTempAmbiente(tempAmbiente);
        q.setFonte(fonte);
        q.setQuadroGeral(quadroGeral);

        List<Circuito> lista = new ArrayList<>();
        for (int i = 0; i < circuitos.size(); i++) {
            Circuito ca = new Circuito();
            ca = circuitos.get(i).clonarSemID();
            ca.setQuadro(q);
            lista.add(ca);
        }
        q.setCircuitos(lista);

        return q;
    }

    public void apagar() {

        id = 0;
        circuitos.clear();
        condutor = null;
        curto = null;
        usoDeDR = null;
        localizacao = "";
        nome = "";
        pot100PercDem = 0;
        usabilidade = null;
    }
}
