package chc.eletrica8.servico;

import chc.eletrica8.dao.JpaDAO;
import chc.eletrica8.entidades.RamalLigacao;
import java.util.List;


public class RamalLigacaoService {

    private final static JpaDAO<RamalLigacao> DAO_RAMAL = new JpaDAO<RamalLigacao>(RamalLigacao.class);

    public static List<RamalLigacao> getAll() {
        List<RamalLigacao> lista = DAO_RAMAL.getAll();
        return lista;
    }

    public static List<RamalLigacao> getByExpres(String expres, Object[] parameter) {

        return DAO_RAMAL.getByExpres(expres, parameter);
    }

    public static RamalLigacao getById(Integer id) {

        return DAO_RAMAL.getById(id);
    }

    public static void remove(RamalLigacao ramal) {

        DAO_RAMAL.remove(ramal);
    }

    public static void removeById(Integer id) {

        DAO_RAMAL.removeById(id);
    }

    public static void salva(RamalLigacao ramal) {

        DAO_RAMAL.salva(ramal);
    }
}
