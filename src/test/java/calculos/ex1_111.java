package calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.entidades.Quadro;

import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.servico.ProjetoService;

public class ex1_111 {

    public static void main(String[] args) {

        Projeto pro = new Projeto();
        pro.setAutor("Teste");
        pro.setNome("Projeto");

        Fonte fonte = new Fonte();
        fonte.setNome("Fonte");
        fonte.setTensaoFN(220);
        fonte.setProjeto(pro);

        Quadro QGF = new Quadro();
        QGF.setNome("QGF");
        QGF.setFonte(fonte);
        
        Quadro CCM3 = new Quadro();
        CCM3.setNome("CCM3");
        CCM3.setQuadroGeral(QGF);

        Quadro CCM1 = new Quadro();
        CCM1.setNome("CCM1");
        CCM1.setQuadroGeral(CCM3);

        Quadro CCM2 = new Quadro();
        CCM2.setNome("CCM2");
        CCM2.setQuadroGeral(QGF);

        Quadro QDL = new Quadro();
        QDL.setNome("QDL");
        QDL.setQuadroGeral(QGF);

        Circuito CIR_1 = new Circuito();
        CIR_1.setNome("CIR_1");
        CIR_1.setQuadro(CCM1);
        Circuito CIR_2 = new Circuito();
        CIR_2.setNome("CIR_2");
        CIR_2.setQuadro(CCM2);
        Circuito CIR_3 = new Circuito();
        CIR_3.setNome("CIR_3");
        CIR_3.setQuadro(CCM2);
        Circuito CIR_FL = new Circuito();
        CIR_FL.setNome("CIR_FL");
        CIR_FL.setQuadro(QDL);
        Circuito CIR_IN = new Circuito();
        CIR_IN.setNome("CIR_IN");
        CIR_IN.setQuadro(QDL);

        Carga motor1 = new Carga();
        motor1.setNome("Motor1");
        motor1.setPotencia(75);
        motor1.setUnidade(UnidadePotencia.CV);
        motor1.setQuantidade(10);
        motor1.setFu(0.87);
        motor1.setRendimento(0.92);
        motor1.setFp(0.86);
        motor1.setfSimu(0.65);
        motor1.setUsabilidade(Usabilidade.MOTOR);
        motor1.setCircuito(CIR_1);

        Carga motor2 = new Carga();
        motor2.setNome("Motor2");
        motor2.setPotencia(30);
        motor2.setUnidade(UnidadePotencia.CV);
        motor2.setQuantidade(10);
        motor2.setFu(0.85);
        motor2.setRendimento(0.9);
        motor2.setFp(0.83);
        motor2.setfSimu(0.65);
        motor2.setUsabilidade(Usabilidade.MOTOR);
        motor2.setCircuito(CIR_2);

        Carga motor3 = new Carga();
        motor3.setNome("Motor3");
        motor3.setPotencia(50);
        motor3.setUnidade(UnidadePotencia.CV);
        motor3.setQuantidade(5);
        motor3.setFu(0.87);
        motor3.setRendimento(0.92);
        motor3.setFp(0.86);
        motor3.setfSimu(0.70);
        motor3.setUsabilidade(Usabilidade.MOTOR);
        motor3.setCircuito(CIR_3);

        Carga lampFlu = new Carga();
        lampFlu.setNome("LampFlu");
        lampFlu.setQuantidade(150);
        lampFlu.setPotencia(40);
        lampFlu.setUnidade(UnidadePotencia.W);
        lampFlu.setPerdasReator(15.3);
        lampFlu.setFp(0.4);
        lampFlu.setUsabilidade(Usabilidade.ILUMINACAO_FLUORESCENTE_PERDAS);
        lampFlu.setCircuito(CIR_FL);

        Carga lampInc = new Carga();
        lampInc.setNome("LampInc");
        lampInc.setQuantidade(52);
        lampInc.setPotencia(100);
        lampInc.setUnidade(UnidadePotencia.W);
        lampInc.setUsabilidade(Usabilidade.ILUMINACAO_INCADESCENTE);
        lampInc.setCircuito(CIR_IN);

        CIR_1.getListaCarga().add(motor1);
        CIR_2.getListaCarga().add(motor2);
        CIR_3.getListaCarga().add(motor3);
        CIR_FL.getListaCarga().add(lampFlu);
        CIR_IN.getListaCarga().add(lampInc);

        CCM1.getCircuitos().add(CIR_1);
        CCM2.getCircuitos().add(CIR_2);
        CCM2.getCircuitos().add(CIR_3);
        QDL.getCircuitos().add(CIR_FL);
        QDL.getCircuitos().add(CIR_IN);

        QGF.getQuadros().add(CCM1);
        QGF.getQuadros().add(CCM2);
        QGF.getQuadros().add(QDL);

        fonte.getQuadros().add(QGF);

        pro.getFontes().add(fonte);

        ProjetoService.salva(pro);

    }
}
