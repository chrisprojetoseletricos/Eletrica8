package chc.eletrica8.calculos;

import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;



public class PotenciaInstaladaCarga {

    private double potencia;
    private UnidadePotencia unidadeOrigem;
    private UnidadePotencia unidadeDestino;
    private double fp;
    private double fu;
    private double fd;
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
        }
        if (usabilidade == Usabilidade.ILUMINACAO_FLUORESCENTE) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = fatorCompensacaoPerdas * this.getPotenciaEmVA();
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = fatorCompensacaoPerdas * this.getPotenciaEmW();
            }
        }
        if (usabilidade == Usabilidade.ILUMINACAO_INCADESCENTE) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA();
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW();
            }
        }
        if (usabilidade == Usabilidade.GERAL) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA();
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW();
            }
        }
        if (usabilidade == Usabilidade.MOTOR) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA()* this.fu / this.rendimento;
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW() * this.fu / this.rendimento;
            }
        }
        if (usabilidade == Usabilidade.EQUIPAMENTOS_ESPECIAIS) {
            if (unidadeDestino == UnidadePotencia.VA) {
                valor = this.getPotenciaEmVA();
            } else if (unidadeDestino == UnidadePotencia.W) {
                valor = this.getPotenciaEmW();
            }
        }

        return valor;
    }

    public PotenciaInstaladaCarga withPotencia(Double potencia) {
        this.potencia = potencia;
        return this;
    }

    public PotenciaInstaladaCarga withUnidadeOrigem(UnidadePotencia unidadeOrigem) {
        this.unidadeOrigem = unidadeOrigem;
        return this;
    }

    public PotenciaInstaladaCarga withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }

    public PotenciaInstaladaCarga withPerdasReator(Double perdasReator) {
        this.perdasReator = perdasReator;
        return this;
    }

    public PotenciaInstaladaCarga withFd(Double fd) {
        this.fd = fd;
        return this;
    }

    public PotenciaInstaladaCarga withFu(Double fu) {
        this.fu = fu;
        return this;
    }

    public PotenciaInstaladaCarga withFp(Double fp) {
        this.fp = fp;
        return this;
    }
    
        public PotenciaInstaladaCarga withRendimento(Double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public PotenciaInstaladaCarga withUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
        return this;
    }

    public PotenciaInstaladaCarga withFatorCompensacaoPerdas(double fator) {
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
