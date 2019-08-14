package chc.eletrica8.calculos;

import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;

public class PotenciaAtivaCarga {

    private double potencia;
    private UnidadePotencia unidadeOrigem;
    private UnidadePotencia unidadeDestino;
    private double fp;
    private double fu;
    private Usabilidade usabilidade;
    private double perdasReator;
    private double rendimento;
    private double fatorCompensacaoPerdas = 1;

    public double valor() {
        double valor = 0;

        if (usabilidade == Usabilidade.ILUMINACAO_FLUORESCENTE_PERDAS) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = fatorCompensacaoPerdas * ((this.getPotenciaEmW()) + (perdasReator) / fp);
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = fatorCompensacaoPerdas * ((this.getPotenciaEmW()) + (perdasReator));
            }
        } else if (usabilidade == Usabilidade.MOTOR) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA() * this.fu / this.rendimento;
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW() * this.fu / this.rendimento;
            }
        } else if (unidadeDestino == UnidadePotencia.VA) {
            valor = this.getPotenciaEmVA();
        } else if (unidadeDestino == UnidadePotencia.W) {
            valor = this.getPotenciaEmW();
        }

        return valor;
    }

    public PotenciaAtivaCarga withPotencia(Double potencia) {
        this.potencia = potencia;
        return this;
    }

    public PotenciaAtivaCarga withUnidadeOrigem(UnidadePotencia unidadeOrigem) {
        this.unidadeOrigem = unidadeOrigem;
        return this;
    }

    public PotenciaAtivaCarga withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }

    public PotenciaAtivaCarga withPerdasReator(Double perdasReator) {
        this.perdasReator = perdasReator;
        return this;
    }

    public PotenciaAtivaCarga withFu(Double fu) {
        this.fu = fu;
        return this;
    }

    public PotenciaAtivaCarga withFp(Double fp) {
        this.fp = fp;
        return this;
    }

    public PotenciaAtivaCarga withRendimento(Double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public PotenciaAtivaCarga withUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
        return this;
    }

    public PotenciaAtivaCarga withFatorCompensacaoPerdas(double fator) {
        this.fatorCompensacaoPerdas = fator;
        return this;
    }

    private double getPotenciaEmVA() {
        return new ConversorPotencia()//
                .withFatorPotencia(fp)//
                .withPotencia(potencia)//
                .withUnidadeOrigem(unidadeOrigem)//
                .withUnidadeDestino(UnidadePotencia.VA)//
                .converte();
    }

    private double getPotenciaEmW() {
        return new ConversorPotencia()//
                .withFatorPotencia(fp)//
                .withPotencia(potencia)//
                .withUnidadeOrigem(unidadeOrigem)//
                .withUnidadeDestino(UnidadePotencia.W)//
                .converte();
    }
}
