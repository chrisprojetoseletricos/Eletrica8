/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;


import chc.eletrica8.dao.ConnectionFactory;
import chc.eletrica8.entidades.Carga;
import chc.eletrica8.servico.CargaService;
import chc.eletrica8.servico.report.ReportUtils;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author chris
 */
public class testeRelatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public void abrirRelatorioClientes() {

        InputStream inputStream = getClass().getResourceAsStream("/LocacoesPorClientes.jasper");

        Map parametros = new HashMap();
        parametros.put("nomeCliente", "F%");

        try {

            ReportUtils.openReport("Locações por Clientes", inputStream, parametros, (Connection) ConnectionFactory.getEntityManager());

        } catch (JRException exc) {
            exc.printStackTrace();
        }

    }

    public void abrirRelatorioClientesDS() {

        InputStream inputStream = getClass().getResourceAsStream("/teste.jasper");

        Map parametros = new HashMap();

        // criando os dados que serão passados ao datasource
        List<Carga> dados = new ArrayList();
        dados = CargaService.getAll();

        // criando o datasource com os dados criados
        JRDataSource ds = new JRBeanCollectionDataSource(dados);

        try {

            // passando o datasource para o método de criação e exibição do relatório
            ReportUtils.openReport("Clientes - Bean Collection Data Source", inputStream, parametros,
                    ds);

        } catch (JRException exc) {
            exc.printStackTrace();
        }

    }

}
