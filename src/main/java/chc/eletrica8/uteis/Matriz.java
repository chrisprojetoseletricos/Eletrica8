/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.uteis;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Matriz {

    public Matriz() {

    }
//pega parte da matriz conforme os limites fornecidos através dos numeros de linhas

    public static String[][] divide(String[][] matriz, int nLinhas, int nColunas) {

        String[][] subMatriz = new String[nLinhas][nColunas];
        ArrayList<String> linhas = new ArrayList();
        int lin = 0;
        for (int i = 0; i < subMatriz.length; i++) {
            for (int j = 0; j < subMatriz[i].length; j++) {
                linhas.add(matriz[i][j]);

            }

            for (int d = 0; d < subMatriz[lin].length; d++) {
                subMatriz[lin][d] = linhas.get(d);
            }
            lin = lin + 1;
            linhas.clear();
        }

        return subMatriz;
    }

    public static String[][] divide2(String[][] matriz, int nLinhas, int nColunas, int linhaInicio, int linhaFim, int colunaInicio, int colunaFim) {
        String[][] nova = new String[nLinhas][nColunas];
        int l = 0;
        int c = 0;
        for (int i = linhaInicio; i < linhaFim; i++) {
            for (int j = colunaInicio; j < colunaFim; j++) {
                nova[l][c] = matriz[i][j];
                c = c + 1;
            }
            c = 0;
            l = l + 1;
        }
        return nova;
    }

//vai dividir uma matriz conforme os limites dados e vai adicionar mais uma coluna a ela. Depois vai adicionar
    //os valores da lista dado nessa ultima coluna
    public static String[][] divideEadicColuna(String[][] matriz, int nResultados, ArrayList resultados, int nLinhas, int nColunas) {

        String[][] subMatriz = divide(matriz, nLinhas, nColunas);
        String[][] maisColuna = new String[subMatriz.length][subMatriz[0].length + nResultados];

        ArrayList<String> linhas = new ArrayList();

        for (int i = 0; i < subMatriz.length; i++) {
            for (int j = 0; j < subMatriz[i].length; j++) {
                maisColuna[i][j] = subMatriz[i][j];
            }
        }

        linhas.addAll(resultados);
        int cont = 0;
        for (int c = 0; c < nResultados; c++) {
            for (int d = 0; d < maisColuna.length; d++) {
                maisColuna[d][subMatriz[0].length + c] = linhas.get(cont);
                cont = cont + 1;
            }
        }
        return maisColuna;
    }

    public static String[][] adicionaColuna(String[][] matriz, String Titulo, ArrayList valores) {

        String[][] novaMatriz = new String[matriz.length][matriz[0].length + 1];
        ArrayList<String> valoresComTitulo = new ArrayList();
        valoresComTitulo.add(Titulo);
        valoresComTitulo.addAll(valores);
        //System.out.println(valoresComTitulo.get(5));
        ArrayList<String> linha = new ArrayList();

        int lin = 0;
        int valor = 0;
        for (int r = 0; r < matriz.length; r++) {
            for (int t = 0; t < matriz[r].length; t++) {
                linha.add(matriz[r][t]);

            }

            linha.add(valoresComTitulo.get(valor));
            for (int d = 0; d < novaMatriz[lin].length; d++) {

                novaMatriz[lin][d] = linha.get(d);
            }

            lin = lin + 1;
            valor = valor + 1;
            linha.clear();

        }

        //System.out.println("n linhas:" + novaMatriz.length + " N de colunas:" + novaMatriz[0].length);
        return novaMatriz;
    }

    public static void imprime(String[][] tabela) {
        for (String[] tabela1 : tabela) {
            for (String tabela11 : tabela1) {
                System.out.print(tabela11 + " ");
            }
            System.out.println();
        }
    }

    public static int EncColuna(String[][] matriz, String nome2) {
        int j = 0;
        int i = 0;
        int achou = -1;
        // find the row
        boolean teste = false;
        for (i = 0; i < matriz.length; i++) {
            // matriz[i] = new String[3];
            for (j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].equals(nome2)) {
                    achou = j;
                    teste = true;
                    break;

                }
                if (teste == true) {
                    break;
                }
            }
            if (teste == true) {
                break;
            }
        }
        if (achou == -1) {
            System.out.println("Não encontrou a coluna do valor requerido (M: EncColuna): " + nome2);
        }
        return achou;
    }

    //Vai buscar a linha para o valor especificado na primeira coluna. Nesta coluna não pode ter repetidos. Se tiver, vai retornar a linha da pri linha
    public static String EncLinhaPrimColuna(String[][] matriz, String nome2) {

        int i = 0;
        int achou = -1;

        boolean teste = false;
        for (i = 0; i < matriz.length; i++) {

            if (matriz[i][0].equals(nome2)) {
                achou = i;
                teste = true;
                break;

            }
            if (teste == true) {
                break;
            }
        }
        if (achou == -1) {
            System.out.println("Não encontrou a linha do valor requerido (M: EncLinhaPrimColuna): " + nome2);
        }
        return Integer.toString(achou);
    }

    public static int EncLinhaPrimColuna2(String[][] matriz, String nome2) {

        int i = 0;
        int achou = -1;

        boolean teste = false;
        for (i = 0; i < matriz.length; i++) {

            if (matriz[i][0].equals(nome2)) {
                achou = i;
                teste = true;
                break;

            }
            if (teste == true) {
                break;
            }
        }
        if (achou == -1) {
            System.out.println("Não encontrou a linha do valor requerido (M: EncLinhaPrimColuna): " + nome2);
        }
        return (achou);
    }

