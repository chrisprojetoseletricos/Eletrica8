package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.Fonte;
import java.util.List;



public class FonteService {

    private final static JpaDAO<Fonte> DAO_FONTE = new JpaDAO<Fonte>(Fonte.class);

    public static List<Fonte> getAll() {
        List<Fonte> lista = DAO_FONTE.getAll();
        return lista;
    }

    public static List<Fonte> getByExpres(String expres, Object[] parameter) {

        return DAO_FONTE.getByExpres(expres, parameter);
    }

    public static Fonte getById(Integer id) {

        return DAO_FONTE.getById(id);
    }

    public static void remove(Fonte fonte) {

        DAO_FONTE.remove(fonte);
    }

    public static void removeById(Integer id) {

        DAO_FONTE.removeById(id);
    }

    public static void salva(Fonte fonte) {

        DAO_FONTE.salva(fonte);
    }
}
