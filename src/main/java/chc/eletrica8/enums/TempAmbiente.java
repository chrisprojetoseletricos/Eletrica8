package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum TempAmbiente {
    T20("20"),
    T25("25"),
    T30("30"),
    T35("35"),
    T40("40"),
    T45("45");
    
    private final String numero;

    private TempAmbiente(String numero){
        this.numero = numero;
    }
    
    public int getNumero(){
        return Integer.parseInt(numero);
    }
    
    public static List<TempAmbiente> getLista() {
        List<TempAmbiente> lista = new ArrayList<>();
        lista.add(TempAmbiente.T20);
        lista.add(TempAmbiente.T25);
        lista.add(TempAmbiente.T30);
        lista.add(TempAmbiente.T35);
        lista.add(TempAmbiente.T40);
        lista.add(TempAmbiente.T45);
        return lista;
    }
}