//encontra a linha do primeiro valor encontrado dentro da matriz
    public static int EncLinhaNoGrupo(String[][] matriz2, String nome1) {
        int j = 0;
        int i = 0;
        int achou = 0;

        // boolean para = false;
        // find the row
        for (i = 0; i < matriz2.length; i++) {
            for (j = 0; j < matriz2[i].length; j++) {
                if (matriz2[i][j].equals(nome1)) {
                    achou = i;
                    break;

                }
            }
        }
        if (achou == 0) {
            System.out.println("Não encontrou a linha do valor requerido (M: EncLinhaNoGrupo): " + nome1);
        }
        return achou;
    }

    //encontrar a linha da matriz procurada na primeira coluna com valor exato. Se não encontrar,
    //envia a linha do valor maior logo após a ele, e a linha do valor menor logo antes dele.
    public static ArrayList EncLinhaDaColuna(String[][] matriz2, String nome1) {

        boolean igual = false;

        ArrayList<Integer> aux = new ArrayList();

        for (int i = 1; i < matriz2.length; i++) {
            if (nome1.equals(matriz2[i][0])) {
                aux.add(i);
                igual = true;
            }
        }
        if (igual == false) {

            for (int u = 1; u < matriz2.length; u++) {
                double comp1 = Double.parseDouble(nome1);
                double comp2 = Double.parseDouble(matriz2[u][0]);
                if (comp1 < comp2) {
                    if (u == 1) {
                        //aux.add(u);
                        break;
                    } else {
                        aux.add(u);
                        aux.add(u - 1);
                        break;
                    }

                }

            }
        }
        if (aux.isEmpty()) {
            System.out.println("Não encontrou a linha do valor requerido (M: EncLinhaDaColuna): " + nome1);
        }
        return aux;
    }
    //vai encontrar a linha com dois parametros de busca na mesma linha

    public static int EncLinhaCom2Parame(String[][] matriz2, String valor1, String valor2) {
        int j = 0;
        int i = 0;
        int encontrou = 0;
        boolean achou1 = false;
        boolean achou2 = false;

        for (i = 0; i < matriz2.length; i++) {
            for (j = 0; j < matriz2[i].length; j++) {
                if (matriz2[i][j].equals(valor1)) {
                    achou1 = true;

                }
                if (matriz2[i][j].equals(valor2)) {
                    achou2 = true;

                }
            }
            if (achou1 == true && achou2 == true) {
                encontrou = i;
                break;
            }

            achou1 = false;
            achou2 = false;
        }
        if (encontrou == 0) {
            System.out.println("Não encontrou a linha dos valores requeridos (M: EncLinhaCom2Parame): " + valor1 + " e " + valor2);
        }
        return encontrou;
    }

    public static int EncLinhaCom4Parame(String[][] matriz2, String valor1, String valor2, String valor3, String valor4) {
        int j = 0;
        int i = 0;
        int encontrou = 0;
        boolean achou1 = false;
        boolean achou2 = false;
        boolean achou3 = false;
        boolean achou4 = false;

        for (i = 0; i < matriz2.length; i++) {
            for (j = 0; j < matriz2[i].length; j++) {
                if (matriz2[i][j].equals(valor1)) {
                    achou1 = true;

                }
                if (matriz2[i][j].equals(valor2)) {
                    achou2 = true;

                }
                if (matriz2[i][j].equals(valor3)) {
                    achou3 = true;

                }
                if (matriz2[i][j].equals(valor4)) {
                    achou4 = true;

                }
            }
            if (achou1 == true && achou2 == true && achou3 == true && achou4 == true) {
                encontrou = i;
                break;
            }

            achou1 = false;
            achou2 = false;
            achou3 = false;
            achou4 = false;
        }
        if (encontrou == 0) {
            System.out.println("Não encontrou a linha dos valores requeridos (M: EncLinhaCom4Parame): " + valor1 + ", " + valor2 + ", " + valor3 + " e " + valor4);
        }
        return encontrou;
    }

    //vai criar uma submatriz da matriz enviada com quatro valores iguais na mesma
    //linha e vai preencher a nova matriz com os valores das quatro linhas a partir de uma coluna escolhida "coluna" 
    //o numColunas é o numero de colunas da nova matriz a partir da coluna escolhida.
    public static String[][] subgrupo(String[][] matriz2, String valor1, String valor2, String valor3, String valor4, String coluna, int numColunas) {
        int j = 0;
        int i = 0;
        int encontrou = 0;
        boolean achou1 = false;
        boolean achou2 = false;
        boolean achou3 = false;
        boolean achou4 = false;
        ArrayList<Integer> linhas = new ArrayList();

        for (i = 0; i < matriz2.length; i++) {
            for (j = 0; j < matriz2[i].length; j++) {
                if (matriz2[i][j].equals(valor1)) {
                    achou1 = true;

                }
                if (matriz2[i][j].equals(valor2)) {
                    achou2 = true;

                }
                if (matriz2[i][j].equals(valor3)) {
                    achou3 = true;

                }
                if (matriz2[i][j].equals(valor4)) {
                    achou4 = true;

                }
            }
            if (achou1 == true && achou2 == true && achou3 == true && achou4 == true) {
                linhas.add(i);
            }
            achou1 = false;
            achou2 = false;
            achou3 = false;
            achou4 = false;
        }

        int Coluna = EncColuna(matriz2, coluna);

        String[][] subMatriz = new String[linhas.size() + 1][numColunas];
        ArrayList<String> aux = new ArrayList();

        for (int h = Coluna; h < matriz2[0].length; h++) {
            aux.add(matriz2[0][h]);
        }
        for (int h = 0; h < aux.size(); h++) {
            subMatriz[0][h] = aux.get(h);
        }
        aux.clear();
        int cont = linhas.get(0);
        int e = 1;
        for (int g = cont; g < linhas.size() + cont; g++) {

            for (int f = Coluna; f < matriz2[g].length; f++) {
                aux.add(matriz2[g][f]);
            }

            for (int s = 0; s < subMatriz[e].length; s++) {
                subMatriz[e][s] = aux.get(s);
            }
            e = e + 1;
            aux.clear();
        }
        if (subMatriz.length == 0) {
            System.out.println("Não encontrou os valores iguais na mesma linha da tabela. (M: subgrupo): " + valor1 + ", " + valor2 + ", " + valor3 + ", " + valor4);
        }
        return subMatriz;
    }

