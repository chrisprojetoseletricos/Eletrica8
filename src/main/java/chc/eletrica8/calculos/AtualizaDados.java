/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.servico.CargaService;
import chc.eletrica8.servico.CircuitoService;
import chc.eletrica8.servico.QuadroService;

/**
 *
 * @author chris
 */
public class AtualizaDados {

    public static void carga(Carga carga) {
        carga.tensao();
        carga.corrente();
        carga.getCircuito().tipoCondutor();
        carga.getCircuito().defineComprimento();
        carga.getCircuito().correnteIB();
        carga.getCircuito().correnteCorr();
        carga.getCircuito().faseCondutor();
        carga.getCircuito().neutroCondutor();
        carga.getCircuito().terraCondutor();
        carga.getCircuito().bitolaCondutor();
        carga.getCircuito().getQuadro().tipoCondutor();
        carga.getCircuito().getQuadro().correnteIB();
        carga.getCircuito().getQuadro().correnteCorr();
        carga.getCircuito().getQuadro().faseCondutor();
        carga.getCircuito().getQuadro().neutroCondutor();
        carga.getCircuito().getQuadro().terraCondutor();
        carga.getCircuito().getQuadro().bitolaCondutor();

        CargaService.salva(carga);
        CircuitoService.salva(carga.getCircuito());
        QuadroService.salva(carga.getCircuito().getQuadro());
    }

    public static void circuito(Circuito circuito) {

        circuito.tipoCondutor();
        circuito.defineComprimento();
        circuito.correnteIB();
        circuito.correnteCorr();
        circuito.faseCondutor();
        circuito.neutroCondutor();
        circuito.terraCondutor();
        circuito.bitolaCondutor();
        circuito.getQuadro().tipoCondutor();
        circuito.getQuadro().correnteIB();
        circuito.getQuadro().correnteCorr();
        circuito.getQuadro().faseCondutor();
        circuito.getQuadro().neutroCondutor();
        circuito.getQuadro().terraCondutor();
        circuito.getQuadro().bitolaCondutor();

        CircuitoService.salva(circuito);
        QuadroService.salva(circuito.getQuadro());
    }

    public static void quadro(Quadro quadro) {

        quadro.tipoCondutor();
        quadro.correnteIB();
        quadro.correnteCorr();
        quadro.faseCondutor();
        quadro.neutroCondutor();
        quadro.terraCondutor();
        quadro.bitolaCondutor();
        
        QuadroService.salva(quadro);
    }
}
