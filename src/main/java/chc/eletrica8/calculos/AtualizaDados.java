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
        carga.correnteReativa();
        carga.correnteAparente();
        carga.correnteAtivaDem();
        carga.correnteReativaDem();
        carga.correnteAparenteDem();
        carga.potAtivaKVA();
        carga.potAtivaDemKVA();
        carga.getCircuito().ordenaDecrListaCarga();
        carga.getCircuito().ligacaoCondutor();
        carga.getCircuito().defineComprimento();
        carga.getCircuito().fatorPotenciaMed();
        carga.getCircuito().correnteAtiva();
        carga.getCircuito().correnteCorr();
        carga.getCircuito().correnteReativa();
        carga.getCircuito().correnteAparente();
        carga.getCircuito().correnteAtivaDem();
        carga.getCircuito().correnteReativaDem();
        carga.getCircuito().correnteAparenteDem();
        carga.getCircuito().faseCondutor();
        carga.getCircuito().neutroCondutor();
        carga.getCircuito().terraCondutor();
        carga.getCircuito().bitolaCondutor();
        carga.getCircuito().tensao();
        carga.getCircuito().potAtivaKVA();
        carga.getCircuito().potAtivaDemKVA();
        carga.getCircuito().getQuadro().ordenaDecrListaCircuitos();
        carga.getCircuito().getQuadro().fatorPotenciaMed();
        carga.getCircuito().getQuadro().ligacaoCondutor();
        carga.getCircuito().getQuadro().correnteAtiva();
        carga.getCircuito().getQuadro().correnteCorr();
        carga.getCircuito().getQuadro().correnteAtiva();
        carga.getCircuito().getQuadro().correnteReativa();
        carga.getCircuito().getQuadro().correnteAparente();
        carga.getCircuito().getQuadro().correnteAtivaDem();
        carga.getCircuito().getQuadro().correnteReativaDem();
        carga.getCircuito().getQuadro().correnteAparenteDem();
        carga.getCircuito().getQuadro().faseCondutor();
        carga.getCircuito().getQuadro().neutroCondutor();
        carga.getCircuito().getQuadro().terraCondutor();
        carga.getCircuito().getQuadro().bitolaCondutor();
        carga.getCircuito().getQuadro().tensao();
        carga.getCircuito().getQuadro().potAtivaKVA();
        carga.getCircuito().getQuadro().potAtivaDemKVA();
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
        carga.getCircuito().getQuadro().getFonte().potAtivaKVA();
        carga.getCircuito().getQuadro().getFonte().potAtivaDemKVA();

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
        circuito.correnteReativa();
        circuito.correnteAparente();
        circuito.correnteAtivaDem();
        circuito.correnteReativaDem();
        circuito.correnteAparenteDem();
        circuito.faseCondutor();
        circuito.neutroCondutor();
        circuito.terraCondutor();
        circuito.bitolaCondutor();
        circuito.tensao();
        circuito.potAtivaKVA();
        circuito.potAtivaDemKVA();
        circuito.getQuadro().ordenaDecrListaCircuitos();
        circuito.getQuadro().fatorPotenciaMed();
        circuito.getQuadro().ligacaoCondutor();
        circuito.getQuadro().correnteAtiva();
        circuito.getQuadro().correnteCorr();
        circuito.getQuadro().correnteAtiva();
        circuito.getQuadro().correnteReativa();
        circuito.getQuadro().correnteAparente();
        circuito.getQuadro().correnteAtivaDem();
        circuito.getQuadro().correnteReativaDem();
        circuito.getQuadro().correnteAparenteDem();
        circuito.getQuadro().faseCondutor();
        circuito.getQuadro().neutroCondutor();
        circuito.getQuadro().terraCondutor();
        circuito.getQuadro().bitolaCondutor();
        circuito.getQuadro().tensao();
        circuito.getQuadro().potAtivaKVA();
        circuito.getQuadro().potAtivaDemKVA();
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
        circuito.getQuadro().getFonte().potAtivaKVA();
        circuito.getQuadro().getFonte().potAtivaDemKVA();

        CircuitoService.salva(circuito);
        QuadroService.salva(circuito.getQuadro());
        FonteService.salva(circuito.getQuadro().getFonte());
    }

    public static void quadro(Quadro quadro) {
        quadro.ordenaDecrListaCircuitos();
        quadro.fatorPotenciaMed();
        quadro.ligacaoCondutor();
        quadro.correnteAtiva();
        quadro.correnteCorr();
        quadro.correnteAtiva();
        quadro.correnteReativa();
        quadro.correnteAparente();
        quadro.correnteAtivaDem();
        quadro.correnteReativaDem();
        quadro.correnteAparenteDem();
        quadro.faseCondutor();
        quadro.neutroCondutor();
        quadro.terraCondutor();
        quadro.bitolaCondutor();
        quadro.tensao();
        quadro.potAtivaKVA();
        quadro.potAtivaDemKVA();
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
        quadro.getFonte().potAtivaKVA();
        quadro.getFonte().potAtivaDemKVA();

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
        fonte.potAtivaKVA();
        fonte.potAtivaDemKVA();

        FonteService.salva(fonte);
    }
}
