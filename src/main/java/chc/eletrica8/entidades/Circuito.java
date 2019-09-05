/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.entidades;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.calculos.CorrenteCircuito;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.Tabelas;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
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
@Table(name = "Circuito")
@TableModel
public class Circuito implements Comparable<Circuito>, Serializable, Entidade<Circuito> {

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
    private TiposFornecimento tipo = TiposFornecimento.TRIFASICO;

    public void ordenaDecrListaCarga() {
        Collections.sort(cargas);
    }

    public void correnteAtiva() {
        double correnteAtiva = 0;
        if (cargas.isEmpty()) {
        } else {
            correnteAtiva = new CorrenteCircuito("Ativa")//
                    .withCarga(cargas)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        resultados.setCorrenteAtiva(correnteAtiva);
    }

    public void correnteAtivaDem() {
        double correnteAtivaDem = 0;
        if (cargas.isEmpty()) {
        } else {
            correnteAtivaDem = new CorrenteCircuito("AtivaDem")//
                    .withCarga(cargas)//
                    .withLigacao(resultados.getLigacao())//
                    .valor();
        }
        resultados.setCorrenteAtivaDem(correnteAtivaDem);
    }

    public void potAtivaKW() {

        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaAtiva();
        }
        resultados.setPotAtiva(pot);

        /* double potencia = resultados.getCorrenteAtiva() * resultados.getTensao() * resultados.getFp();

        switch (resultados.getLigacao()) {
            case FFF:
            case FFFN:
                resultados.setPotAtiva(Math.sqrt(3) * potencia);
                break;
            default:
                resultados.setPotAtiva(potencia);
                break;
        }*/
    }

    public void potReativaKVAr() {
        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaReativa();
        }
        resultados.setPotReativa(pot);
    }

    public void potAtivaDemKW() {
        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaAtivaDem();
        }
        resultados.setPotAtivaDem(pot);
    }

    public void potReativaDemKVAr() {
        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaReativaDem();
        }
        resultados.setPotReativaDem(pot);
    }

    public void potAparenteKVA() {
        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaAparente();
        }
        resultados.setPotAparente(pot);
    }

    public void potAparenteDemKVA() {
        double pot = 0;
        for (Carga carga : this.cargas) {
            pot += carga.getPotenciaAparenteDem();
        }
        resultados.setPotAparenteDem(pot);
    }

    public void tensao() {

        try {
            if (resultados.getLigacao().equals(Ligacao.FN)) {
                resultados.setTensao(getQuadro().getFonte().getTensaoFN());
            } else {
                resultados.setTensao(Math.sqrt(3) * getQuadro().getFonte().getTensaoFN());
            }
        } catch (Exception e) {

        }
    }

    public void fatorPotenciaMed() {
        double fp = 0;
        int i = 0;
        for (Carga carga : cargas) {
            fp += carga.getFp();
            i++;
        }
        if (i == 0) {
            resultados.setFp(0);
        } else {
            resultados.setFp(fp / i);
        }
    }

    public void correnteCorr() {
        double correnteIB = resultados.getCorrenteAtivaDem();
        double fatorTemp = Numero.stringToDouble(//
                Matriz.pegarValorMatrizEspecial(new LerCSV(tabelaCorrecaoTemp(condutor).getNome()).toMatriz(),//
                        Integer.toString(condutor.getTemperatura()),//
                        condutor.getIsolacao()), 0);

        double fatorAgrupa = 0;

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

        resultados.setCorrenteCorr(correnteIB / (fatorTemp * fatorAgrupa));
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

    public double LXI() {
        double LXI = 0;
        for (int i = 0; i < cargas.size(); i++) {
            LXI += cargas.get(i).getResultados().getCorrenteAtiva() * cargas.get(i).getComprimentoInstal();
        }
        return LXI;
    }
    
    public Ligacao ligacaoCargaMaior(){
        Ligacao liga = Ligacao.FN;
        double corrente = 0;
        for(Carga carga : cargas){
            if(resultados.getCorrenteAtiva() > corrente){
                liga = carga.getLigacao();
                corrente = resultados.getCorrenteAtiva();
            }
        }
        
        return liga;
    }

    public void faseCondutor() {

        double fase = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacaoCargaMaior())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .fase();

        resultados.setFase(fase);

    }

    public void neutroCondutor() {

        double neutro = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacaoCargaMaior())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .neutro();

        resultados.setNeutro(neutro);

    }

    public void bitolaCondutor() {

        String bitola = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacaoCargaMaior())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .formatado();

        resultados.setBitola(bitola);

    }

    public void terraCondutor() {

        double terra = new Bitola()//
                .withEnterrado(condutor.getEnterrado())//
                .withInstalacao(condutor.getModoInstalacao())//
                .withMultipolar(condutor.getMultipolar())//
                .withQuedaTensao(condutor.getQuedaTensao())//
                .withTabelaCapacidadeCorrente(new LerCSV(tabelaCapacidadeCorr(condutor).getNome()).toMatriz())//
                .withMaterial(condutor.getMaterial())//
                .withIsolacao(condutor.getIsolacao())//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCargas(cargas)//
                .withCorrenteCorr(resultados.getCorrenteCorr())//
                .withFornecimento(tipo)//
                .withUsabilidade(usabilidade)//
                .withLigacao(ligacaoCargaMaior())//
                .withTensaoFN(quadro.getFonte().getTensaoFN())//
                .withLXI(LXI())//
                .withPotAparenteDem(resultados.getPotAparenteDem())//
                .withCurto(curto)//
                .terra();

        resultados.setTerra(terra);

    }

    //Define tipo do circuito e Ligação do condutor
    public void ligacaoCondutor() {
        Ligacao temp = Ligacao.FFF;
        String neutro = "Nao";
        if (!this.getCargas().isEmpty()) {
            for (Carga carga : this.getCargas()) {
                if (carga.getLigacao() == Ligacao.FF || carga.getLigacao() == Ligacao.FFF) {
                } else {
                    neutro = "Sim";
                    break;
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
        }
        resultados.setLigacao(temp);
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
        c.setTipo(tipo);
        c.setUsabilidade(usabilidade);

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

    @Override
    public int compareTo(Circuito outroCircuito) {
        if (resultados.getCorrenteAtiva() > outroCircuito.getResultados().getCorrenteAtiva()) {
            return -1;
        }
        if (resultados.getCorrenteAtiva() < outroCircuito.getResultados().getCorrenteAtiva()) {
            return 1;
        }
        return 0;
    }
}
