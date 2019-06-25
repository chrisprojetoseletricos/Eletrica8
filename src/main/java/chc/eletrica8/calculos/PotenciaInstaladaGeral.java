package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.QuadroFinal;
import chc.eletrica8.entidades.QuadroGeral;
import chc.eletrica8.entidades.QuadroParcial;
import chc.eletrica8.enums.UnidadePotencia;



public class PotenciaInstaladaGeral {

    private QuadroGeral quadroGeral;
    private UnidadePotencia unidadeDestino;

    public double valor() {
        double valor = 0;

        try {
            for (Circuito c : quadroGeral.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }
        } catch (Exception e) {
        }
        for (QuadroParcial quadroParcial : quadroGeral.getListaQuadrosParciais()) {
            try {
                for (Circuito c : quadroParcial.getCircuitos()) {
                    for (Carga e : c.getListaCarga()) {
                        valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                    }
                }
            } catch (Exception e) {
            }
        }
        for (QuadroFinal quadroFinal : quadroGeral.getListaQuadrosFinais()) {

            for (Circuito c : quadroFinal.getCircuitos()) {
                for (Carga e : c.getListaCarga()) {
                    valor += e.getQuantidade() * e.getPotenciaInstalada(unidadeDestino);
                }
            }
        }
        return valor;
    }

    public PotenciaInstaladaGeral withQuadro(QuadroGeral quadroGeral) {
        this.quadroGeral = quadroGeral;
        return this;
    }

    public PotenciaInstaladaGeral withUnidadeDestino(UnidadePotencia unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }
}
