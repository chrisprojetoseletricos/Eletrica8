package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.UnidadePotencia;

public class PotenciaInstaladaQuadro {

    private Quadro quadro;
    private UnidadePotencia unidadeDestino;

    public double valor() {
        double valor = 0;

        try {
            for (Circuito c : quadro.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }
        } catch (Exception e) {
        }

        for (Quadro quadro : quadro.getQuadros()) {

            for (Circuito c : quadro.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }

        }
        return valor;
    }

    public PotenciaInstaladaQuadro withQuadro(Quadro quadroParcial) {
        this.quadro = quadroParcial;
        return this;
    }

    public PotenciaInstaladaQuadro withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }
}
