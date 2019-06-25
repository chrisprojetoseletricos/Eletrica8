package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.Quadro;
import java.util.List;



public class QuadroService {

    private final static JpaDAO<Quadro> DAO_QUADRO = new JpaDAO<Quadro>(Quadro.class);

    public static List<Quadro> getAll() {
        List<Quadro> lista = DAO_QUADRO.getAll();
        return lista;
    }

    public static List<Quadro> getByExpres(String expres, Object[] parameter) {

        return DAO_QUADRO.getByExpres(expres, parameter);
    }

    public static Quadro getById(Integer id) {

        return DAO_QUADRO.getById(id);
    }

    public static void remove(Quadro quadro) {

        DAO_QUADRO.remove(quadro);
    }

    public static void removeById(Integer id) {

        DAO_QUADRO.removeById(id);
    }

    public static void salva(Quadro quadro) {

        DAO_QUADRO.salva(quadro);
    }
}
