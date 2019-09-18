/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Curto;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;
import chc.eletrica8.uteis.codigoTabelaCapacidade;
import java.util.List;

/**
 *
 * @author chris
 */
public class Bitola {

    private Instalacao instalacao;
    private String multipolar;
    private String isolacao;
    private Enterrado enterrado;
    private String paramEspecial; //H de horizontal ou V de vestical. ex: PEH3
    private double quedaTensao;
    private String[][] tabelaCapacidadeCorrente;
    private double correnteCorr;
    private TiposFornecimento fornecimento;
    private String material;
    private Usabilidade usabilidade;
    private List<Carga> cargas;
    private Ligacao ligacao;
    private double tensaoFN;
    private double LXI;
    private double potApaDem;
    private Curto curto;
    private double faseDefDisjuntor;

    public double bitolaMinima(double bitola) {
        double bitolaMin = 0;
        switch (usabilidade) {
            case ILUMINACAO_FLUORESCENTE:
            case ILUMINACAO_FLUORESCENTE_PERDAS:
            case ILUMINACAO_INCADESCENTE:
                if (bitola < 1.5) {
                    bitolaMin = 1.5;
                    break;
                }

            case EQUIPAMENTOS_ESPECIAIS:
            case GERAL:
            case MOTOR:
            case TOMADA_USO_GERAL:
                if (bitola < 2.5) {
                    bitolaMin = 2.5;
                    break;
                }

            default:
                bitolaMin = bitola;
        }
        return bitolaMin;
    }

