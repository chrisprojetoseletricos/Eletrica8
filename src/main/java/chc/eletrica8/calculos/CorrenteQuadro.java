package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.enums.Ligacao;
import java.util.ArrayList;
import java.util.List;

public class CorrenteQuadro {

    private final ArrayList<Double> FFF = new ArrayList<>();
    private final ArrayList<Double> FF = new ArrayList<>();
    private final ArrayList<Double> FN = new ArrayList<>();
    private final ArrayList<Integer> SeqLigacao = new ArrayList<>();
    private List<Circuito> circuito = new ArrayList<>();
    private double SOMA_AN = 0;
    private double SOMA_BN = 0;
    private double SOMA_CN = 0;
    private double SOMA_AB = 0;
    private double SOMA_CA = 0;
    private double SOMA_BC = 0;
    private double SOMA_ABC = 0;
    private double TOTAL = 0;
    private int AUX = 0;
    private int FASES_QUADRO = 0;
    private String tipoCorrente;

    public CorrenteQuadro(String tipoCorrente) {
        this.tipoCorrente = tipoCorrente;
    }

    public CorrenteQuadro withCircuito(List<Circuito> circuito) {
        this.circuito = circuito;
        return this;
    }

    public CorrenteQuadro withLigacao(Ligacao ligacao) {
        this.FASES_QUADRO = ligacao.getNumeroFases();
        return this;
    }

    public double valor() {
        separaLigacoes();
        separaCorrentesPorCircuito();
        somaCorrentesPorCircuito();

        return TOTAL;
    }

    private void separaLigacoes() {

        for (int i = 0; i < circuito.size(); i++) {
            double corrente = 0;

            switch (tipoCorrente) {
                case "Ativa":
                    corrente = circuito.get(i).getResultados().getCorrenteAtiva();
                    break;
                case "AtivaDem":
                    corrente = circuito.get(i).getResultados().getCorrenteAtivaDem();
                    break;

                default:
                    break;
            }

            int fase = circuito.get(i).getResultados().getLigacao().getNumeroFases();

            switch (fase) {
                case 1:
                    FN.add(corrente);
                    SeqLigacao.add(1);
                    break;
                case 2:
                    FF.add(corrente);
                    SeqLigacao.add(2);
                    break;
                case 3:
                    FFF.add(corrente);
                    SeqLigacao.add(3);
                    break;
            }
        }
    }

    private void separaCorrentesPorCircuito() {

        switch (FASES_QUADRO) {

            case 1:
                for (int h = 0; h < FN.size(); h++) {
                    SOMA_AN = SOMA_AN + FN.get(h);
                    circuito.get(AUX).getResultados().setLigacaoReal("AN");
                    AUX += 1;
                }
                break;

            case 2:
                int aux4 = 0;
                int aux3 = 0;

                for (int i = 0; i < SeqLigacao.size(); i++) {
                    if (SeqLigacao.get(i) == 1) {
                        if (SOMA_AN <= SOMA_BN) {
                            SOMA_AN = SOMA_AN + FN.get(aux3);
                            circuito.get(AUX).getResultados().setLigacaoReal("AN");
                            AUX += 1;
                        } else {
                            SOMA_BN = SOMA_BN + FN.get(aux3);
                            circuito.get(AUX).getResultados().setLigacaoReal("BN");
                            AUX += 1;
                        }
                        aux3 += 1;
                    } else {
                        SOMA_AB = SOMA_AB + FF.get(aux4);
                        circuito.get(AUX).getResultados().setLigacaoReal("AB");
                        AUX += 1;
                        aux4 += 1;
                    }
                }
                break;

            case 3:
                int aux5 = 0;
                int aux6 = 0;
                int aux7 = 0;

                for (int i = 0; i < SeqLigacao.size(); i++) {
                    if (SeqLigacao.get(i) == 1) {
                        if (SOMA_AN <= SOMA_BN && SOMA_AN <= SOMA_CN) {
                            SOMA_AN = SOMA_AN + FN.get(aux5);
                            circuito.get(AUX).getResultados().setLigacaoReal("AN");
                            //CircuitoService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_BN <= SOMA_AN && SOMA_BN <= SOMA_CN) {
                            SOMA_BN = SOMA_BN + FN.get(aux5);
                            circuito.get(AUX).getResultados().setLigacaoReal("BN");
                            AUX += 1;
                        } else if (SOMA_CN <= SOMA_AN && SOMA_CN <= SOMA_BN) {
                            SOMA_CN = SOMA_CN + FN.get(aux5);
                            circuito.get(AUX).getResultados().setLigacaoReal("CN");
                            AUX += 1;
                        }
                        aux5 += 1;
                    } else if (SeqLigacao.get(i) == 2) {
                        if (SOMA_AB <= SOMA_BC && SOMA_AB <= SOMA_CA) {
                            SOMA_AB = SOMA_AB + FF.get(aux6);
                            circuito.get(AUX).getResultados().setLigacaoReal("AB");
                            //CircuitoService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_BC <= SOMA_AB && SOMA_BC <= SOMA_CA) {
                            SOMA_BC = SOMA_BC + FF.get(aux6);
                            circuito.get(AUX).getResultados().setLigacaoReal("BC");
                            //CircuitoService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_CA <= SOMA_AB && SOMA_CA <= SOMA_BC) {
                            SOMA_CA = SOMA_CA + FF.get(aux6);
                            circuito.get(AUX).getResultados().setLigacaoReal("CA");
                            AUX += 1;
                        }
                        aux6 += 1;
                    } else if (SeqLigacao.get(i) == 3) {
                        SOMA_ABC = SOMA_ABC + FFF.get(aux7);
                        circuito.get(AUX).getResultados().setLigacaoReal("ABC");
                        AUX += 1;
                        aux7 += 1;
                    }
                }
                break;
        }
    }

