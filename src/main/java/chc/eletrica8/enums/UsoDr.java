package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum UsoDr {
    DR_PRINCIPAL,
    DR_PRINCIPAL_COM_MAGNETICO;

    public static List<UsoDr> getLista() {
        List<UsoDr> lista = new ArrayList<>();
        lista.add(UsoDr.DR_PRINCIPAL);
        lista.add(UsoDr.DR_PRINCIPAL_COM_MAGNETICO);
        return lista;
    }
}
