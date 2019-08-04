package chc.eletrica8.enums;

import java.util.ArrayList;
import java.util.List;

public enum TempAmbiente {
    T10("10"),
    T15("15"),
    T25("25"),
    T30("30"),
    T35("35"),
    T40("40"),
    T45("45"),
    T50("50"),
    T55("55"),
    T60("60"),
    T65("65"),
    T70("70"),
    T75("75"),
    T80("80");
    
    private final String numero;

    private TempAmbiente(String numero){
        this.numero = numero;
    }
    
    public int getNumero(){
        return Integer.parseInt(numero);
    }
    
    public static List<TempAmbiente> getLista() {
        List<TempAmbiente> lista = new ArrayList<>();
        lista.add(TempAmbiente.T10);
        lista.add(TempAmbiente.T15);
        lista.add(TempAmbiente.T25);
        lista.add(TempAmbiente.T30);
        lista.add(TempAmbiente.T35);
        lista.add(TempAmbiente.T40);
        lista.add(TempAmbiente.T45);
        lista.add(TempAmbiente.T50);
        lista.add(TempAmbiente.T55);
        lista.add(TempAmbiente.T60);
        lista.add(TempAmbiente.T65);
        lista.add(TempAmbiente.T70);
        lista.add(TempAmbiente.T75);
        lista.add(TempAmbiente.T80);
        return lista;
    }
}
