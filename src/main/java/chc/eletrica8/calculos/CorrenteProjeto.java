/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.Usabilidade;
import static chc.eletrica8.enums.Usabilidade.EQUIPAMENTOS_ESPECIAIS;
import static chc.eletrica8.enums.Usabilidade.MOTOR;
import java.util.List;

/**
 *
 * @author chris
 */
public class CorrenteProjeto {

    private Quadro quadro;
    private Circuito circuito;
    private Usabilidade usabilidade;
    private List<Carga> cargas;

    public CorrenteProjeto() {
        //quadro = new Quadro();
        //circuito = new Circuito();

    }

    public void calcula() {
        quadroOuCircuito();
        switch (usabilidade) {
            case MOTOR:
            case EQUIPAMENTOS_ESPECIAIS:
                double corrente = 0;
                double correnteMaior = 0;

                for (Carga carga : cargas) {
                    if (carga.getResultados().getCorrenteAtiva() / carga.getQuantidade() > correnteMaior) {
                        correnteMaior = carga.getResultados().getCorrenteAtiva() / carga.getQuantidade();
                    }
                }
                if (circuito != null) {
                    corrente = circuito.getResultados().getCorrenteAtiva() - correnteMaior;
                    corrente = (correnteMaior) + corrente;//1.25 * correnteMaior
                    circuito.getResultados().setCorrenteProjeto(corrente);
                } else {
                    corrente = quadro.getResultados().getCorrenteAtiva() - correnteMaior;
                    corrente = (correnteMaior) + corrente;//1.25 * correnteMaior
                    quadro.getResultados().setCorrenteProjeto(corrente);
                }
                break;

            default:
                if (circuito != null) {
                    circuito.getResultados().setCorrenteProjeto(circuito.getResultados().getCorrenteAtiva());
                } else {
                    quadro.getResultados().setCorrenteProjeto(quadro.getResultados().getCorrenteAtiva());
                }
                break;
        }
    }

    private void quadroOuCircuito() {

        if (circuito != null) {
            usabilidade = circuito.getUsabilidade();
            cargas = circuito.getCargas();

        } else {
            usabilidade = quadro.getUsabilidade();
            cargas = quadro.todasCargas();
        }
    }

    public CorrenteProjeto withQuadro(Quadro quadro) {
        this.quadro = quadro;
        return this;
    }

    public CorrenteProjeto withCircuito(Circuito circuito) {
        this.circuito = circuito;
        return this;
    }

}