    private void somaCorrentesPorCircuito() {

        switch (FASES_QUADRO) {

            case 1:
                TOTAL = SOMA_AN;
                break;

            case 2:
                if (SOMA_AN >= SOMA_BN) {
                    TOTAL = SOMA_AB + SOMA_AN;
                } else {
                    TOTAL = SOMA_AB + SOMA_BN;
                }
                break;

            case 3:
                AUX = 0;
                boolean uso = false;

                if (SOMA_AN >= SOMA_BN && SOMA_AN >= SOMA_CN && uso == false) {
                    if (SOMA_AB >= SOMA_BC && SOMA_AB >= SOMA_CA) {
                        TOTAL = SOMA_ABC + SOMA_AB + SOMA_AN;
                    }
                    uso = true;
                }
                if (SOMA_AN >= SOMA_BN && SOMA_AN >= SOMA_CN && uso == false) {
                    if (SOMA_BC >= SOMA_AB && SOMA_BC >= SOMA_CA) {
                        TOTAL = SOMA_ABC + SOMA_BC + SOMA_AN;
                    }
                    uso = true;
                }
                if (SOMA_AN >= SOMA_BN && SOMA_AN >= SOMA_CN && uso == false) {
                    if (SOMA_CA >= SOMA_AB && SOMA_CA >= SOMA_BC) {
                        TOTAL = SOMA_ABC + SOMA_CA + SOMA_AN;
                    }
                } else {
                    if (SOMA_BN >= SOMA_AN && SOMA_BN >= SOMA_CN && uso == false) {
                        if (SOMA_AB >= SOMA_BC && SOMA_AB >= SOMA_CA) {
                            TOTAL = SOMA_ABC + SOMA_AB + SOMA_BN;
                        }
                        uso = true;
                    }
                    if (SOMA_BN >= SOMA_AN && SOMA_BN >= SOMA_CN && uso == false) {
                        if (SOMA_BC >= SOMA_AB && SOMA_BC >= SOMA_CA) {
                            TOTAL = SOMA_ABC + SOMA_BC + SOMA_BN;
                        }
                        uso = true;
                    }
                    if (SOMA_BN >= SOMA_AN && SOMA_BN >= SOMA_CN && uso == false) {
                        if (SOMA_CA >= SOMA_AB && SOMA_CA >= SOMA_BC) {
                            TOTAL = SOMA_ABC + SOMA_CA + SOMA_BN;
                        }
                    } else {
                        if (SOMA_CN >= SOMA_AN && SOMA_CN >= SOMA_BN && uso == false) {
                            if (SOMA_AB >= SOMA_BC && SOMA_AB >= SOMA_CA) {
                                TOTAL = SOMA_ABC + SOMA_AB + SOMA_CN;
                            }
                            uso = true;
                        }
                        if (SOMA_CN >= SOMA_AN && SOMA_CN >= SOMA_BN && uso == false) {
                            if (SOMA_BC >= SOMA_AB && SOMA_BC >= SOMA_CA) {
                                TOTAL = SOMA_ABC + SOMA_BC + SOMA_CN;
                            }
                            uso = true;
                        }
                        if (SOMA_CN >= SOMA_AN && SOMA_CN >= SOMA_BN && uso == false) {
                            if (SOMA_CA >= SOMA_AB && SOMA_CA >= SOMA_BC) {
                                TOTAL = SOMA_ABC + SOMA_CA + SOMA_CN;
                            }
                        }
                    }
                }
                break;
        }
    }
}
