/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.uteis.Numero;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Fusivel {

    private Circuito circuito;
    private Quadro quadro;
    double K1 = 0;
    private double Ip = 0;
    private double Tsc = 0;
    private double Tp = 0;
    private double correnteMaior = 0;
    private double relPartida = 0;
    private double correntesSoma = 0;
    private String condicaoIsolamento = "false";
    private String condicaoPartida = "false";
    private String valor = "";

    public Fusivel(Circuito circuito) {
        this.circuito = circuito;
    }

    public Fusivel(Quadro quadro) {
        this.quadro = quadro;
    }

    // Menor ou igual a este
    public String valor() {
        if (quadro != null) {
            maiorCargaPorQuadro(quadro);
            switch (quadro.getUsabilidade()) {
                case MOTOR:
                    calculoParaMotor();
                    break;
                case GERAL:
                    calculoParaGeral();
                    condicaoPartida();
                    //condicao de protecao do isolamento pg 361
                    condicaoIsolamento();
                    break;
                default:
                    break;
            }
        } else if (circuito != null) {
            maiorCargaPorCircuito(circuito);
            switch (circuito.getUsabilidade()) {
                case MOTOR:
                    calculoParaMotor();
                    condicaoPartida();
                    //condicao de protecao do isolamento pg 361
                    condicaoIsolamento();
                    break;

                case GERAL:
                    calculoParaGeral();
                    //condicao de protecao do isolamento pg 361
                    condicaoIsolamento();
                    break;
                default:
                    break;
            }
        }
        return valor;
    }

    private void maiorCarga(Carga carga) {
        switch (carga.getUsabilidade()) {
            case MOTOR:
                if (carga.getResultados().getCorrenteAtiva() > correnteMaior) {
                    relPartida = carga.getRelacaoPartidaMotor();
                    correnteMaior = carga.getResultados().getCorrenteAtiva() / carga.getQuantidade();
                    correntesSoma += correnteMaior * carga.getQuantidade();
                    Tp = carga.getTempoPartidaMotor();
                }
                break;
            default:
                correntesSoma += carga.getResultados().getCorrenteAtiva();
                break;
        }
    }

    private void maiorCargaPorCircuito(Circuito circuito) {
        for (Carga carga : circuito.getCargas()) {
            maiorCarga(carga);
        }
    }

    private void maiorCargaPorQuadro(Quadro quadro) {
        for (Circuito subCircuito : quadro.getCircuitos()) {
            maiorCargaPorCircuito(subCircuito);
        }
        for (Quadro subQuadro : quadro.getQuadros()) {
            maiorCargaPorQuadro(subQuadro);
        }
    }

    private void calculoParaMotor() {
        Ip = relPartida * correnteMaior;
        correntesSoma = correntesSoma - correnteMaior;

        if (Ip <= 40) {
            K1 = 0.5;
        } else if (Ip > 40 || Ip <= 500) {
            K1 = 0.4;
        } else {
            K1 = 0.3;
        }
        valor = Numero.decimal((Math.round((K1 * Ip) + correntesSoma)), "#0.0");// Menor ou igual a este
    }

    private void calculoParaGeral() {
        correntesSoma = correntesSoma - correnteMaior;
        valor = Numero.decimal((1.15 * correntesSoma), "#0.0");// o fusivel precisa ser um valor logo acima deste
    }

    private void condicaoPartida() {
        while (condicaoPartida.equals("False")) {

            JOptionPane.showMessageDialog(null, "P/ verificar a condição de partida do motor, ver no gráfico do fusível (<=" + valor + " A)\n "//
                    + "o tempo mínimo na faixa de atuação com corrente de partida (" + Numero.decimal(Ip, "#0.0") + " A)\n"//
                    + "Condição: Tempo > " + Numero.decimal(Tp, "#0.0"));

            switch (JOptionPane.showConfirmDialog(null, "Essa condição de partida satisfaz?")) {
                case 0:
                    //System.out.println("botao yes clicado");
                    condicaoPartida = "True";
                    break;
                case 1:
                    //System.out.println("botao no clicado");
                    JOptionPane.showMessageDialog(null, "Escolha outra classe de fusível.");
                    break;
                case 2:
                    // System.out.println("botao cancel clicado");
                    break;
            }
        }
    }

    private void condicaoIsolamento() {
        while (condicaoIsolamento.equals("false")) {
            calculoTempoDeCurto();//Tsc
            String tempo = "";

            if (circuito != null) {
                switch (circuito.getUsabilidade()) {
                    case MOTOR:
                        tempo = JOptionPane.showInputDialog("FUSÍVEL DO CIRCUITO: " + circuito.getNome()//
                                + "\nEntre com o tempo mínimo de disparo do fusível "//
                                + "\n pelo gráfico do fusível escolhido (menor ou igual a " + valor + " A) para" + "\n"//
                                + "corrente de Curto Circuito:"//
                                + Numero.decimal(circuito.getCurto().getCorrenteCurto(), "#0.0") + " KA");
                        valor = "<=" + valor + " A";
                        break;
                    case GERAL:
                        tempo = JOptionPane.showInputDialog("FUSÍVEL DO CIRCUITO: " + circuito.getNome()//
                                + "\n Entre com o tempo mínimo de disparo do fusível "//
                                + "\n pelo gráfico do fusível escolhido (maior ou igual a " + valor + " A) para" + "\n"//
                                + "corrente de Curto Circuito:"//
                                + Numero.decimal(circuito.getCurto().getCorrenteCurto(), "#.0") + " KA");
                        valor = ">=" + valor + " A";
                        break;
                    default:
                        break;
                }
            } else if (quadro != null) {
                switch (quadro.getUsabilidade()) {
                    case MOTOR:
                        tempo = JOptionPane.showInputDialog("FUSÍVEL DO QUADRO: " + quadro.getNome()//
                                + "\n Entre com o tempo mínimo de disparo do fusível "//
                                + "\n pelo gráfico do fusível escolhido (menor ou igual a " + valor + ") para" + "\n"//
                                + "corrente de Curto Circuito: "//
                                + Numero.decimal(quadro.getCurto().getCorrenteCurto(), "#0.0") + " KA");
                        valor = "<=" + valor + " A";
                        break;
                    case GERAL:
                        tempo = JOptionPane.showInputDialog("FUSÍVEL DO QUADRO: " + quadro.getNome()//
                                + "\n Entre com o tempo mínimo de disparo do fusível "//
                                + "\n pelo gráfico do fusível escolhido (maior ou igual a  " + valor + ") para" + "\n"//
                                + "corrente de Curto Circuito: "//
                                + Numero.decimal(quadro.getCurto().getCorrenteCurto(), "#0.0") + " KA");
                        valor = ">=" + valor + " A";
                        break;
                    default:
                        break;
                }
            }

            if (Numero.stringToDouble(tempo, 0) < Tsc) {
                condicaoIsolamento = "true";
                if (circuito != null) {
                    circuito.getCondutor().setFase(circuito.getResultados().getFase());
                } else if (quadro != null) {
                    quadro.getCondutor().setFase(quadro.getResultados().getFase());
                }
            } else {//e a condicao nao e satisfeita aumenta-se uma bitola
                if (circuito != null) {
                    for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                        if (circuito.getResultados().getFase() == BitolasMili.getLista().get(i).getNumero()) {
                            circuito.getResultados().setFase(BitolasMili.getLista().get(i + 1).getNumero());
                            break;
                        }
                    }
                } else if (quadro != null) {
                    for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                        if (quadro.getResultados().getFase() == BitolasMili.getLista().get(i).getNumero()) {
                            quadro.getResultados().setFase(BitolasMili.getLista().get(i + 1).getNumero());
                            break;
                        }
                    }
                }
            }
        }
    }

    private void calculoTempoDeCurto() {
        Tsc = new CurtoCircuito()//
                .withIcs(circuito.getCurto().getCorrenteCurto())//
                .withIsolacao(circuito.getCondutor().getIsolacao())//
                .withFase(circuito.getResultados().getFase())//
                .tempo();
    }

}
