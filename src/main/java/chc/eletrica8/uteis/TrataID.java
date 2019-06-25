package chc.eletrica8.uteis;

public class TrataID {

    public static Integer StringToInteger(String id) {
        Integer valor;
        if (id.equals("") || id.equals("0")) {
            valor = null;
        } else {
            valor = Integer.valueOf(id);
        }
        return valor;
    }

    public static Integer IntegerToInteger(Integer id) {
        Integer valor;
        if (id == 0) {
            valor = null;
        } else {
            valor = id;
        }
        return valor;
    }

}
