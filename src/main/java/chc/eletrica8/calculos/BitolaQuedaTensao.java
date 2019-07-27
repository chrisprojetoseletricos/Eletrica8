/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.enums.TiposFornecimento;

/**
 *
 * @author chris
 */
public class BitolaQuedaTensao {

    private double quedaTensao;
    private String material;
    private Circuito circuito;
    private TiposFornecimento fornecimento;

    public double valor() {
        double valor = 0;
        double LXI = 0;
        double tensao = circuito.getQuadro().getFonte().getTensaoFN();

        if (fornecimento.equals(TiposFornecimento.TRIFASICO)) {
            for (int i = 0; i < circuito.getCargas().size(); i++) {

                LXI += circuito.getCargas().get(i).getCorrenteA() * circuito.getCargas().get(i).getComprimentoInstal();
            }
            if (material.equals("Cobre")) {

                valor = 100 * 0.0178 * LXI / (quedaTensao * tensao);
            } else {
                valor = 100 * 0.0286 * LXI / (quedaTensao * tensao);
            }
        } else if (material.equals("Cobre")) {
            valor = 200 * 0.0178 * LXI / (quedaTensao * tensao);
        } else {
            valor = 200 * 0.0286 * LXI / (quedaTensao * tensao);
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

    public BitolaQuedaTensao withCircuito(Circuito circuito) {
        this.circuito = circuito;
        return this;
    }

    public BitolaQuedaTensao withFornecimento(TiposFornecimento fornecimento) {
        this.fornecimento = fornecimento;
        return this;
    }
}
