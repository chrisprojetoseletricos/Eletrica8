/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.servico.CargaService;
import chc.eletrica8.servico.CircuitoService;
import chc.eletrica8.servico.FonteService;
import chc.eletrica8.servico.QuadroService;

/**
 *
 * @author chris
 */
public class AtualizaDados {

    public static void carga(Carga carga) {
        carga.tensao();
        carga.correnteAtiva();
        carga.correnteAtivaDem();
        carga.potAtivaKW();
        carga.potAtivaDemKW();
        carga.potReativaKVAr();
        carga.potReativaDemKVAr();
        carga.potAparenteKVA();
        carga.potAparenteDemKVA();

        carga.getCircuito().ordenaDecrListaCarga();
        carga.getCircuito().ligacaoCondutor();
        carga.getCircuito().defineComprimento();
        carga.getCircuito().fatorPotenciaMed();
        carga.getCircuito().correnteAtiva();
        carga.getCircuito().correnteCorr();
        carga.getCircuito().correnteAtivaDem();
        carga.getCircuito().faseCondutor();
        carga.getCircuito().neutroCondutor();
        carga.getCircuito().terraCondutor();
        carga.getCircuito().bitolaCondutor();
        carga.getCircuito().tensao();
        carga.getCircuito().potAtivaKW();
        carga.getCircuito().potAtivaDemKW();
        carga.getCircuito().potReativaKVAr();
        carga.getCircuito().potReativaDemKVAr();
        carga.getCircuito().potAparenteKVA();
        carga.getCircuito().potAparenteDemKVA();

        carga.getCircuito().getQuadro().ordenaDecrListaCircuitos();
        carga.getCircuito().getQuadro().fatorPotenciaMed();
        carga.getCircuito().getQuadro().ligacaoCondutor();
        carga.getCircuito().getQuadro().tensao();
        carga.getCircuito().getQuadro().potAtivaKW();
        carga.getCircuito().getQuadro().potAtivaDemKW();
        carga.getCircuito().getQuadro().potReativaKVAr();
        carga.getCircuito().getQuadro().potReativaDemKVAr();
        carga.getCircuito().getQuadro().potAparenteKVA();
        carga.getCircuito().getQuadro().potAparenteDemKVA();
        carga.getCircuito().getQuadro().correnteAtiva();
        carga.getCircuito().getQuadro().correnteAtivaDem();
        carga.getCircuito().getQuadro().correnteCorr();
        carga.getCircuito().getQuadro().faseCondutor();
        carga.getCircuito().getQuadro().neutroCondutor();
        carga.getCircuito().getQuadro().terraCondutor();
        carga.getCircuito().getQuadro().bitolaCondutor();

        carga.getCircuito().getQuadro().getFonte().fatorPotenciaMed();
        carga.getCircuito().getQuadro().getFonte().defineTipo();
        carga.getCircuito().getQuadro().getFonte().ligacaoCondutor();
        carga.getCircuito().getQuadro().getFonte().correnteAtiva();
        carga.getCircuito().getQuadro().getFonte().correnteReativa();
        carga.getCircuito().getQuadro().getFonte().correnteAparente();
        carga.getCircuito().getQuadro().getFonte().correnteAtivaDem();
        carga.getCircuito().getQuadro().getFonte().correnteReativaDem();
        carga.getCircuito().getQuadro().getFonte().correnteAparenteDem();
        carga.getCircuito().getQuadro().getFonte().tensao();
        carga.getCircuito().getQuadro().getFonte().potAparenteKVA();
        carga.getCircuito().getQuadro().getFonte().potAparenteDemKVA();

        CargaService.salva(carga);
        CircuitoService.salva(carga.getCircuito());
        QuadroService.salva(carga.getCircuito().getQuadro());
        FonteService.salva(carga.getCircuito().getQuadro().getFonte());
    }