    public String fase() {
        double bitolaCapacidade = 0;
        double bitolaQuedaTensao = 0;
        double bitolaCurtoCircuito = 0;
        double fase = 0;

        bitolaQuedaTensao = new BitolaQuedaTensao()//
                .withFornecimento(fornecimento)//
                .withMaterial(material)//
                .withQuedaTensao(quedaTensao)//
                .withTensaoFN(tensaoFN)//
                .withLXI(LXI)//
                .valor();

        bitolaCurtoCircuito = new CurtoCircuito()//
                .withIcs(curto.getCorrenteCurto())//
                .withIsolacao(isolacao)//
                .withTe(curto.getTempoElimDef())//
                .bitola();

        bitolaCapacidade = Numero.stringToDouble(Matriz.pegaValor(tabelaCapacidadeCorrente, parametro(), correnteCorr, "BITOLA"), 0);

        if (bitolaCapacidade >= bitolaQuedaTensao && bitolaCapacidade >= bitolaCurtoCircuito) {
            fase = (bitolaCapacidade);

        } else if (bitolaQuedaTensao >= bitolaCapacidade && bitolaQuedaTensao >= bitolaCurtoCircuito) {
            for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                if (bitolaQuedaTensao <= BitolasMili.getLista().get(i).getNumero()) {
                    fase = BitolasMili.getLista().get(i).getNumero();
                    break;
                }
            }
        } else {
            for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                if (bitolaCurtoCircuito <= BitolasMili.getLista().get(i).getNumero()) {
                    fase = BitolasMili.getLista().get(i).getNumero();
                    break;
                }
            }
        }
        if (faseDefDisjuntor >= fase) {
            fase = bitolaMinima(faseDefDisjuntor);
        } else {
            fase = bitolaMinima(fase);
        }

        return Numero.decimal(fase, "0.#");
    }

    public String neutro() {
        double neutro = 0;
        double potApaComNeutro = 0;
        for (Carga carga : cargas) {
            if (!(carga.getLigacao() == Ligacao.FF || carga.getLigacao() == Ligacao.FFF)) {
                potApaComNeutro += carga.getPotenciaAparente();
            }
        }
        double fase = Numero.stringToDouble(fase(), 0);
        //pag136. condicao para o neutro
        if (0.1 * potApaDem < potApaComNeutro) {
            neutro = fase;
        } else {

            if (fase <= 25) {
                neutro = fase;
            } else if (fase == 35) {
                neutro = 25;
            } else if (fase == 50) {
                neutro = 25;
            } else if (fase == 70) {
                neutro = 35;
            } else if (fase == 95) {
                neutro = 50;
            } else if (fase == 120) {
                neutro = 70;
            } else if (fase == 150) {
                neutro = 70;
            } else if (fase == 185) {
                neutro = 95;
            } else if (fase == 240) {
                neutro = 120;
            } else if (fase == 300) {
                neutro = 150;
            } else if (fase == 400) {
                neutro = 185;
            } else if (fase == 500) {
                neutro = 185;
            }
        }
        return Numero.decimal(neutro, "0.#");
    }

    public String terra() {
        double terra = 0;
        double fase = Numero.stringToDouble(fase(), 0);
        if (fase <= 16) {
            terra = fase;
        } else if (fase > 16 && fase <= 35) {
            terra = 16;
        } else if (fase > 35) {
            terra = 0.5 * fase;
        }
        return Numero.decimal(terra, "0.#");
    }

    public String formatado() {
        String bitola = "";

        switch (ligacao.name()) {
            case "FFF":
                bitola = "3#" + fase() + "/-/" + terra() + " mm²";
                break;
            case "FFFN":
                bitola = "3#" + fase() + "/" + neutro() + "/" + terra() + " mm²";
                break;
            case "FF":
                bitola = "2#" + fase() + "/-/" + terra() + " mm²";
                break;
            case "FFN":
                bitola = "2#" + fase() + "/" + neutro() + "/" + terra() + " mm²";
                break;
            case "FN":
                bitola = "1#" + fase() + "/" + neutro() + "/" + terra() + " mm²";
                break;
            default:
                break;
        }

        return bitola;
    }

    public Bitola withMaterial(String material) {
        this.material = material;
        return this;
    }

    public Bitola withFaseDefDisjuntor(double FaseDefDisjuntor) {
        this.faseDefDisjuntor = FaseDefDisjuntor;
        return this;
    }

    public Bitola withUsabilidade(Usabilidade usabilidade) {
        this.usabilidade = usabilidade;
        return this;
    }

    public Bitola withLXI(double LXI) {
        this.LXI = LXI;
        return this;
    }

    public Bitola withFornecimento(TiposFornecimento fornecimento) {
        this.fornecimento = fornecimento;
        return this;
    }

    public Bitola withCorrenteCorr(double correnteCorr) {
        this.correnteCorr = correnteCorr;
        return this;
    }

    public Bitola withTensaoFN(double tensaoFN) {
        this.tensaoFN = tensaoFN;
        return this;
    }

    public Bitola withLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
        return this;
    }

    public Bitola withInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
        return this;
    }

    public Bitola withMultipolar(String multipolar) {
        this.multipolar = multipolar;
        return this;
    }

    public Bitola withIsolacao(String isolacao) {
        this.isolacao = isolacao;
        return this;
    }

    public Bitola withEnterrado(Enterrado enterrado) {
        this.enterrado = enterrado;
        return this;
    }

    public Bitola withQuedaTensao(double quedaTensao) {
        this.quedaTensao = quedaTensao;
        return this;
    }

    public Bitola withCargas(List<Carga> cargas) {
        this.cargas = cargas;
        return this;
    }

    public Bitola withTabelaCapacidadeCorrente(String[][] tabelaCapacidadeCorrente) {
        this.tabelaCapacidadeCorrente = tabelaCapacidadeCorrente;
        return this;
    }

    public Bitola withParametroEspecial(String paramEspecial) {
        this.paramEspecial = paramEspecial;
        return this;
    }

    public Bitola withPotAparenteDem(double valor) {
        this.potApaDem = valor;
        return this;
    }

    public Bitola withCurto(Curto curto) {
        this.curto = curto;
        return this;
    }

    private String parametro() {
        return new codigoTabelaCapacidade()//
                .withInstalacao(instalacao)//
                .withIsolacao(isolacao)//
                .withLigacao(ligacao)//
                .withParametroEspecial(paramEspecial)//
                .cod();
    }
}
