package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.QuadroFinal;
import chc.eletrica8.entidades.QuadroParcial;
import chc.eletrica8.enums.UnidadePotencia;



public class PotenciaInstaladaParcial {

    private QuadroParcial quadroParcial;
    private UnidadePotencia unidadeDestino;

    public double valor() {
        double valor = 0;

        try {
            for (Circuito c : quadroParcial.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }
        } catch (Exception e) {
        }

        for (QuadroFinal quadroFinal : quadroParcial.getListaQuadrosFinais()) {

            for (Circuito c : quadroFinal.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }

        }
        return valor;
    }

    public PotenciaInstaladaParcial withQuadro(QuadroParcial quadroParcial) {
        this.quadroParcial = quadroParcial;
        return this;
    }

    public PotenciaInstaladaParcial withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }
}
