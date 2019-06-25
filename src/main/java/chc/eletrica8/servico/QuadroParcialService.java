package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.QuadroParcial;
import java.util.List;



public class QuadroParcialService {

    private final static JpaDAO<QuadroParcial> DAO_QUADRO = new JpaDAO<QuadroParcial>(QuadroParcial.class);

    public static List<QuadroParcial> getAll() {
        List<QuadroParcial> lista = DAO_QUADRO.getAll();
        return lista;
    }

    public static List<QuadroParcial> getByExpres(String expres, Object[] parameter) {

        return DAO_QUADRO.getByExpres(expres, parameter);
    }

    public static QuadroParcial getById(Integer id) {

        return DAO_QUADRO.getById(id);
    }

    public static void remove(QuadroParcial quadro) {

        DAO_QUADRO.remove(quadro);
    }

    public static void removeById(Integer id) {

        DAO_QUADRO.removeById(id);
    }

    public static void salva(QuadroParcial quadro) {

        DAO_QUADRO.salva(quadro);
    }
}
