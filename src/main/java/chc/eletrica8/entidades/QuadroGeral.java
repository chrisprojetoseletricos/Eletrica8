package chc.eletrica8.entidades;


import chc.eletrica8.calculos.PotenciaDemandadaGeral;
import chc.eletrica8.calculos.PotenciaInstaladaGeral;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.TempAmbiente;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.enums.UsoDr;
import chc.eletrica8.servico.tableModel.Column;
import chc.eletrica8.servico.tableModel.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author chris
 */
@Entity
@Table(name = "Quadro")
@TableModel
public class QuadroGeral implements Serializable, Entidade<QuadroGeral> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private Fonte fonte;
    @OneToMany(mappedBy = "quadroGeral", targetEntity = QuadroParcial.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuadroParcial> quadrosParciais = new ArrayList<QuadroParcial>();
    @OneToMany(mappedBy = "quadroGeral", targetEntity = QuadroFinal.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuadroFinal> quadrosFinais = new ArrayList<>();
    @OneToMany(mappedBy = "quadroGeral", targetEntity = Circuito.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Circuito> circuitos = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    private Condutor condutor;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
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
    @Enumerated(EnumType.STRING)
    private TempAmbiente tempAmbiente;
    @Enumerated(EnumType.STRING)
    private Instalacao instalacao;

    public double getPotenciaDemandada(UnidadePotencia unidadeDestino) {
        double total;

        total = new PotenciaDemandadaGeral()//
                .withQuadro(this)//
                .withUnidadeDestino(unidadeDestino)//
                .valor();

        return total;
    }

    public double getPotenciaInstalada(UnidadePotencia unidadeDestino) {
        return new PotenciaInstaladaGeral()//
                .withQuadro(this)//
                .withUnidadeDestino(unidadeDestino)//
                .valor();
    }

    public List<QuadroParcial> getListaQuadrosParciais() {
        return quadrosParciais;
    }

    public void setListaQuadrosParciais(List<QuadroParcial> listaQuadrosParciais) {
        this.quadrosParciais = listaQuadrosParciais;
    }

    public List<QuadroFinal> getListaQuadrosFinais() {
        return quadrosFinais;
    }

    public void setListaQuadrosFinais(List<QuadroFinal> listaQuadrosFinais) {
        this.quadrosFinais = listaQuadrosFinais;
    }

    public Instalacao getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TempAmbiente getTempAmbiente() {
        return tempAmbiente;
    }

    public void setTempAmbiente(TempAmbiente tempAmbiente) {
        this.tempAmbiente = tempAmbiente;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
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

    @Override
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
        if (!(object instanceof QuadroGeral)) {
            return false;
        }
        QuadroGeral other = (QuadroGeral) object;
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
    public QuadroGeral clonarSemID() {
        QuadroGeral q = copiar();
        q.setId(null);
        return q;
    }

    @Override
    public QuadroGeral copiar() {
        QuadroGeral q = new QuadroGeral();
        q.setId(id);
        q.setListaQuadrosFinais(quadrosFinais);
        q.setListaQuadrosParciais(quadrosParciais);
        q.setNome(nome);
        q.setFonte(fonte);
        q.setCondutor(condutor);
        q.setCurto(curto);
        q.setFd(fd);
        q.setFp(fp);
        q.setLocalizacao(localizacao);
        q.setPot100PercDem(pot100PercDem);
        q.setUsabilidade(usabilidade);
        q.setUsoDeDR(usoDeDR);
        q.setInstalacao(instalacao);
        q.setTempAmbiente(tempAmbiente);
        q.setCircuitos(circuitos);

        return q;
    }

    @Override
    public void apagar() {

        id = 0;
        fonte = null;
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
