/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

/**
 *
 * @author chris
 */
public class CurtoCircuito {

    private String isolacao;
    private double Te;
    private double Ics;
    private double Tf;
    private double Ti;
    private double fase;

    public double bitola() {
        double valor = 0;
        if (isolacao.equals("PVC")) {
            Tf = 160;
            Ti = 70;
        } else {
            Tf = 250;
            Ti = 90;
        }
        double parte1 = Math.sqrt(Te) * Ics;
        double parte4 = (234 + Tf) / (234 + Ti);
        double parte2 = Math.log10(parte4);
        double parte3 = 0.34 * Math.sqrt(parte2);
        
        valor = parte1 / parte3;

        return valor;
    }
    
    public double tempo(){
        double valor = 0;
        if (isolacao.equals("PVC")) {
            Tf = 160;
            Ti = 70;
        } else {
            Tf = 250;
            Ti = 90;
        }
        
        double parte4 = (234 + Tf) / (234 + Ti);
        double parte2 = Math.log10(parte4);
        double parte3 = 0.34 * fase * Math.sqrt(parte2);
        double parte1 = Math.pow(parte3/Ics,2);
        
        valor = parte1;
       
        return valor;
    }

    public CurtoCircuito withIsolacao(String isolacao) {
        this.isolacao = isolacao;
        return this;
    }

    public CurtoCircuito withTe(double Te) {
        this.Te = Te;
        return this;
    }

    public CurtoCircuito withIcs(double Ics) {
        this.Ics = Ics;
        return this;
    }
    
        public CurtoCircuito withFase(double fase) {
        this.fase = fase;
        return this;
    }
}
