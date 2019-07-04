/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculos;

import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.servico.FonteService;
import chc.eletrica8.servico.ProjetoService;

public class testeService {

    public static void main(String[] args) {

       Fonte fonte = FonteService.getById(1).clonarSemID();
       
       FonteService.salva(fonte);

    }
}
