/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.Tabelas;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.uteis.LerCSV;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;
import chc.eletrica8.uteis.codigoTabelaCapacidade;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class DisjuntorTM {

    private Circuito circuito;
    private Quadro quadro;
    private double fase;
    private Condutor condutor;
    private double correnteProjeto;
    private Usabilidade usabilidade;
    private Ligacao ligacao;
    private double K1 = 0;
    private double Ip = 0;//corrente partida
    private double Ia = 0;//valor assumido
    private double Iz = 0;
    private double Tp = 0;//tempo de partida
    private double correnteMaior = 0;
    private double CS = 0;//corrente de curto circuito
    private double correntesSoma = 0;
    private double relPartida = 0;
    private double FCA;
    private double FCTDisjuntor;
    private double FCTCondutor;
    private String condicaoPartida = "false";
    private String condicaoCurto = "false";
    private String condicaoCapacidadeFase = "false";
    private String valor = "";

    public DisjuntorTM(Circuito circuito) {

        this.circuito = circuito;
        fase();
        condutor();
        usabilidade();
        correnteProjeto();
        ligacao();
    }

    public DisjuntorTM(Quadro quadro) {

        this.quadro = quadro;
        usabilidade();
        ligacao();
        condutor();
        fase();
        correnteProjeto();

    }

    public String valor() {
        if (quadro != null) {
            maiorCargaPorQuadro(quadro);
            switch (quadro.getUsabilidade()) {
                case MOTOR:
                    calculoFatores();
                    condicaoCapacidadeFase();
                    condicaoPartida();
                    //condicao de protecao do isolamento pg 361
                    condicaoIsolamento();
                case GERAL:
                    calculoFatores();
                    condicaoCapacidadeFase();
                    break;
                default:
                    break;
            }
        } else if (circuito != null) {
            maiorCargaPorCircuito(circuito);
            switch (circuito.getUsabilidade()) {
                case MOTOR:
                    calculoFatores();
                    condicaoCapacidadeFase();
                    condicaoPartida();
                    //condicao de protecao do isolamento pg 361
                    condicaoIsolamento();
                    break;

                case GERAL:
                    calculoFatores();
                    condicaoCapacidadeFase();
                    break;
                default:
                    break;
            }
        }
        return valor;
    }

    private void fase() {
        if (circuito != null) {
            fase = 0;
            fase = circuito.getResultados().getFase();
        } else {
            fase = 0;
            fase = quadro.getResultados().getFase();
        }
    }

    private void condutor() {
        if (circuito != null) {
            condutor = circuito.getCondutor();
        } else {
            condutor = quadro.getCondutor();
        }
    }

    private void correnteProjeto() {
        if (circuito != null) {
            correnteProjeto = circuito.getResultados().getCorrenteProjeto();
        } else {
            correnteProjeto = quadro.getResultados().getCorrenteProjeto();
        }
    }

    private void usabilidade() {
        if (circuito != null) {
            usabilidade = circuito.getUsabilidade();
        } else {
            usabilidade = quadro.getUsabilidade();
        }
    }

    private void ligacao() {
        if (circuito != null) {
            ligacao = circuito.getResultados().getLigacao();
        } else {
            ligacao = quadro.getResultados().getLigacao();
        }
    }

    private void maiorCarga(Carga carga) {
        switch (carga.getUsabilidade()) {
            case MOTOR:
                if (carga.getResultados().getCorrenteAtiva() > correnteMaior) {
                    relPartida = carga.getRelacaoPartidaMotor();
                    correnteMaior = carga.getResultados().getCorrenteAtiva() / carga.getQuantidade();
                    correntesSoma += correnteMaior * carga.getQuantidade();
                    Tp = carga.getTempoPartidaMotor();
                    CS = carga.getCS();
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

    private void condicaoCapacidadeFase() {
        while (condicaoCapacidadeFase.equals("false")) {
            switch (usabilidade) {
                case MOTOR:
                case EQUIPAMENTOS_ESPECIAIS:
                    Iz = 1.45 * capacidadeFase() * FCA * FCTCondutor;
                    break;
                default:
                    Iz = capacidadeFase() * FCA * FCTCondutor;
                    break;
            }

            if (FCTDisjuntor <= 1) {
                Ia = correnteProjeto / (FCTDisjuntor);
            } else {
                Ia = correnteProjeto;
            }

            if (Ia <= Iz) {
                condicaoCapacidadeFase = "true";
                valor = Numero.decimal(correnteProjeto, "#.0") + " - " + Numero.decimal(capacidadeFase() * FCA * FCTCondutor, "#.0");
                condutor.setFase(fase);
            } else {
                for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                    if (fase == BitolasMili.getLista().get(i).getNumero()) {
                        fase = BitolasMili.getLista().get(i + 1).getNumero();
                        break;
                    }
                }
            }
        }
    }

    private void condicaoPartida() {
        while (condicaoPartida.equals("false")) {
            //condicao de partida do motor
            Ip = relPartida * correnteMaior;
            double M = (Ip / Ia);
            String tempo1 = "";
            if (circuito != null) {
                tempo1 = JOptionPane.showInputDialog("DISJUNTOR DO CIRCUITO: " + circuito.getNome() + "\n Entre com o tempo de disparo do maior motor " + "\n" + "dado pelo gráfico do disjuntor escolhido" + "\n" + "para ajuste térmico do disjuntor " + "\n" + Numero.decimal(Ia, "#.0") + "A com M = " + Numero.decimal(M, "#.0"));
            } else {
                tempo1 = JOptionPane.showInputDialog("DISJUNTOR DO QUADRO: " + quadro.getNome() + "\n Entre com o tempo de disparo do maior motor " + "\n" + "dado pelo gráfico do disjuntor escolhido" + "\n" + "para ajuste térmico do disjuntor " + "\n" + Numero.decimal(Ia, "#.0") + "A com M = " + Numero.decimal(M, "#.0"));
            }
            if (Numero.stringToDouble(tempo1, 0) > Tp) {
                condicaoPartida = "true";
                valor = Numero.decimal(correnteProjeto, "#.0") + " - " + Numero.decimal(capacidadeFase() * FCA * FCTCondutor, "#.0");
                condutor.setFase(fase);
            } else {
                JOptionPane.showMessageDialog(null, "Disjuntor não atende!\n Encontre outro disjuntor com uma curva de tempo maior.");
            }
        }
    }

    private void condicaoIsolamento() {
        while (condicaoCurto.equals("false")) {
            double Tsc = new CurtoCircuito()//
                    .withFase(fase)//
                    .withIcs(CS)//
                    .withIsolacao(condutor.getIsolacao())//
                    .tempo();

            double M2 = (CS * 1000 / Ia);
            String tempo2 = "";
            if (circuito != null) {
                tempo2 = JOptionPane.showInputDialog("DISJUNTOR DO CIRCUITO: " + circuito.getNome() + "\n Entre com o tempo de disparo do maior motor dado " + "\n(" + Numero.decimal(Ia, "#.0") + "A) pelo gráfico do disjuntor escolhido para" + "\n" + "curto circuito com M = " + Numero.decimal(M2, "#.0"));
            } else {
                tempo2 = JOptionPane.showInputDialog("DISJUNTOR DO QUADRO: " + quadro.getNome() + "\n Entre com o tempo de disparo do maior motor dado " + "\n(" + Numero.decimal(Ia, "#.0") + "A) pelo gráfico do disjuntor escolhido para" + "\n" + "curto circuito com M = " + Numero.decimal(M2, "#.0"));
            }
            if (Numero.stringToDouble(tempo2, 0) < Tsc) {
                condicaoCurto = "true";
                valor = Numero.decimal(correnteProjeto, "#.0") + " - " + Numero.decimal(capacidadeFase() * FCA * FCTCondutor, "#.0");
                condutor.setFase(fase);
            } else {
                for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                    if (fase == BitolasMili.getLista().get(i).getNumero()) {
                        fase = BitolasMili.getLista().get(i + 1).getNumero();
                        break;
                    }
                }
            }
        }
    }

    private double capacidadeFase() {
        double valor = 0;
        valor = Numero.stringToDouble(Matriz.pegarValorMatrizEspecial(new LerCSV(Tabelas.CapaciCorrBaixaCobre.getNome()).toMatriz(),//
                Numero.decimal(fase, "0.#"),//
                parametro()), 0);
        return valor;
    }

    private String parametro() {
        return new codigoTabelaCapacidade()//
                .withInstalacao(condutor.getModoInstalacao())//
                .withIsolacao(condutor.getIsolacao())//
                .withLigacao(ligacao)//
                .withParametroEspecial("")//
                .cod();
    }

    private void calculoFatores() {
        //condicao do segundo livro usando K. pag 338
        FCA = new Fator(condutor).FCA();
        FCTCondutor = new Fator(condutor).FCT();

        if (circuito != null) {
            FCTDisjuntor = new Fator(condutor).FCT(circuito.getQuadro().getTempAmbiente());
        } else {
            FCTDisjuntor = new Fator(condutor).FCT(quadro.getTempAmbiente());
        }
    }

}
