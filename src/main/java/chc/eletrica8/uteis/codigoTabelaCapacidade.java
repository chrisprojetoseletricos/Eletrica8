/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.uteis;

import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;

/**
 *
 * @author chris
 */
public class codigoTabelaCapacidade {

    private String isolacao;
    private Instalacao instalacao;
    private String paramEspecial; //H de horizontal ou V de vestical. ex: PEH3
    private Ligacao ligacao;

    public String cod() {
        String para = "";
        if (isolacao.equalsIgnoreCase("PVC")) {
            para = "P" + instalacao.name() + paramEspecial + ligacao.getNumeroCondutCarregados();
        } else {
            para = "X" + instalacao.name() + paramEspecial + ligacao.getNumeroCondutCarregados();
        }
        System.out.println("parametro: " + para);

        return para;
    }

    public codigoTabelaCapacidade withIsolacao(String isolacao) {
        this.isolacao = isolacao;
        return this;
    }

    public codigoTabelaCapacidade withInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
        return this;
    }

    public codigoTabelaCapacidade withParametroEspecial(String paramEspecial) {
        this.paramEspecial = paramEspecial;
        return this;
    }

    public codigoTabelaCapacidade withLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
        return this;
    }
}
