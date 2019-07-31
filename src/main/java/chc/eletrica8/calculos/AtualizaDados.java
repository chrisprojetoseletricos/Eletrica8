/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.calculos;

import chc.eletrica8.entidades.Carga;
import chc.eletrica8.servico.CargaService;
import chc.eletrica8.servico.CircuitoService;

/**
 *
 * @author chris
 */
public class AtualizaDados {

    public static void exec(Carga carga) {
        carga.getCircuito().tipoCircuito();
        carga.getCircuito().defineComprimento();
        carga.setTensao();
        carga.getCircuito().bitolaCondutor();
        carga.getCircuito().getCorrenteIB();
        CargaService.salva(carga);
        CircuitoService.salva(carga.getCircuito());
    }

}
