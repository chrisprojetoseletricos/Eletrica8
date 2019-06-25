package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.QuadroGeral;
import java.util.List;



public class QuadroGeralService {

    private final static JpaDAO<QuadroGeral> DAO_QUADRO = new JpaDAO<QuadroGeral>(QuadroGeral.class);

    public static List<QuadroGeral> getAll() {
        List<QuadroGeral> lista = DAO_QUADRO.getAll();
        return lista;
    }

    public static List<QuadroGeral> getByExpres(String expres, Object[] parameter) {

        return DAO_QUADRO.getByExpres(expres, parameter);
    }

    public static QuadroGeral getById(Integer id) {

        return DAO_QUADRO.getById(id);
    }

    public static void remove(QuadroGeral quadro) {

        DAO_QUADRO.remove(quadro);
    }

    public static void removeById(Integer id) {

        DAO_QUADRO.removeById(id);
    }

    public static void salva(QuadroGeral quadro) {

        DAO_QUADRO.salva(quadro);
    }
}
