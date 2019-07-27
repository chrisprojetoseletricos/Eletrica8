/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;

/**
 *
 * @author chris
 */
public class Bitola {

    private Instalacao instalacao;
    private String multipolar;
    private String isolacao;
    private String enterrado;
    private String paramEspecial; //H de horizontal ou V de vestical. ex: PEH3
    private double quedaTensao;
    private int condutoresCarregados;
    private String[][] tabelaCapacidadeCorrente;
    private Circuito circuito;
    private TiposFornecimento fornecimento;
    private String material;

    public String valor() {
        double bitolaCapacidade = 0;
        double bitolaQuedaTensao = 0;
        String bitola = "";

        bitolaQuedaTensao = new BitolaQuedaTensao()//
                .withCircuito(circuito)//
                .withFornecimento(fornecimento)//
                .withMaterial(material)//
                .withQuedaTensao(quedaTensao)//
                .valor();
        
        bitolaCapacidade = Numero.stringToDouble(Matriz.pegaValor(tabelaCapacidadeCorrente, parametro(), circuito.getCorrenteIB(), "BITOLA"), 0);

        if (bitolaCapacidade >= bitolaQuedaTensao) {
            bitola = Numero.decimal(bitolaCapacidade, "##.#");
        } else {
            for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                if (bitolaQuedaTensao <= BitolasMili.getLista().get(i).getNumero()) {
                    bitola = Numero.decimal(BitolasMili.getLista().get(i).getNumero(), "##.#");
                }
            }
        }
        switch (circuito.getCondutor().getLigacao().name()) {
                    case "FFF":
                        bitola = "3 # "+bitola;
                        break;
                    case "FFFN":
                        bitola = "3 # "+bitola+"/ Neutro mm²";
                        break;
                    case "FF":
                        bitola = "2 # "+bitola;
                        break;
                    case "FFN":
                        bitola = "2 # "+bitola+"/ Neutro mm²";
                        break;
                    case "FN":
                        bitola = "1 # "+bitola+"/ Neutro mm²";
                        break;
                    default:
                        break;
                }

        return bitola;
    }

    public Bitola withMaterial(String material) {
        this.material = material;
        return this;
    }

    public Bitola withFornecimento(TiposFornecimento fornecimento) {
        this.fornecimento = fornecimento;
        return this;
    }

    public Bitola withCircuito(Circuito circuito) {
        this.circuito = circuito;
        return this;
    }

    public Bitola withInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
        return this;
    }

    public Bitola withMultipolar(String multipolar) {
        this.multipolar = multipolar;
        return this;
    }

    public Bitola withIsolacao(String isolacao) {
        this.isolacao = isolacao;
        return this;
    }

    public Bitola withEnterrado(String enterrado) {
        this.enterrado = enterrado;
        return this;
    }

    public Bitola withQuedaTensao(double quedaTensao) {
        this.quedaTensao = quedaTensao;
        return this;
    }

    public Bitola withTabelaCapacidadeCorrente(String[][] tabelaCapacidadeCorrente) {
        this.tabelaCapacidadeCorrente = tabelaCapacidadeCorrente;
        return this;
    }

    public Bitola withCondutoresCarregados(int condutoresCarregados) {
        this.condutoresCarregados = condutoresCarregados;
        return this;
    }

    public Bitola withParametroEspecial(String paramEspecial) {
        this.paramEspecial = paramEspecial;
        return this;
    }

    private String parametro() {
        String para = "";
        if (isolacao.equalsIgnoreCase("PVC")) {
            para = "P" + instalacao.name() + paramEspecial + condutoresCarregados;
        } else {
            para = "X" + instalacao.name() + paramEspecial + condutoresCarregados;
        }
        System.out.println("parametro: " + para);
        return para;
    }

    /**
     * @return the instalacao
     */
    public Instalacao getInstalacao() {
        return instalacao;
    }

    /**
     * @param instalacao the instalacao to set
     */
    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    /**
     * @return the multipolar
     */
    public String getMultipolar() {
        return multipolar;
    }

    /**
     * @param multipolar the multipolar to set
     */
    public void setMultipolar(String multipolar) {
        this.multipolar = multipolar;
    }

    /**
     * @return the isolacao
     */
    public String getIsolacao() {
        return isolacao;
    }

    /**
     * @param isolacao the isolacao to set
     */
    public void setIsolacao(String isolacao) {
        this.isolacao = isolacao;
    }

    /**
     * @return the enterrado
     */
    public String getEnterrado() {
        return enterrado;
    }

    /**
     * @param enterrado the enterrado to set
     */
    public void setEnterrado(String enterrado) {
        this.enterrado = enterrado;
    }

    /**
     * @return the quedaTensao
     */
    public double getQuedaTensao() {
        return quedaTensao;
    }

    /**
     * @param quedaTensao the quedaTensao to set
     */
    public void setQuedaTensao(double quedaTensao) {
        this.quedaTensao = quedaTensao;
    }
}
