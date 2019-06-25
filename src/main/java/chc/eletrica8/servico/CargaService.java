package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.Carga;
import java.util.List;

public class CargaService {

    private final static JpaDAO<Carga> DAO_CARGA = new JpaDAO<Carga>(Carga.class);

    public static List<Carga> getAll() {
        List<Carga> lista = DAO_CARGA.getAll();
        return lista;
    }

    public static List<Carga> getByExpres(String expres, Object[] parameter) {

        return DAO_CARGA.getByExpres(expres, parameter);
    }

    public static Carga getById(Integer id) {

        return DAO_CARGA.getById(id);
    }

    public static void remove(Carga carga) {

        DAO_CARGA.remove(carga);
    }

    public static void removeById(Integer id) {

        DAO_CARGA.removeById(id);
    }

    public static void salva(Carga carga) {

        DAO_CARGA.salva(carga);
    }

}
