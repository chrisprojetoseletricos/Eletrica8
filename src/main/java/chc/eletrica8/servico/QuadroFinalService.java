package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.QuadroFinal;
import java.util.List;



public class QuadroFinalService {

    private final static JpaDAO<QuadroFinal> DAO_QUADRO = new JpaDAO<QuadroFinal>(QuadroFinal.class);

    public static List<QuadroFinal> getAll() {
        List<QuadroFinal> lista = DAO_QUADRO.getAll();
        return lista;
    }

    public static List<QuadroFinal> getByExpres(String expres, Object[] parameter) {

        return DAO_QUADRO.getByExpres(expres, parameter);
    }

    public static QuadroFinal getById(Integer id) {

        return DAO_QUADRO.getById(id);
    }

    public static void remove(QuadroFinal quadro) {

        DAO_QUADRO.remove(quadro);
    }

    public static void removeById(Integer id) {

        DAO_QUADRO.removeById(id);
    }

    public static void salva(QuadroFinal quadro) {

        DAO_QUADRO.salva(quadro);
    }
}
