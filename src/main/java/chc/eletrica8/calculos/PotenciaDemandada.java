package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.UnidadePotencia;

public class PotenciaDemandada {

    private Quadro quadro;
    private UnidadePotencia unidadeDestino;

    public double valor() {
        double valor = 0;

        try {
            for (Circuito c : quadro.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaDemandada(unidadeDestino) * e.getfSimu();
                }
            }
        } catch (Exception e) {
        }
        for (Quadro quadroFinal : quadro.getQuadros()) {

            for (Circuito c : quadroFinal.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaDemandada(unidadeDestino) * e.getfSimu();
                }
            }

        }

        return valor;
    }

    public PotenciaDemandada withQuadro(Quadro quadroParcial) {
        this.quadro = quadroParcial;
        return this;
    }

    public PotenciaDemandada withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }
}
