/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.uteis.Matriz;
import chc.eletrica8.uteis.Numero;
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
    private int condutoresCarregados;
    private String[][] tabelaCapacidadeCorrente;
    private double correnteCorr;
    private double correnteIB;
    private TiposFornecimento fornecimento;
    private String material;
    private Usabilidade usabilidade;
    private List<Carga> cargas;
    private Ligacao ligacao;
    private double tensaoFN;
    private double LXI;

    public double fase() {
        double bitolaCapacidade = 0;
        double bitolaQuedaTensao = 0;
        double fase = 0;

        bitolaQuedaTensao = new BitolaQuedaTensao()//
                .withFornecimento(fornecimento)//
                .withMaterial(material)//
                .withQuedaTensao(quedaTensao)//
                .withTensaoFN(tensaoFN)//
                .withLXI(LXI)//
                .valor();

            bitolaCapacidade = Numero.stringToDouble(Matriz.pegaValor(tabelaCapacidadeCorrente, parametro(), correnteCorr, "BITOLA"), 0);


        if (bitolaCapacidade >= bitolaQuedaTensao) {
            fase = (bitolaCapacidade);
        } else {
            for (int i = 0; i < BitolasMili.getLista().size(); i++) {
                if (bitolaQuedaTensao <= BitolasMili.getLista().get(i).getNumero()) {
                    fase = BitolasMili.getLista().get(i).getNumero();
                    break;
                }
            }
        }

        return fase;
    }

    public double neutro() {
        double neutro = 0;
        if (fase() <= 25) {
            neutro = fase();
        } else if (fase() == 35) {
            neutro = 25;
        } else if (fase() == 50) {
            neutro = 25;
        } else if (fase() == 70) {
            neutro = 35;
        } else if (fase() == 95) {
            neutro = 50;
        } else if (fase() == 120) {
            neutro = 70;
        } else if (fase() == 150) {
            neutro = 70;
        } else if (fase() == 185) {
            neutro = 95;
        } else if (fase() == 240) {
            neutro = 120;
        } else if (fase() == 300) {
            neutro = 150;
        } else if (fase() == 500) {
            neutro = 185;
        }
        return neutro;
    }

    public double terra() {
        double terra = 0;
        if (fase() <= 16) {
            terra = fase();
        } else if (fase() > 16 && fase() <= 35) {
            terra = 16;
        } else if (fase() > 35) {
            terra = 0.5 * fase();
        }
        return terra;
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
    
        public Bitola withCorrenteIB(double correnteIB) {
        this.correnteIB = correnteIB;
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

    public Bitola withCondutoresCarregados(int condutoresCarregados) {
        this.condutoresCarregados = condutoresCarregados;
        return this;
    }

    public Bitola withParametroEspecial(String paramEspecial) {
        this.paramEspecial = paramEspecial;
        return this;
    }

    private String parametro() {
        String para = "";
        if (isolacao.equalsIgnoreCase("PVC")) {
            para = "P" + instalacao.name() + paramEspecial + condutoresCarregados;
        } else {
            para = "X" + instalacao.name() + paramEspecial + condutoresCarregados;
        }
        System.out.println("parametro: " + para);
        return para;
    }
}
