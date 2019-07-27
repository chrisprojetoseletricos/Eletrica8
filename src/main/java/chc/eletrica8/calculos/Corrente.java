package chc.eletrica8.calculos;

import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.UnidadePotencia;

public class Corrente {

    private Ligacao ligacao;
    private double potencia;
    private double tensaoFN;
    private UnidadePotencia unidade;
    private double fp;
    private double rendimento;

    public double valor() {
        double valor = 0;

        if (ligacao == Ligacao.FFF || ligacao == Ligacao.FFFN) {
            try {
                valor = getPotenciaEmVA() / (3 * tensaoFN * rendimento);
            } catch (Exception e) {
                valor = getPotenciaEmW() / (3 * tensaoFN * fp * rendimento);
            }
        } else {
            try {
                valor = getPotenciaEmVA() / (Math.sqrt(3) * tensaoFN * rendimento);
            } catch (Exception e) {
                valor = getPotenciaEmW() / (Math.sqrt(3) * tensaoFN * fp * rendimento);
            }
        }
        return valor;
    }

    public Corrente withPotencia(double potencia) {
        this.potencia = potencia;
        return this;
    }

    public Corrente withLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
        return this;
    }

    public Corrente withTensao(double tensaoFN) {
        this.tensaoFN = tensaoFN;
        return this;
    }

    public Corrente withUnidade(UnidadePotencia unidade) {
        this.unidade = unidade;
        return this;
    }

    public Corrente withFP(Double fp) {
        this.fp = fp;
        return this;
    }

    public Corrente withRendimento(double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    private double getPotenciaEmVA() {
        return new ConversorPotencia()//
                .withFatorPotencia(fp)//
                .withPotencia(potencia)//
                .withUnidadeOrigem(unidade)//
                .withUnidadeDestino(UnidadePotencia.VA)//
                .converte();
    }

    private double getPotenciaEmW() {
        return new ConversorPotencia()//
                .withFatorPotencia(fp)//
                .withPotencia(potencia)//
                .withUnidadeOrigem(unidade)//
                .withUnidadeDestino(UnidadePotencia.W)//
                .converte();
    }
}
