package calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.entidades.Curto;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.UnidadePotencia;
import java.time.Instant;
import java.util.Date;

public class ex3_3 {

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
        condutor.setComprimento(100);
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
        motor1.setNome("Motor3000");
        motor1.setPotencia(3000);
        motor1.setUnidade(UnidadePotencia.W);
        motor1.setFp(0.90);
        motor1.setLigacao(Ligacao.FN);
        motor1.setCircuito(CIR_1);
        motor1.setTensao();

        Carga motor2 = new Carga();
        motor2.setNome("Motor800");
        motor2.setPotencia(800);
        motor2.setUnidade(UnidadePotencia.W);
        motor2.setFp(0.70);
        motor2.setLigacao(Ligacao.FN);
        motor2.setCircuito(CIR_1);
        motor2.setTensao();

        Carga motor3 = new Carga();
        motor3.setNome("Motor600");
        motor3.setPotencia(600);
        motor3.setUnidade(UnidadePotencia.W);
        motor3.setFp(0.60);
        motor3.setLigacao(Ligacao.FN);
        motor3.setCircuito(CIR_1);
        motor3.setTensao();

        Carga motor4 = new Carga();
        motor4.setNome("Motor2500");
        motor4.setPotencia(2500);
        motor4.setUnidade(UnidadePotencia.W);
        motor4.setFp(0.80);
        motor4.setLigacao(Ligacao.FF);
        motor4.setCircuito(CIR_1);
        motor4.setTensao();

        CIR_1.getListaCarga().add(motor1);
        CIR_1.getListaCarga().add(motor2);
        CIR_1.getListaCarga().add(motor3);
        CIR_1.getListaCarga().add(motor4);
        
        QGF.getCircuitos().add(CIR_1);

        fonte.getQuadros().add(QGF);

        pro.getFontes().add(fonte);
        
        double correnteIB = CIR_1.getCorrenteIB();

       // ProjetoService.salva(pro);

        

        System.out.println("CorrenteIB: " + correnteIB);

        for (Carga eq : CIR_1.getListaCarga()) {
            System.out.println("Carga: " + eq.getNome() + ". Ligação: " + eq.getLigacaoReal() + ". Tensão: "+ eq.getTensao());
        }

        // CorrenteIB: 23.375199362041464
        // Carga: Motor3000. Ligacao: AN
        // Carga: Motor800. Ligacao: BN
        // Carga: Motor600. Ligacao: BN
        // Carga: Motor2500. Ligacao: AB
    }

}