//vai pegar valor da matriz com uma referencia de coluna e com uma OU duas referencias de linhas. Isso acontece
    //quando o valor entre as linhas não existe e leva em consideração uma interpolação dos valores proximos a ele, se
    //for numeros.
    public static String pegarValorMatrizEspecial(String[][] matriz1, String nomeLinha, String nomeColuna) {

        ArrayList<Integer> L = Matriz.EncLinhaDaColuna(matriz1, nomeLinha);
        double y = 0;
        String retorno = null;
        int C = Matriz.EncColuna(matriz1, nomeColuna);

        if (L.size() > 1) { //existem duas linhas. Interpolação: y = y1 + {([x - x1]/[x2 - x1])x(y2 - y1)}

            double y1 = Numero.stringToDouble(matriz1[L.get(0)][C], 0);
            double y2 = Numero.stringToDouble(matriz1[L.get(1)][C], 0);

            double x = Numero.stringToDouble(nomeLinha.replace(",", "."), 0);
            double x1 = Numero.stringToDouble(matriz1[L.get(0)][0], 0);
            double x2 = Numero.stringToDouble(matriz1[L.get(1)][0], 0);

            y = y1 + (((x - x1) / (x2 - x1)) * (y2 - y1));
            retorno = Double.toString(y);

        } else {

            retorno = matriz1[L.get(0)][C];

        }

        return retorno;
    }

    //metodo especifico para os calculos pelo metodo das cavidades zonais. pg61
    public static String pegarValorMatrizEspecial2(String[][] matriz1, double reflEfetivoCaviTeto, double reflPa, double relCavidadeRecinto, double reflPi) {

        ArrayList<Integer> L = Matriz.EncLinhaDaColuna(matriz1, Numero.decimal(relCavidadeRecinto, "##.##"));
        int L1 = L.get(1);
        int L2 = L.get(0);
        String reflPaString = Numero.decimal(reflPa, "##.##");
        double y = 0;
        String retorno = null;
        double fu1 = 0;
        double fu2 = 0;
        boolean indica = false;
        int C = 0;

        if (!Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("80") && !Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("50") && !Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("10")) {
            indica = true;
            if (reflEfetivoCaviTeto < 80 && reflEfetivoCaviTeto > 50) {
                int C1 = Matriz.EncColuna(matriz1, "CT80/PA" + reflPaString + "/CP20");
                int C2 = Matriz.EncColuna(matriz1, "CT50/PA" + reflPaString + "/CP20");
                double ft1 = Double.parseDouble(matriz1[L1][C1]);
                double ft2 = Double.parseDouble(matriz1[L1][C2]);
                double ft3 = Double.parseDouble(matriz1[L2][C1]);
                double ft4 = Double.parseDouble(matriz1[L2][C2]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (80 - reflEfetivoCaviTeto)) / (80 - 50)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (80 - reflEfetivoCaviTeto)) / (80 - 50)) - ft3) * (-1);

            } else if (reflEfetivoCaviTeto < 50 && reflEfetivoCaviTeto > 10) {
                int C1 = Matriz.EncColuna(matriz1, "CT50/PA" + reflPaString + "/CP20");
                int C2 = Matriz.EncColuna(matriz1, "CT10/PA" + reflPaString + "/CP20");
                double ft1 = Double.parseDouble(matriz1[L1][C1]);
                double ft2 = Double.parseDouble(matriz1[L1][C2]);
                double ft3 = Double.parseDouble(matriz1[L2][C1]);
                double ft4 = Double.parseDouble(matriz1[L2][C2]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (50 - reflEfetivoCaviTeto)) / (50 - 10)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (50 - reflEfetivoCaviTeto)) / (50 - 10)) - ft3) * (-1);

            }

        } else {
            switch (Numero.decimal(reflEfetivoCaviTeto, "##.##")) {
                case "80":
                    C = Matriz.EncColuna(matriz1, "CT80/" + "PA" + reflPaString + "/CP20");
                    break;
                case "50":
                    C = Matriz.EncColuna(matriz1, "CT50/" + "PA" + reflPaString + "/CP20");
                    break;
                case "10":
                    C = Matriz.EncColuna(matriz1, "CT10/" + "PA" + reflPaString + "/CP20");
                    break;
            }
        }

        double y1 = 0;
        double y2 = 0;

        if (L.size() > 1) { //existem duas linhas. Interpolação: y = y1 + {([x - x1]/[x2 - x1])x(y2 - y1)}
            if (indica == true) {
                y1 = fu1;
                y2 = fu2;
            } else {
                y1 = Numero.stringToDouble(matriz1[L1][C], 0);
                y2 = Numero.stringToDouble(matriz1[L2][C], 0);
            }

            double x = relCavidadeRecinto;
            double x1 = Numero.stringToDouble(matriz1[L1][0], 0);
            double x2 = Numero.stringToDouble(matriz1[L2][0], 0);

            y = y1 + (((x - x1) / (x2 - x1)) * (y2 - y1));
            retorno = Double.toString(y);

        } else {

            retorno = matriz1[L.get(0)][C];

        }

        return retorno;
    }

    //metodo especifico para os calculos pelo metodo das cavidades zonais (para a tab 2.15). pg61
    public static String pegarValorMatrizEspecial3(String[][] matriz, double sujeiraEsperada, String iluminamento, double relCavidadeRecinto) {

        ArrayList<Integer> L = Matriz.EncLinhaDaColuna(matriz, Numero.decimal(relCavidadeRecinto, "##.##"));
        int L1 = L.get(1);
        int L2 = L.get(0);
        double y = 0;
        String retorno = null;
        double fu1 = 0;
        double fu2 = 0;
        boolean indica = false;
        int C = 0;
        String abreIluminamento = "";

        switch (iluminamento) {
            case "Direto":
                abreIluminamento = "D";
                break;
            case "Semi-indireto":
                abreIluminamento = "SI";
                break;
            case "Semidireto":
                abreIluminamento = "S";
                break;
            case "Direto-indireto":
                abreIluminamento = "DI";
                break;
            case "Indireto":
                abreIluminamento = "I";
                break;
        }

        if (!Numero.decimal(sujeiraEsperada, "##.##").equals("10") && !Numero.decimal(sujeiraEsperada, "##.##").equals("20") && !Numero.decimal(sujeiraEsperada, "##.##").equals("30") && !Numero.decimal(sujeiraEsperada, "##.##").equals("40")) {
            indica = true;
            if ((sujeiraEsperada) > 10 && (sujeiraEsperada) < 20) {
                int C1 = Matriz.EncColuna(matriz, abreIluminamento + "10");
                int C2 = Matriz.EncColuna(matriz, abreIluminamento + "20");
                double ft1 = Double.parseDouble(matriz[L1][C1]);
                double ft2 = Double.parseDouble(matriz[L1][C2]);
                double ft3 = Double.parseDouble(matriz[L2][C1]);
                double ft4 = Double.parseDouble(matriz[L2][C2]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (10 - sujeiraEsperada)) / (10 - 20)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (10 - sujeiraEsperada)) / (10 - 20)) - ft3) * (-1);

            } else if (sujeiraEsperada > 20 && sujeiraEsperada < 30) {
                int C1 = Matriz.EncColuna(matriz, abreIluminamento + "20");
                int C2 = Matriz.EncColuna(matriz, abreIluminamento + "30");
                double ft1 = Double.parseDouble(matriz[C1][L.get(0)]);
                double ft2 = Double.parseDouble(matriz[C2][L.get(0)]);
                double ft3 = Double.parseDouble(matriz[C1][L.get(1)]);
                double ft4 = Double.parseDouble(matriz[C2][L.get(1)]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (20 - sujeiraEsperada)) / (20 - 30)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (20 - sujeiraEsperada)) / (20 - 30)) - ft3) * (-1);

            } else if (sujeiraEsperada > 30 && sujeiraEsperada < 40) {
                int C1 = Matriz.EncColuna(matriz, abreIluminamento + "30");
                int C2 = Matriz.EncColuna(matriz, abreIluminamento + "40");
                double ft1 = Double.parseDouble(matriz[C1][L.get(0)]);
                double ft2 = Double.parseDouble(matriz[C2][L.get(0)]);
                double ft3 = Double.parseDouble(matriz[C1][L.get(1)]);
                double ft4 = Double.parseDouble(matriz[C2][L.get(1)]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (30 - sujeiraEsperada)) / (30 - 40)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (30 - sujeiraEsperada)) / (30 - 40)) - ft3) * (-1);

            }

        } else {
            switch (Numero.decimal(sujeiraEsperada, "##.##")) {
                case "10":
                    C = Matriz.EncColuna(matriz, abreIluminamento + "10");
                    break;
                case "20":
                    C = Matriz.EncColuna(matriz, abreIluminamento + "20");
                    break;
                case "30":
                    C = Matriz.EncColuna(matriz, abreIluminamento + "30");
                    break;
                case "40":
                    C = Matriz.EncColuna(matriz, abreIluminamento + "40");
                    break;
            }
        }

        double y1 = 0;
        double y2 = 0;

        if (L.size() > 1) { //existem duas linhas. Interpolação: y = y1 + {([x - x1]/[x2 - x1])x(y2 - y1)}
            if (indica == true) {
                y1 = fu1;
                y2 = fu2;
            } else {
                y1 = Numero.stringToDouble(matriz[L1][C], 0);
                y2 = Numero.stringToDouble(matriz[L2][C], 0);
            }

            double x = relCavidadeRecinto;
            double x1 = Numero.stringToDouble(matriz[L1][0], 0);
            double x2 = Numero.stringToDouble(matriz[L2][0], 0);

            y = (y1 + (((x - x1) / (x2 - x1)) * (y2 - y1))) / 100;
            retorno = (Double.toString(y));

        } else {

            retorno = Numero.decimal(Double.parseDouble(matriz[L.get(0)][C]) / 100, "##.##");

        }

        return retorno;
    }

    //metodo especifico para os calculos pelo metodo das cavidades zonais. pg61
    public static String pegarValorMatrizEspecial4(String[][] matriz1, double reflEfetivoCaviTeto, double reflPa, double relCavidadeRecinto) {

        ArrayList<Integer> L = Matriz.EncLinhaDaColuna(matriz1, Numero.decimal(relCavidadeRecinto, "##.##"));
        int L1 = L.get(1);
        int L2 = L.get(0);
        String reflPaString = Numero.decimal(reflPa, "##.##");
        double y = 0;
        String retorno = null;
        double fu1 = 0;
        double fu2 = 0;
        boolean indica = false;
        int C = 0;

        if (!Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("80") && !Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("70") && !Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("50") && !Numero.decimal(reflEfetivoCaviTeto, "##.##").equals("10")) {
            indica = true;
            if (reflEfetivoCaviTeto < 80 && reflEfetivoCaviTeto > 70) {
                int C1 = Matriz.EncColuna(matriz1, "CT80/PA" + reflPaString);
                int C2 = Matriz.EncColuna(matriz1, "CT70/PA" + reflPaString);
                double ft1 = Double.parseDouble(matriz1[L1][C1]);
                double ft2 = Double.parseDouble(matriz1[L1][C2]);
                double ft3 = Double.parseDouble(matriz1[L2][C1]);
                double ft4 = Double.parseDouble(matriz1[L2][C2]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (80 - reflEfetivoCaviTeto)) / (80 - 70)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (80 - reflEfetivoCaviTeto)) / (80 - 70)) - ft3) * (-1);

            } else if (reflEfetivoCaviTeto < 70 && reflEfetivoCaviTeto > 50) {
                int C1 = Matriz.EncColuna(matriz1, "CT70/PA" + reflPaString);
                int C2 = Matriz.EncColuna(matriz1, "CT50/PA" + reflPaString);
                double ft1 = Double.parseDouble(matriz1[C1][L.get(0)]);
                double ft2 = Double.parseDouble(matriz1[C2][L.get(0)]);
                double ft3 = Double.parseDouble(matriz1[C1][L.get(1)]);
                double ft4 = Double.parseDouble(matriz1[C2][L.get(1)]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (70 - reflEfetivoCaviTeto)) / (70 - 50)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (70 - reflEfetivoCaviTeto)) / (70 - 50)) - ft3) * (-1);

            } else if (reflEfetivoCaviTeto < 50 && reflEfetivoCaviTeto > 10) {
                int C1 = Matriz.EncColuna(matriz1, "CT50/PA" + reflPaString);
                int C2 = Matriz.EncColuna(matriz1, "CT10/PA" + reflPaString);
                double ft1 = Double.parseDouble(matriz1[C1][L.get(0)]);
                double ft2 = Double.parseDouble(matriz1[C2][L.get(0)]);
                double ft3 = Double.parseDouble(matriz1[C1][L.get(1)]);
                double ft4 = Double.parseDouble(matriz1[C2][L.get(1)]);
                //pag61 interpolação
                fu1 = ((((ft1 - ft2) * (50 - reflEfetivoCaviTeto)) / (50 - 10)) - ft1) * (-1);
                fu2 = ((((ft3 - ft4) * (50 - reflEfetivoCaviTeto)) / (50 - 10)) - ft3) * (-1);

            }

        } else {
            switch (Numero.decimal(reflEfetivoCaviTeto, "##.##")) {
                case "80":
                    C = Matriz.EncColuna(matriz1, "CT80/" + "PA" + reflPaString);
                    break;
                case "70":
                    C = Matriz.EncColuna(matriz1, "CT70/" + "PA" + reflPaString);
                    break;
                case "50":
                    C = Matriz.EncColuna(matriz1, "CT50/" + "PA" + reflPaString);
                    break;
                case "10":
                    C = Matriz.EncColuna(matriz1, "CT10/" + "PA" + reflPaString);
                    break;
            }
        }

        double y1 = 0;
        double y2 = 0;

        if (L.size() > 1) { //existem duas linhas. Interpolação: y = y1 + {([x - x1]/[x2 - x1])x(y2 - y1)}
            if (indica == true) {
                y1 = fu1;
                y2 = fu2;
            } else {
                y1 = Numero.stringToDouble(matriz1[L1][C], 0);
                y2 = Numero.stringToDouble(matriz1[L2][C], 0);
            }

            double x = relCavidadeRecinto;
            double x1 = Numero.stringToDouble(matriz1[L1][0], 0);
            double x2 = Numero.stringToDouble(matriz1[L2][0], 0);

            y = y1 + (((x - x1) / (x2 - x1)) * (y2 - y1));
            retorno = Double.toString(y);

        } else {

            retorno = matriz1[L.get(0)][C];

        }

        return retorno;
    }

    public static ArrayList pegarValor(String[][] matriz, String Linha, String Coluna) {

        int L = EncLinhaNoGrupo(matriz, Linha);
        int C = EncColuna(matriz, Coluna);
        ArrayList LC = new ArrayList();
        LC.add(L);
        LC.add(C);

        return LC;
    }

    public static String pegarValorCom4ParameLinha(String[][] matriz, String Linha1, String Linha2, String Linha3, String Linha4, String Coluna) {

        int L = EncLinhaCom4Parame(matriz, Linha1, Linha2, Linha3, Linha4);
        int C = EncColuna(matriz, Coluna);
        String local = matriz[L][C];

        return local;
    }

    public static String pegarValor2(String[][] matriz, String nomeLinha, String nomeColuna) {

        int L = Matriz.EncLinhaNoGrupo(matriz, nomeLinha);
        int C = Matriz.EncColuna(matriz, nomeColuna);
        String local = matriz[L][C];

        return local;
    }

    public static String pegarValor3(String[][] matriz, String nLinha, String nomeColuna) {

        int L = Integer.parseInt(nLinha);
        int C = Matriz.EncColuna(matriz, nomeColuna);
        String local = matriz[L][C];

        return local;
    }

