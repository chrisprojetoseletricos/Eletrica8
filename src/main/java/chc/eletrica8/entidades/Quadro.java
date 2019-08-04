package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.calculos.PotenciaDemandadaQuadro;
import chc.eletrica8.calculos.PotenciaInstaladaQuadro;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.enums.UsoDr;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import chc.eletrica8.uteis.LerCSV;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;
import java.io.Serializable;
import java.util.ArrayList;
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
    private double fd = 1;
    private double fp = 1;
    @Column(colName = "Local", colPosition = 1)
    private String localizacao;
    @Column(colName = "Nome", colPosition = 0)
    private String nome;
    private double pot100PercDem;
    @Enumerated(EnumType.STRING)
    private Usabilidade usabilidade;
    private int tempAmbiente;

    public double getPotenciaDemandada(UnidadePotencia unidadeDestino) {
        double total;

        total = new PotenciaDemandadaQuadro()//
                .withQuadro(this)//
                .withUnidadeDestino(unidadeDestino)//
                .valor();

        return total;
    }

    public double getPotenciaInstalada(UnidadePotencia unidadeDestino) {
        return new PotenciaInstaladaQuadro()//
                .withQuadro(this)//
                .withUnidadeDestino(unidadeDestino)//
                .valor();
    }

    public void correnteIB() {
        double correnteIB = 0;
        for (Circuito cir : circuitos) {
            correnteIB += cir.getResultados().getCorrenteIB();
        }
        resultados.setCorrenteIB(correnteIB);
    }

    public void correnteCorr() {
        double correnteIB = resultados.getCorrenteIB();
        double fator = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV("CorrecaoTemperatura.csv").toMatriz(), Integer.toString(condutor.getTemperatura()), condutor.getIsolacao()), 0);
        resultados.setCorrenteCorr(correnteIB / fator);
    }

    public double LXI() {

        return condutor.getComprimento() * resultados.getCorrenteIB();
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
                .withTabelaCapacidadeCorrente(new LerCSV("CCBaixaCobre.csv").toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
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
                .withTabelaCapacidadeCorrente(new LerCSV("CCBaixaCobre.csv").toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
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
                .withTabelaCapacidadeCorrente(new LerCSV("CCBaixaCobre.csv").toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
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
                .withTabelaCapacidadeCorrente(new LerCSV("CCBaixaCobre.csv").toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withCondutoresCarregados(resultados.numCirCarregados())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(todasCargas())//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(fonte.getTensaoFN())//
                .withLXI(LXI())//
                .terra();

        resultados.setTerra(terra);

    }

    //Define tipo do circuito e Ligação do condutor
    public void tipoCondutor() {
        TiposFornecimento temp = TiposFornecimento.MONOFASICO;
        if (todasCargas() != null) {
            for (Carga carga : todasCargas()) {

                switch (carga.getLigacao()) {
                    case FFF:
                        temp = TiposFornecimento.TRIFASICO;
                        resultados.setLigacao(Ligacao.FFF);
                        break;
                    case FFFN:
                        temp = TiposFornecimento.TRIFASICO;
                        resultados.setLigacao(Ligacao.FFFN);
                        break;
                    case FF:
                        if (temp.equals(TiposFornecimento.MONOFASICO)) {
                            temp = TiposFornecimento.BIFASICO;
                            resultados.setLigacao(Ligacao.FF);
                        }
                        break;
                    case FFN:
                        if (temp.equals(TiposFornecimento.MONOFASICO)) {
                            temp = TiposFornecimento.BIFASICO;
                            resultados.setLigacao(Ligacao.FFN);
                        }
                        break;
                    case FN:
                        if (temp.equals(TiposFornecimento.MONOFASICO)) {
                            temp = TiposFornecimento.MONOFASICO;
                            resultados.setLigacao(Ligacao.FN);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        resultados.setTipo(temp);
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

    public double getFd() {
        return fd;
    }

    public void setFd(double fd) {
        this.fd = fd;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
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
        q.setFd(fd);
        q.setFp(fp);
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
        fd = 1;
        fp = 1;
        localizacao = "";
        nome = "";
        pot100PercDem = 0;
        usabilidade = null;
    }
}
