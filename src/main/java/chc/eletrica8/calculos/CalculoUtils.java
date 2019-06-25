package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.enums.UnidadePotencia;

public class CalculoUtils {

    public enum MODELO_INSTALACAO {
        INDUSTRIAL, PREDIAL
    }

    private Fonte fonte;
    @SuppressWarnings("unused")
    private MODELO_INSTALACAO modelo;

    public CalculoUtils comFonte(Fonte f) {
        this.fonte = f;
        return this;
    }

    public CalculoUtils comModelo(MODELO_INSTALACAO m) {
        this.modelo = m;
        return this;
    }

    public double getPotenciaDemandada(UnidadePotencia UnidadeDestino) {
        double valor = 0;
        if (modelo.equals(MODELO_INSTALACAO.INDUSTRIAL)){
            valor = fonte.getPotenciaDemandada(UnidadeDestino);
        }
        return valor;
    }

    public double getPotenciaInstalada(UnidadePotencia UnidadeDestino) {

        return fonte.getPotenciaInstalada(UnidadeDestino);
    }
}