//encontra o valor dentro da primeira coluna com referencia ao valor informado dentro da matriz em uma coluna informada
    public static String EncontraLinhaPcoluna(String[][] matriz2, String valorColuna, String nomeColuna) {

        Double[] temp;
        int Linha = 0;

        int C = EncColuna(matriz2, nomeColuna);
        temp = new Double[matriz2.length - 1];

        for (int i = 1; i < matriz2.length; i++) {

            //DecimalFormat myFormatter = new DecimalFormat("###.##");
            //String texto = myFormatter.format(Double.parseDouble(matriz2[i][C]));
            //double numero = Double.parseDouble(texto);
            //temp[i - 1] = numero;
            temp[i - 1] = Double.parseDouble(matriz2[i][C].replaceAll(",", "."));
        }
        for (int t = 0; t < temp.length; t++) {
            if (temp[t] >= Double.parseDouble(valorColuna)) {
                Linha = t + 1;

                break;
            } else {
                Linha = temp.length;
            }
        }
        if (matriz2[Linha][0].isEmpty()) {
            System.out.println("Não encontrou o valor. (M: EncontraLinhaPcoluna): " + valorColuna + " e " + nomeColuna);
        }
        double valor = Double.parseDouble(matriz2[Linha][0]);
        return Numero.decimal(valor, "##.##");
    }

    //para valores numéricos
    public static int EncontraLinhaPcoluna(String[][] matriz, double valorColuna, String nomeColuna) {

        int Linha = 0;

        int C = EncColuna(matriz, nomeColuna);
        Double[] temp = new Double[matriz.length - 1];

        for (int i = 1; i < matriz.length; i++) {

            //DecimalFormat myFormatter = new DecimalFormat("###.##");
            //String texto = myFormatter.format(Double.parseDouble(matriz2[i][C]));
            //double numero = Double.parseDouble(texto);
            //temp[i - 1] = numero;
            temp[i - 1] = Double.parseDouble(matriz[i][C].replaceAll(",", "."));

        }
        for (int t = 0; t < temp.length; t++) {
            if (temp[t] >= valorColuna) {
                Linha = t + 1;

                break;
            }
        }
        if (matriz[Linha][0].isEmpty()) {
            System.out.println("Não encontrou o valor. (M: EncontraLinhaPcoluna2): " + valorColuna + " e " + nomeColuna);
        }
        return Linha;
    }

    public static String pegarValorMatriz(String[][] matriz, int linha, int coluna) {

        String valor = matriz[linha][coluna];

        if (matriz[linha][coluna].isEmpty()) {
            System.out.println("Não encontrou o valor. (M: pegarValorMatriz): " + linha + " e " + coluna);
        }
        return valor;
    }

    public static String[][] copia(String[][] matriz) {
        String[][] copia = new String[matriz.length][matriz[0].length];

        for (int i = 0; i < copia.length; i++) {
            for (int j = 0; j < copia[i].length; j++) {

                copia[i][j] = "";

            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                copia[i][j] = matriz[i][j];
            }
        }
        return copia;
    }

