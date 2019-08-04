/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.calculos.CorrenteIB;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
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
@Table(name = "Circuito")
@TableModel
public class Circuito implements Serializable, Entidade<Circuito> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Quadro quadro;
    @Embedded
    private CircuitoResultados resultados = new CircuitoResultados();
    @Embedded
    private Condutor condutor;
    @Embedded
    private Curto curto;
    @OneToMany(mappedBy = "circuito", targetEntity = Carga.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carga> cargas = new ArrayList<>();
    @Column(colName = "Nº", colPosition = 0)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Usabilidade usabilidade;

    public void correnteIB() {
        double correnteIB = 0;
        if (cargas.isEmpty()) {
        } else {
            correnteIB = new CorrenteIB()//
                    .withCarga(getCargas())//
                    .valor();
        }
        resultados.setCorrenteIB(correnteIB);
    }

    public void correnteCorr() {
        double correnteIB = resultados.getCorrenteIB();
        double fator = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV("CorrecaoTemperatura.csv").toMatriz(), Integer.toString(condutor.getTemperatura()), condutor.getIsolacao()), 0);
        resultados.setCorrenteCorr(correnteIB / fator);
    }

    public double LXI() {
        double LXI = 0;
        for (int i = 0; i < cargas.size(); i++) {

            LXI += cargas.get(i).getResultados().getCorrente() * cargas.get(i).getComprimentoInstal();
        }
        return LXI;
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
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
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
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
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
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
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
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withCorrenteIB(resultados.getCorrenteIB())//
                .withFornecimento(resultados.getTipo())//
                .withUsabilidade(usabilidade)//
                .withLigacao(resultados.getLigacao())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
                .withLXI(LXI())//
                .terra();

        resultados.setTerra(terra);

    }

    //Define tipo do circuito e Ligação do condutor
    public void tipoCondutor() {
        TiposFornecimento temp = TiposFornecimento.MONOFASICO;
        if (this.getCargas() != null) {
            for (Carga carga : this.getCargas()) {

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

    public void defineComprimento() {
        resultados.setComprimento(0);
        for (int i = 0; i < cargas.size(); i++) {
            if (cargas.get(i).getComprimentoInstal() >= resultados.getComprimento()) {
                resultados.setComprimento(cargas.get(i).getComprimentoInstal());
                condutor.setComprimento(cargas.get(i).getComprimentoInstal());
            }
        }

    }

    /**
     * @return the resultados
     */
    public CircuitoResultados getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(CircuitoResultados resultados) {
        this.resultados = resultados;
    }

    /**
     * @return the usabilidade
     */
    public Usabilidade getUsabilidade() {
        return usabilidade;
    }

    /**
     * @param usabilidade the usabilidade to set
     */
    public void setUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
    }

    /**
     * @return the cargas
     */
    public List<Carga> getCargas() {
        return cargas;
    }

    /**
     * @param cargas the cargas to set
     */
    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }

    /**
     * @return the quadroParcial
     */
    public Quadro getQuadro() {
        return quadro;
    }

    /**
     * @param quadro the quadroParcial to set
     */
    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    public List<Carga> getListaCarga() {
        return getCargas();

    }

    /**
     * @param listaCarga the listaCarga to set
     */
    public void setListaCarga(List<Carga> listaCarga) {
        this.setCargas(listaCarga);
    }

    public void excluiCarga(Carga carga) {

        List<Carga> lista = getListaCarga();
        if (lista.contains(carga)) {
            lista.remove(carga);
        }
        setListaCarga(lista);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Curto getCurto() {
        return curto;
    }

    public void setCurto(Curto curto) {
        this.curto = curto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
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
        if (!(object instanceof Circuito)) {
            return false;
        }
        Circuito other = (Circuito) object;
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
    public Circuito clonarSemID() {
        Circuito c = copiar();
        c.setId(null);
        return c;
    }

    @Override
    public Circuito copiar() {
        Circuito c = new Circuito();
        c.setId(id);
        c.setNome(nome);
        c.setQuadro(quadro);
        c.setCondutor(condutor);
        c.setCurto(curto);
        c.setResultados(resultados);

        List<Carga> lista = new ArrayList<>();
        for (int i = 0; i < getCargas().size(); i++) {
            Carga ca = new Carga();
            ca = getCargas().get(i).clonarSemID();
            ca.setCircuito(c);
            lista.add(ca);
        }
        c.setListaCarga(lista);
        return c;
    }

    public void apagar() {
        id = 0;
        quadro = null;
        condutor = null;
        curto = null;
        getCargas().clear();
        nome = "";
    }
}
