/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.janelas;

import chc.eletrica8.controle.Ids;
import chc.eletrica8.dao.ConnectionFactory;
import chc.eletrica8.entidades.Fonte;
import chc.eletrica8.entidades.Projeto;
import chc.eletrica8.servico.ProjetoService;
import chc.eletrica8.servico.report.ReportUtils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;


/**
 *
 * @author chris
 */
public class RelatorioProjetoFrm extends javax.swing.JInternalFrame {

    /**
     * Creates new form ProjetoFrm
     */
    public RelatorioProjetoFrm() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollEsquerdo = new javax.swing.JScrollPane();
        painelEsquerdo = new javax.swing.JPanel();
        jbtnQuadroCarga = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Projeto");
        setName("ProjetoFrm"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollEsquerdo.setBorder(null);
        scrollEsquerdo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEsquerdo.setMinimumSize(new java.awt.Dimension(15, 5));
        scrollEsquerdo.setPreferredSize(new java.awt.Dimension(100, 100));

        painelEsquerdo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relatórios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        painelEsquerdo.setPreferredSize(new java.awt.Dimension(50, 100));

        jbtnQuadroCarga.setText("Quadro de carga");
        jbtnQuadroCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnQuadroCargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnQuadroCarga)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnQuadroCarga)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        scrollEsquerdo.setViewportView(painelEsquerdo);

        getContentPane().add(scrollEsquerdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 9, 330, 120));

        setBounds(0, 0, 364, 178);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnQuadroCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnQuadroCargaActionPerformed
        /*
         * Obtendo o arquivo do relatório.
         * Note que estamos utilizando um InputStream para obter o arquivo que
         * está dentro do nosso projeto. Fazendo isso, não teremos problema
         * quando nosso projeto for empacotado em um .jar.
         *
         * Note que o caminho do .jasper inicia com /, ou seja, a raiz da
         * localização das classes compiladas do nosso projeto
         * (o pacote default).
         *
         * Utilize a aba Files (canto superior esquerdo) e veja que os arquivos
         * .jasper e .jrxml são copiados para o diretório /build/classes
         * e por consequencia para o .jar que for criado.
         *
         * Se não os estiver vendo, mande dar um Clean and Build no projeto
         * (botão direito no nó raiz do projeto, Clean and Build (Limpar e Construir)
         *
         */
        Projeto projeto = ProjetoService.getById(Ids.getIdProjeto());
        for (Fonte f : projeto.getFontes()) {
            //p.getDemandaMaxVA();
           // p.getPotenciaInstalVA();
            //p.getFatorDemanda();
        }

        InputStream inputStream = getClass().getResourceAsStream("/relatorios/Projetos.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        try {
            // abre o relatório
            ReportUtils.openReport(
                    "Projetos",//
                    inputStream,//
                    parametros,//
                    ConnectionFactory.getConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }//GEN-LAST:event_jBtnQuadroCargaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnQuadroCarga;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JScrollPane scrollEsquerdo;
    // End of variables declaration//GEN-END:variables
}