//copia somente as quantidades informadas
    public static String[][] copia2(String[][] matriz, int qtdLinhas, int qtdColunas) {
        String[][] copia = new String[matriz.length][matriz[0].length];

        for (int i = 0; i < copia.length; i++) {
            for (int j = 0; j < copia[i].length; j++) {

                copia[i][j] = "";

            }
        }

        for (int i = 0; i < qtdLinhas; i++) {
            for (int j = 0; j < qtdColunas; j++) {

                copia[i][j] = matriz[i][j];
            }
        }
        return copia;
    }
// Metodo usado para buscar a bitola na tabela de capacidade de corrente

    public static String pegaValor(String[][] matriz, String nomeColuna, double valorDeBusca, String colunaResposta) {

        int linha = 0;
        int coluna = EncColuna(matriz, nomeColuna);
        int coluna2 = EncColuna(matriz, colunaResposta);
        Double[] temp = new Double[matriz.length - 1];

        for (int i = 1; i < matriz.length; i++) {
            temp[i - 1] = Double.parseDouble(matriz[i][coluna].replaceAll(",", "."));
        }
        for (int t = 0; t < temp.length; t++) {
            if (temp[t] < valorDeBusca) {
                linha = t;

                break;
            }
        }

        return pegarValorMatriz(matriz, linha, coluna2);
    }

}
