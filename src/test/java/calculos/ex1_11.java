package calculos;

import chc.eletrica8.calculos.CalculoUtils;
import chc.eletrica8.calculos.CalculoUtils.MODELO_INSTALACAO;
import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.entidades.QuadroFinal;
import chc.eletrica8.entidades.QuadroGeral;
import chc.eletrica8.enums.UnidadePotencia;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.servico.ProjetoService;
import chc.eletrica8.servico.QuadroFinalService;

public class ex1_11 {

    public static void main(String[] args) {

        Projeto pro = new Projeto();
        pro.setAutor("Teste");
        pro.setNome("Projeto");

        Fonte fonte = new Fonte();
        fonte.setNome("Fonte");
        fonte.setTensaoFN(220);
        fonte.setProjeto(pro);

        QuadroGeral QGF = new QuadroGeral();
        QGF.setNome("QGF");
        QGF.setFonte(fonte);

        QuadroFinal CCM1 = new QuadroFinal();
        CCM1.setNome("CCM1");
        CCM1.setQuadroGeral(QGF);
        
        QuadroFinalService.salva(CCM1);

    }
}
