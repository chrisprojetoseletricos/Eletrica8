package calculos;

import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.servico.ProjetoService;
import chc.eletrica8.uteis.DataUtil;
import java.util.List;



public class testeJPA {

	public static void main(String[] args) {
		Projeto projeto = new Projeto();

		projeto.setNome("CHRIS");
		projeto.setAutor("projeto");
		projeto.setDescricao("teste");
		//projeto.setData(DataUtil.Atual());


		ProjetoService.salva(projeto);

		List<Projeto> lista = ProjetoService.getByExpres2("select nome from Projeto",new String[] {});
		System.out.println("lista: " + lista.get(0).getNome().toString());

	}

}
