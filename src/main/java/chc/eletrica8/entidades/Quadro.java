package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.calculos.CorrenteQuadro;
import chc.eletrica8.controle.Ids;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.EspacamentoCabos;
import chc.eletrica8.enums.EspacamentoEletrodutos;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.Tabelas;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_15;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_16;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_17;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_18;
import static chc.eletrica8.enums.Tabelas.CorrecaoAgruT3_19;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.enums.UsoDr;
import chc.eletrica8.servico.FonteService;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import chc.eletrica8.uteis.LerCSV;
import chc.eletrica8.uteis.Matriz;
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
    @OneToMany(mappedBy = "quadro", targetEntity = Circuito.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    private TiposFornecimento tipo = TiposFornecimento.TRIFASICO;

    public void ordenaDecrListaCircuitos() {
        Collections.sort(circuitos);
    }

    public void potAtivaKVA() {
        resultados.setPotAtiva(Math.sqrt(3) * resultados.getCorrenteAtiva() * resultados.getTensao() / 1000);
    }

    public void potAtivaDemKVA() {
        resultados.setPotAtivaDem(Math.sqrt(3) * resultados.getCorrenteAtivaDem() * resultados.getTensao() / 1000);
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

    public void correnteCorr() {
        double correnteIB = resultados.getCorrenteAtiva();
        double fatorTemp = Numero.stringToDouble(//
                Matriz.pegarValorMatrizEspecial(new LerCSV(tabelaCorrecaoTemp(condutor).getNome()).toMatriz(),//
                        Integer.toString(condutor.getTemperatura()),//
                        condutor.getIsolacao()), 0);

        double fatorAgrupa = 0;

        if (condutor.getEspacoCabos() == EspacamentoCabos.MaiorQDobroDoDiametro
                ||//
                condutor.getEspacoEletrodutos() == EspacamentoEletrodutos.MaiorQ1m) {
            fatorAgrupa = 1;
        } else {
            switch (tabelaCorrecaoAgrupa(condutor)) {
                case CorrecaoAgruT3_15:
                    fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_15.getNome()).toMatriz(),//
                            condutor.getFormaAgrupa().getNome(),//
                            Integer.toString(condutor.getnCirAgrupa())), 0);
                    break;
                case CorrecaoAgruT3_16:
                    fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_16.getNome()).toMatriz(),//
                            Integer.toString(condutor.getnCamadas()),//
                            Integer.toString(condutor.getnCirAgrupa())), 0);
                    break;
                case CorrecaoAgruT3_17:
                    fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_17.getNome()).toMatriz(),//
                            Integer.toString(condutor.getnCirAgrupa()),//
                            condutor.getEspacoCabos().getNome()), 0);
                    break;
                case CorrecaoAgruT3_18:
                    fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_18.getNome()).toMatriz(),//
                            Integer.toString(condutor.getnCirAgrupa()),//
                            condutor.getEspacoEletrodutos().getNome()), 0);
                    break;
                case CorrecaoAgruT3_19:
                    fatorAgrupa = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CorrecaoAgruT3_19.getNome()).toMatriz(),//
                            Integer.toString(condutor.getnCirAgrupa()),//
                            condutor.getEspacoEletrodutos().getNome()), 0);
                    break;
                default:
                    break;

            }
        }

        resultados.setCorrenteCorr(correnteIB / (fatorTemp * fatorAgrupa));
    }

    public void fatorPotenciaMed() {
        double fp = 0;
        int i = 0;
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

    public void correnteAtiva() {
        double correnteAtiva = 0;
        if (circuitos.isEmpty()) {
        } else {
            correnteAtiva = new CorrenteQuadro("Ativa")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        if (this.somaQuadrosFilhos().isEmpty()) {
        } else {
            List<Quadro> listaQuadros = this.somaQuadrosFilhos();
            for (Quadro quadro : listaQuadros) {
                correnteAtiva += quadro.getResultados().getCorrenteAtiva();
            }
        }
        resultados.setCorrenteAtiva(correnteAtiva);
    }

    public void correnteAtivaDem() {
        double correnteAtivaDem = 0;
        if (circuitos.isEmpty()) {
        } else {
            correnteAtivaDem = new CorrenteQuadro("AtivaDem")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        if (this.somaQuadrosFilhos().isEmpty()) {
        } else {
            List<Quadro> listaQuadros = this.somaQuadrosFilhos();
            for (Quadro quadro : listaQuadros) {
                correnteAtivaDem += quadro.getResultados().getCorrenteAtivaDem();
            }
        }
        resultados.setCorrenteAtivaDem(correnteAtivaDem);
    }

    public void correnteReativa() {
        double correnteReativa = 0;
        if (circuitos.isEmpty()) {
        } else {
            correnteReativa = new CorrenteQuadro("Reativa")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        if (this.somaQuadrosFilhos().isEmpty()) {
        } else {
            List<Quadro> listaQuadros = this.somaQuadrosFilhos();
            for (Quadro quadro : listaQuadros) {
                correnteReativa += quadro.getResultados().getCorrenteReativa();
            }
        }
        resultados.setCorrenteReativa(correnteReativa);
    }

    public void correnteReativaDem() {
        double correnteReativaDem = 0;
        if (circuitos.isEmpty()) {
        } else {
            correnteReativaDem = new CorrenteQuadro("ReativaDem")//
                    .withCircuito(circuitos)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        if (this.somaQuadrosFilhos().isEmpty()) {
        } else {
            List<Quadro> listaQuadros = this.somaQuadrosFilhos();
            for (Quadro quadro : listaQuadros) {
                correnteReativaDem += quadro.getResultados().getCorrenteReativaDem();
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

        return condutor.getComprimento() * resultados.getCorrenteAtiva();
    }

    public List<Carga> todasCargas() {
        List<Carga> cargas = new ArrayList<>();
        for (Circuito circuito : this.getCircuitos()) {
            for (Carga carga : circuito.getCargas()) {
                cargas.add(carga);
            }
        }
        return cargas;
    }

    public void faseCondutor() {

        double fase = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteAtiva())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .fase();

        resultados.setFase(fase);

    }

    public void neutroCondutor() {

        double neutro = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteAtiva())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .neutro();

        resultados.setNeutro(neutro);

    }

    public void bitolaCondutor() {

        String bitola = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteAtiva())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .formatado();

        resultados.setBitola(bitola);

    }

    public void terraCondutor() {

        double terra = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor.getMaterial()).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteAtiva())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .terra();

        resultados.setTerra(terra);

    }

    //Define tipo do circuito e Ligação do condutor
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
            switch (tipo) {
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
            for (Quadro quadro : fonte.getQuadros()) {

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

    public List<Quadro> somaQuadrosFilhos() {
        List<Quadro> quadros = new ArrayList<>();
        for (Quadro quadro : FonteService.getById(Ids.getIdFonte()).getQuadros()) {
            try {
                String nome = quadro.getQuadroGeral().nome;
                String esseNome = this.nome;
                System.out.println("Nome: " + quadro.getQuadroGeral().nome + "  Nome desse quadro: " + quadro.getQuadroGeral().nome);
                if (nome.equals(esseNome)) {
                    quadros.add(quadro);
                }
            } catch (Exception e) {

            }

        }
        return quadros;
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
        q.setTipo(tipo);

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
