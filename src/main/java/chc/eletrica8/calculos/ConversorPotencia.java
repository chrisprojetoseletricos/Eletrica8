package chc.eletrica8.calculos;

import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.UnidadePotencia;

public class ConversorPotencia {

    private double fatorPotencia;
    private double rendimento;
    private double fu;
    private double potencia;
    private UnidadePotencia unidadeDestino;
    private UnidadePotencia unidadeOrigem;
    private final double WATT_BTU = 0.29307107;
    private final double WATT_CV = 735.499;
    private final double WATT_HP = 745.699872;

    public double converte() {

        if (unidadeOrigem == unidadeDestino) {
            return potencia;
        }

        if (unidadeOrigem == UnidadePotencia.W && unidadeDestino == UnidadePotencia.CV) {
            return potencia * fu * rendimento / WATT_CV;
        }
        if (unidadeOrigem == UnidadePotencia.VA && unidadeDestino == UnidadePotencia.W) {
            return potencia * fatorPotencia;
        }
        if (unidadeOrigem == UnidadePotencia.CV && unidadeDestino == UnidadePotencia.W) {
            return potencia * fu * WATT_CV / rendimento;
        }
        if (unidadeOrigem == UnidadePotencia.BTU && unidadeDestino == UnidadePotencia.W) {
            return potencia * WATT_BTU;
        }
        if (unidadeOrigem == UnidadePotencia.W && unidadeDestino == UnidadePotencia.BTU) {
            return potencia / WATT_BTU;
        }
        if (unidadeOrigem == UnidadePotencia.HP && unidadeDestino == UnidadePotencia.W) {
            return potencia * fu * WATT_HP / rendimento;
        }
        if (unidadeOrigem == UnidadePotencia.W && unidadeDestino == UnidadePotencia.HP) {
            return potencia * fu * rendimento / WATT_HP;
        }
        if (unidadeOrigem == UnidadePotencia.W && unidadeDestino == UnidadePotencia.VA) {
            return potencia / fatorPotencia;
        }
        if (unidadeOrigem == UnidadePotencia.BTU && unidadeDestino == UnidadePotencia.VA) {
            return potencia * WATT_BTU / fatorPotencia;
        }
        if (unidadeOrigem == UnidadePotencia.CV && unidadeDestino == UnidadePotencia.VA) {
            return potencia * fu * WATT_CV / (fatorPotencia * rendimento);
        }
        if (unidadeOrigem == UnidadePotencia.HP && unidadeDestino == UnidadePotencia.VA) {
            return potencia * fu * WATT_HP / (fatorPotencia * rendimento);
        }

        throw new RuntimeException(
                "Nao foi possivel converter a potencia de " + unidadeOrigem + " para " + unidadeDestino);
    }

    public ConversorPotencia withFatorPotencia(Double fatorPotencia) {
        this.fatorPotencia = fatorPotencia;
        return this;
    }

    public ConversorPotencia withPotencia(Double potencia) {
        this.potencia = potencia;
        return this;
    }

    public ConversorPotencia withFu(Double fu) {
        this.fu = fu;
        return this;
    }

    public ConversorPotencia withRendimento(Double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public ConversorPotencia withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }

    public ConversorPotencia withUnidadeOrigem(UnidadePotencia unidadeOrigem) {
        this.unidadeOrigem = unidadeOrigem;
        return this;
    }
}
