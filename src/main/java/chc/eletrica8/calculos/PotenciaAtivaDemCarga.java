package chc.eletrica8.calculos;

import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;

public class PotenciaAtivaDemCarga {

    private double potencia;
    private UnidadePotencia unidadeOrigem;
    private UnidadePotencia unidadeDestino;
    private double fp;
    private double fu;
    private double fd;
    private double fsimu;
    private Usabilidade usabilidade;
    private double perdasReator;
    private double rendimento;
    private double fatorCompensacaoPerdas = 1;

    public double valor() {
        double valor = 0;

        if (usabilidade == Usabilidade.ILUMINACAO_FLUORESCENTE_PERDAS) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = fatorCompensacaoPerdas * ((this.getPotenciaEmW() * fd) + (perdasReator) / fp);
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = fatorCompensacaoPerdas * ((this.getPotenciaEmW() * fd) + (perdasReator));
            }
        } else if (usabilidade == Usabilidade.MOTOR) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA() * this.fu * this.fsimu / this.rendimento;
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW() * this.fu * this.fsimu / this.rendimento;
            }
        } else if (unidadeDestino == UnidadePotencia.VA) {
            valor = this.getPotenciaEmVA() * fd;
        } else if (unidadeDestino == UnidadePotencia.W) {
            valor = this.getPotenciaEmW() * fd;
        }

        return valor;
    }

    public PotenciaAtivaDemCarga withPotencia(Double potencia) {
        this.potencia = potencia;
        return this;
    }

    public PotenciaAtivaDemCarga withUnidadeOrigem(UnidadePotencia unidadeOrigem) {
        this.unidadeOrigem = unidadeOrigem;
        return this;
    }

    public PotenciaAtivaDemCarga withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }

    public PotenciaAtivaDemCarga withPerdasReator(Double perdasReator) {
        this.perdasReator = perdasReator;
        return this;
    }

    public PotenciaAtivaDemCarga withFd(Double fd) {
        this.fd = fd;
        return this;
    }

    public PotenciaAtivaDemCarga withFp(Double fp) {
        this.fp = fp;
        return this;
    }

    public PotenciaAtivaDemCarga withSimu(Double fsimu) {
        this.fsimu = fsimu;
        return this;
    }

    public PotenciaAtivaDemCarga withRendimento(Double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public PotenciaAtivaDemCarga withFu(Double fu) {
        this.fu = fu;
        return this;
    }

    public PotenciaAtivaDemCarga withUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
        return this;
    }

    public PotenciaAtivaDemCarga withFatorCompensacaoPerdas(double fator) {
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
