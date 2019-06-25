package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.Projeto;
import java.util.List;



public class ProjetoService {

    private final static JpaDAO<Projeto> DAO_PROJETO = new JpaDAO<Projeto>(Projeto.class);

    public static List<Projeto> getAll() {
        List<Projeto> lista = DAO_PROJETO.getAll();
        return lista;
    }

    public static List<Projeto> getByExpres2(String expres, Object[] parameter) {

        return DAO_PROJETO.getByExpres2(expres, parameter);
    }

    public static Projeto getById(Integer id) {

        return DAO_PROJETO.getById(id);
    }

    public static void remove(Projeto projeto) {

        DAO_PROJETO.remove(projeto);
    }

    public static void removeById(Integer id) {

        DAO_PROJETO.removeById(id);
    }

    public static void salva(Projeto projeto) {

        DAO_PROJETO.salva(projeto);
    }

}
