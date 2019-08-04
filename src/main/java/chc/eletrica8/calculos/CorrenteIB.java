package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import java.util.ArrayList;
import java.util.List;



public class CorrenteIB {

    private final ArrayList<Double> FFF = new ArrayList<>();
    private final ArrayList<Double> FF = new ArrayList<>();
    private final ArrayList<Double> FN = new ArrayList<>();
    private final ArrayList<Integer> cargaSeq = new ArrayList<>();
    private List<Carga> carga = new ArrayList<>();
    private double SOMA_AN = 0;
    private double SOMA_BN = 0;
    private double SOMA_CN = 0;
    private double SOMA_AB = 0;
    private double SOMA_CA = 0;
    private double SOMA_BC = 0;
    private double SOMA_ABC = 0;
    private double TOTAL = 0;
    private int AUX = 0;
    private int FASES_CIRCUITO = 0;

    public CorrenteIB withCarga(List<Carga> carga) {
        this.carga = carga;
        return this;
    }

    public double valor() {
        separaLigacoes();
        separaCorrentesPorCircuito();
        somaCorrentesPorCircuito();

        return TOTAL;
    }

    private void separaLigacoes() {

        for (int i = 0; i < carga.size(); i++) {

            double corrente = carga.get(i).getResultados().getCorrente();
            int fase = carga.get(i).getLigacao().getNumeroFases();

            switch (fase) {
                case 1:
                    FN.add(corrente);
                    cargaSeq.add(1);
                    if (FASES_CIRCUITO < 1) {
                        FASES_CIRCUITO = 1;
                    }
                    break;
                case 2:
                    FF.add(corrente);
                    cargaSeq.add(2);
                    if (FASES_CIRCUITO < 2) {
                        FASES_CIRCUITO = 2;
                    }
                    break;
                case 3:
                    FFF.add(corrente);
                    cargaSeq.add(3);
                    if (FASES_CIRCUITO < 3) {
                        FASES_CIRCUITO = 3;
                    }
                    break;
            }
        }
    }

    private void separaCorrentesPorCircuito() {

        switch (FASES_CIRCUITO) {

            case 1:
                for (int h = 0; h < FN.size(); h++) {
                    SOMA_AN = SOMA_AN + FN.get(h);
                    carga.get(AUX).setLigacaoReal("AN");
                    AUX += 1;
                }
                break;

            case 2:
                int aux4 = 0;
                int aux3 = 0;

                for (int i = 0; i < cargaSeq.size(); i++) {
                    if (cargaSeq.get(i) == 1) {
                        if (SOMA_AN <= SOMA_BN) {
                            SOMA_AN = SOMA_AN + FN.get(aux3);
                            carga.get(AUX).setLigacaoReal("AN");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        } else {
                            SOMA_BN = SOMA_BN + FN.get(aux3);
                            carga.get(AUX).setLigacaoReal("BN");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        }
                        aux3 += 1;
                    } else {
                        SOMA_AB = SOMA_AB + FF.get(aux4);
                        carga.get(AUX).setLigacaoReal("AB");
                        //CargaService.salva(carga.get(AUX));
                        AUX += 1;
                        aux4 += 1;
                    }
                }
                break;

            case 3:
                int aux5 = 0;
                int aux6 = 0;
                int aux7 = 0;

                for (int i = 0; i < cargaSeq.size(); i++) {
                    if (cargaSeq.get(i) == 1) {
                        if (SOMA_AN <= SOMA_BN && SOMA_AN <= SOMA_CN) {
                            SOMA_AN = SOMA_AN + FN.get(aux5);
                            carga.get(AUX).setLigacaoReal("AN");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_BN <= SOMA_AN && SOMA_BN <= SOMA_CN) {
                            SOMA_BN = SOMA_BN + FN.get(aux5);
                            carga.get(AUX).setLigacaoReal("BN");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_CN <= SOMA_AN && SOMA_CN <= SOMA_BN) {
                            SOMA_CN = SOMA_CN + FN.get(aux5);
                            carga.get(AUX).setLigacaoReal("CN");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        }
                        aux5 += 1;
                    } else if (cargaSeq.get(i) == 2) {
                        if (SOMA_AB <= SOMA_BC && SOMA_AB <= SOMA_CA) {
                            SOMA_AB = SOMA_AB + FF.get(aux6);
                            carga.get(AUX).setLigacaoReal("AB");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_BC <= SOMA_AB && SOMA_BC <= SOMA_CA) {
                            SOMA_BC = SOMA_BC + FF.get(aux6);
                            carga.get(AUX).setLigacaoReal("BC");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        } else if (SOMA_CA <= SOMA_AB && SOMA_CA <= SOMA_BC) {
                            SOMA_CA = SOMA_CA + FF.get(aux6);
                            carga.get(AUX).setLigacaoReal("CA");
                            //CargaService.salva(carga.get(AUX));
                            AUX += 1;
                        }
                        aux6 += 1;
                    } else if (cargaSeq.get(i) == 3) {
                        SOMA_ABC = SOMA_ABC + FFF.get(aux7);
                        carga.get(AUX).setLigacaoReal("ABC");
                        //CargaService.salva(carga.get(AUX));
                        AUX += 1;
                        aux7 += 1;
                    }
                }
                break;
        }
    }

    private void somaCorrentesPorCircuito() {

        switch (FASES_CIRCUITO) {

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