    public static void circuito(Circuito circuito) {
        circuito.ordenaDecrListaCarga();
        circuito.ligacaoCondutor();
        circuito.defineComprimento();
        circuito.fatorPotenciaMed();
        circuito.correnteAtiva();
        circuito.correnteCorr();
        circuito.correnteAtivaDem();
        circuito.faseCondutor();
        circuito.neutroCondutor();
        circuito.terraCondutor();
        circuito.bitolaCondutor();
        circuito.tensao();
        circuito.potAtivaKW();
        circuito.potAtivaDemKW();
        circuito.potReativaKVAr();
        circuito.potReativaDemKVAr();
        circuito.potAparenteKVA();
        circuito.potAparenteDemKVA();

        circuito.getQuadro().ordenaDecrListaCircuitos();
        circuito.getQuadro().fatorPotenciaMed();
        circuito.getQuadro().ligacaoCondutor();
        circuito.getQuadro().tensao();
        circuito.getQuadro().potAtivaKW();
        circuito.getQuadro().potAtivaDemKW();
        circuito.getQuadro().potReativaKVAr();
        circuito.getQuadro().potReativaDemKVAr();
        circuito.getQuadro().potAparenteKVA();
        circuito.getQuadro().potAparenteDemKVA();
        circuito.getQuadro().correnteAtiva();
        circuito.getQuadro().correnteAtivaDem();
        circuito.getQuadro().correnteCorr();
        circuito.getQuadro().faseCondutor();
        circuito.getQuadro().neutroCondutor();
        circuito.getQuadro().terraCondutor();
        circuito.getQuadro().bitolaCondutor();

        circuito.getQuadro().getFonte().fatorPotenciaMed();
        circuito.getQuadro().getFonte().defineTipo();
        circuito.getQuadro().getFonte().ligacaoCondutor();
        circuito.getQuadro().getFonte().correnteAtiva();
        circuito.getQuadro().getFonte().correnteReativa();
        circuito.getQuadro().getFonte().correnteAparente();
        circuito.getQuadro().getFonte().correnteAtivaDem();
        circuito.getQuadro().getFonte().correnteReativaDem();
        circuito.getQuadro().getFonte().correnteAparenteDem();
        circuito.getQuadro().getFonte().tensao();
        circuito.getQuadro().getFonte().potAparenteKVA();
        circuito.getQuadro().getFonte().potAparenteDemKVA();

        CircuitoService.salva(circuito);
        QuadroService.salva(circuito.getQuadro());
        FonteService.salva(circuito.getQuadro().getFonte());
    }

    public static void quadro(Quadro quadro) {
        quadro.ordenaDecrListaCircuitos();
        quadro.fatorPotenciaMed();
        quadro.ligacaoCondutor();
        quadro.tensao();
        quadro.potAtivaKW();
        quadro.potAtivaDemKW();
        quadro.potReativaKVAr();
        quadro.potReativaDemKVAr();
        quadro.potAparenteKVA();
        quadro.potAparenteDemKVA();
        quadro.correnteAtiva();
        quadro.correnteAtivaDem();
        quadro.correnteCorr();
        quadro.faseCondutor();
        quadro.neutroCondutor();
        quadro.terraCondutor();
        quadro.bitolaCondutor();

        quadro.getFonte().fatorPotenciaMed();
        quadro.getFonte().defineTipo();
        quadro.getFonte().ligacaoCondutor();
        quadro.getFonte().correnteAtiva();
        quadro.getFonte().correnteReativa();
        quadro.getFonte().correnteAparente();
        quadro.getFonte().correnteAtivaDem();
        quadro.getFonte().correnteReativaDem();
        quadro.getFonte().correnteAparenteDem();
        quadro.getFonte().tensao();
        quadro.getFonte().potAparenteKVA();
        quadro.getFonte().potAparenteDemKVA();

        QuadroService.salva(quadro);
        FonteService.salva(quadro.getFonte());
    }

    public static void fonte(Fonte fonte) {
        fonte.fatorPotenciaMed();
        fonte.defineTipo();
        fonte.ligacaoCondutor();
        fonte.correnteAtiva();
        fonte.correnteReativa();
        fonte.correnteAparente();
        fonte.correnteAtivaDem();
        fonte.correnteReativaDem();
        fonte.correnteAparenteDem();
        fonte.tensao();
        fonte.potAparenteKVA();
        fonte.potAparenteDemKVA();

        FonteService.salva(fonte);
    }
}
