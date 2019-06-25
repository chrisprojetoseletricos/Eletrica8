package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.QuadroFinal;
import chc.eletrica8.entidades.QuadroGeral;
import chc.eletrica8.entidades.QuadroParcial;
import chc.eletrica8.enums.UnidadePotencia;



public class PotenciaDemandadaGeral {

    private QuadroGeral quadroGeral;
    private UnidadePotencia unidadeDestino;

    public double valor() {
        double valor = 0;

        try {
            for (Circuito c : quadroGeral.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaDemandada(unidadeDestino) * e.getfSimu();
                }
            }
        } catch (Exception e) {
        }
        for (QuadroParcial quadroParcial : quadroGeral.getListaQuadrosParciais()) {
            try {
                for (Circuito c : quadroParcial.getCircuitos()) {
                    for (Carga e : c.getListaCarga()) {
                        valor += e.getQuantidade() * e.getPotenciaDemandada(unidadeDestino) * e.getfSimu();
                    }
                }
            } catch (Exception e) {
            }
        }
        for (QuadroFinal quadroFinal : quadroGeral.getListaQuadrosFinais()) {

            for (Circuito c : quadroFinal.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaDemandada(unidadeDestino) * e.getfSimu();
                }
            }
        }
        return valor;
    }

    public PotenciaDemandadaGeral withQuadro(QuadroGeral quadroGeral) {
        this.quadroGeral = quadroGeral;
        return this;
    }

    public PotenciaDemandadaGeral withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }
}
