/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculos;

import chc.eletrica8.calculos.Bitola;
import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.entidades.Curto;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.BitolasMili;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.TiposFornecimento;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.uteis.LerCSV;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author chris
 */
public class Ex3_8 {

    public static void main(String[] args) {
        
        Projeto pro = new Projeto();
        pro.setAutor("Teste");
        pro.setNome("Projeto");
        pro.setDataProjeto(Date.from(Instant.now()));

        Fonte fonte = new Fonte();
        fonte.setNome("Fonte");
        fonte.setTensaoFN(220);
        fonte.setProjeto(pro);
        
        Condutor condutor = new Condutor();
        condutor.setComprimento(1);
        Curto curto = new Curto();
        curto.setCorrenteCurto(100);
        
        Quadro QGF = new Quadro();
        QGF.setNome("QGF");
        QGF.setFonte(fonte);
        QGF.setCondutor(condutor);
        QGF.setCurto(curto);
        
        Circuito CIR_1 = new Circuito();
        CIR_1.setNome("CIR_1");
        CIR_1.setCondutor(condutor);
        CIR_1.setCurto(curto);
        CIR_1.setQuadro(QGF);
       

        Carga motor1 = new Carga();
        motor1.setNome("Motor1");
        motor1.setPotencia(5);
        motor1.setUnidade(UnidadePotencia.CV);
        motor1.setFp(0.83);
        motor1.setLigacao(Ligacao.FFF);
        motor1.setCircuito(CIR_1);
        motor1.setTensao();
        motor1.setComprimentoInstal(8);
        motor1.setRendimento(0.83);
        
        Carga motor2 = new Carga();
        motor2.setNome("Motor2");
        motor2.setPotencia(15);
        motor2.setUnidade(UnidadePotencia.CV);
        motor2.setFp(0.75);
        motor2.setLigacao(Ligacao.FFF);
        motor2.setCircuito(CIR_1);
        motor2.setTensao();
        motor2.setComprimentoInstal(18);
        motor2.setRendimento(0.86);
        
        Carga motor3 = new Carga();
        motor3.setNome("Motor3");
        motor3.setPotencia(20);
        motor3.setUnidade(UnidadePotencia.CV);
        motor3.setFp(0.86);
        motor3.setLigacao(Ligacao.FFF);
        motor3.setCircuito(CIR_1);
        motor3.setTensao();
        motor3.setComprimentoInstal(24);
        motor3.setRendimento(0.88);
        
        Carga motor4 = new Carga();
        motor4.setNome("Motor4");
        motor4.setPotencia(7.5);
        motor4.setUnidade(UnidadePotencia.CV);
        motor4.setFp(0.81);
        motor4.setLigacao(Ligacao.FFF);
        motor4.setCircuito(CIR_1);
        motor4.setTensao();
        motor4.setComprimentoInstal(38);
        motor4.setRendimento(0.84);
        
        Carga motor5 = new Carga();
        motor5.setNome("Motor5");
        motor5.setPotencia(20);
        motor5.setUnidade(UnidadePotencia.CV);
        motor5.setFp(0.83);
        motor5.setLigacao(Ligacao.FFF);
        motor5.setCircuito(CIR_1);
        motor5.setTensao();
        motor5.setComprimentoInstal(49);
        motor5.setRendimento(0.88);
        
        CIR_1.getListaCarga().add(motor1);
        CIR_1.getListaCarga().add(motor2);
        CIR_1.getListaCarga().add(motor3);
        CIR_1.getListaCarga().add(motor4);
        CIR_1.getListaCarga().add(motor5);
        
        CIR_1.tipoCircuito();
        
        QGF.getCircuitos().add(CIR_1);

        fonte.getQuadros().add(QGF);

        pro.getFontes().add(fonte);

        String valor = new Bitola()//
                .withEnterrado("Não")//
                .withInstalacao(Instalacao.B1)//
                .withMultipolar("Não")//
                .withQuedaTensao(4)//
                .withTabelaCapacidadeCorrente(new LerCSV("CCBaixaCobre.csv").toMatriz())//
                .withMaterial("Cobre")//
                .withIsolacao("XLPE")//
                .withCondutoresCarregados(3)//
                .withParametroEspecial("")//H de horizontal ou V de vestical. ex: PEH3
                .withCircuito(CIR_1)//
                .withFornecimento(TiposFornecimento.TRIFASICO)//
                .valor();
        
        System.out.println("valor: "+ valor);

    }

}
