/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.enums.TiposFornecimento;

/**
 *
 * @author chris
 */
public class BitolaQuedaTensao {

    private double quedaTensao;
    private double tensaoFN;
    private String material;
    private TiposFornecimento fornecimento;
    private double LXI;

    public double valor() {
        double valor = 0;

        if (fornecimento.equals(TiposFornecimento.TRIFASICO)) {

            if (material.equals("Cobre")) {

                valor = 100 * 0.0178 * LXI / (quedaTensao * tensaoFN);
            } else {
                valor = 100 * 0.0286 * LXI / (quedaTensao * tensaoFN);
            }
        } else if (material.equals("Cobre")) {
            valor = 200 * 0.0178 * LXI / (quedaTensao * tensaoFN);
        } else {
            valor = 200 * 0.0286 * LXI / (quedaTensao * tensaoFN);
        }

        return valor;
    }

    public BitolaQuedaTensao withQuedaTensao(double quedaTensao) {
        this.quedaTensao = quedaTensao;
        return this;
    }

    public BitolaQuedaTensao withMaterial(String material) {
        this.material = material;
        return this;
    }

    public BitolaQuedaTensao withTensaoFN(double tensaoFN) {
        this.tensaoFN = tensaoFN;
        return this;
    }

    public BitolaQuedaTensao withFornecimento(TiposFornecimento fornecimento) {
        this.fornecimento = fornecimento;
        return this;
    }

    public BitolaQuedaTensao withLXI(double LXI) {
        this.LXI = LXI;
        return this;
    }
}
