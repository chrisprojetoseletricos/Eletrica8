package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.Curto;
import java.util.List;

public class CurtoService {

    private final static JpaDAO<Curto> DAO_CURTO = new JpaDAO<Curto>(Curto.class);

    public static List<Curto> getAll() {
        List<Curto> lista = DAO_CURTO.getAll();
        return lista;
    }

    public static List<Curto> getByExpres(String expres, Object[] parameter) {

        return DAO_CURTO.getByExpres(expres, parameter);
    }

    public static Curto getById(Integer id) {

        return DAO_CURTO.getById(id);
    }

    public static void remove(Curto Curto) {

        DAO_CURTO.remove(Curto);
    }

    public static void removeById(Integer id) {

        DAO_CURTO.removeById(id);
    }

    public static void salva(Curto Curto) {

        DAO_CURTO.salva(Curto);
    }
}
