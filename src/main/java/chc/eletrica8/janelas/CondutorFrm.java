/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.janelas;

import chc.eletrica8.entidades.Condutor;
import chc.eletrica8.enums.AgrupaConduto;
import chc.eletrica8.enums.Enterrado;
import chc.eletrica8.enums.EspacamentoCabos;
import chc.eletrica8.enums.EspacamentoEletrodutos;
import chc.eletrica8.enums.Instalacao;
import chc.eletrica8.uteis.ApenasNumero;
import chc.eletrica8.uteis.Numero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author chris
 */
public class CondutorFrm extends javax.swing.JDialog implements KeyListener {

    private Condutor condutor;
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form TesteJDialog
     */
    public CondutorFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        adicionarKeyListener();
        cbInstalacaoItens();
        cbEspacamentoCabosItens();
        cbEnterradoItens();
        cbAgrupamento();
        cbEspacamentoEletrodutosItens();
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
        jLabel1 = new javax.swing.JLabel();
        campoQuedaTensao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbNCircuitosAgrupados = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbMaterial = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbAgrupamento = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbMultipolar = new javax.swing.JComboBox<>();
        cbInstalacao = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbIsolacao = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbEspacamentoCabos = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbBitolasSucessivas = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbNCamadas = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbEnterrado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        campoResistividade = new javax.swing.JTextField();
        campoTemperatura = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoComprimento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbEspacamentoEletrodutos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        scrollEsquerdo.setBorder(null);
        scrollEsquerdo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEsquerdo.setPreferredSize(new java.awt.Dimension(300, 400));

        painelEsquerdo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        painelEsquerdo.setPreferredSize(new java.awt.Dimension(300, 400));

        jLabel1.setText("Queda de tensão:");

        campoQuedaTensao.setText("1");
        campoQuedaTensao.setName("campoQuedaTensao"); // NOI18N

        jLabel3.setText("Multipolar:");

        jLabel5.setText("Instalação:");

        jLabel6.setText("Nº circuitos agrupados:");

        cbNCircuitosAgrupados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        jLabel7.setText("Material:");

        cbMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cobre", "Alumínio" }));

        jLabel8.setText("Agrupamento:");

        cbAgrupamento.setName("cbAgrupamento"); // NOI18N

        jLabel9.setText("Temperatura ambiente (°C):");

        cbMultipolar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não", "Sim" }));

        cbInstalacao.setToolTipText("");

        jLabel11.setText("Isolação:");

        cbIsolacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PVC", "XLPE" }));

        jLabel12.setText("Espaçamento entre cabos:");

        cbEspacamentoCabos.setName("cbEspacamentoCabos"); // NOI18N

        jLabel13.setText("Bitolas sucessivas?:");

        cbBitolasSucessivas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não", "Sim" }));

        jLabel14.setText("Nº de camadas:");

        cbNCamadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel15.setText("Enterrado?:");

        cbEnterrado.setName("cbEnterrado"); // NOI18N

        jLabel4.setText("Resistividade térmica do solo:");

        campoResistividade.setText("0");
        campoResistividade.setName("campoResistividade"); // NOI18N

        campoTemperatura.setText("30");
        campoTemperatura.setName("campoTemperatura"); // NOI18N

        jLabel10.setText("Comprimento (m):");

        campoComprimento.setText("5");
        campoComprimento.setName("campoComprimento"); // NOI18N

        jLabel16.setText("Espaçamento entre duto:");

        cbEspacamentoEletrodutos.setName("cbEspacamentoEletrodutos"); // NOI18N

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9))
                        .addGap(24, 24, 24)
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoQuedaTensao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(campoTemperatura)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(55, 55, 55)
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMaterial, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbNCircuitosAgrupados, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbInstalacao, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbMultipolar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoComprimento)
                            .addComponent(campoResistividade)))
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11)
                            .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                    .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel14))
                                    .addGap(47, 47, 47)))
                            .addComponent(jLabel16))
                        .addGap(35, 35, 35)
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEspacamentoEletrodutos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEnterrado, javax.swing.GroupLayout.Alignment.TRAILING, 0, 273, Short.MAX_VALUE)
                            .addComponent(cbNCamadas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbBitolasSucessivas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEspacamentoCabos, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbIsolacao, javax.swing.GroupLayout.Alignment.TRAILING, 0, 273, Short.MAX_VALUE)))
                    .addComponent(cbAgrupamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoQuedaTensao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(campoTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campoComprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoResistividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbMultipolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbInstalacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbNCircuitosAgrupados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAgrupamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbIsolacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbEspacamentoCabos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbEspacamentoEletrodutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbBitolasSucessivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbNCamadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbEnterrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        scrollEsquerdo.setViewportView(painelEsquerdo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        condutor = getDados();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CondutorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CondutorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CondutorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CondutorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CondutorFrm dialog = new CondutorFrm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void cbInstalacaoItens() {
        cbInstalacao.removeAllItems();
        cbInstalacao.addItem(null);
        for (Instalacao var : Instalacao.getLista()) {
            cbInstalacao.addItem(var.name());
        }
    }

    private Instalacao cbInstalacaoItensTrans(String valor) {
        Instalacao ins = null;
        for (Instalacao var : Instalacao.getLista()) {
            if (var.name().equals(valor)) {
                ins = var;
                break;
            }
        }
        return ins;
    }

    private void cbEspacamentoCabosItens() {
        cbEspacamentoCabos.removeAllItems();
        cbEspacamentoCabos.addItem(null);
        for (EspacamentoCabos var : EspacamentoCabos.getLista()) {
            cbEspacamentoCabos.addItem(var.getNome());
        }
    }

    private EspacamentoCabos cbEspacamentoCabosItensTrans(String valor) {
        EspacamentoCabos ins = null;
        for (EspacamentoCabos var : EspacamentoCabos.getLista()) {
            if (var.getNome().equals(valor)) {
                ins = var;
                break;
            }else{
                ins = EspacamentoCabos.NaoAplicavel;
            }
        }
        return ins;
    }

    private void cbEnterradoItens() {
        cbEnterrado.removeAllItems();
        cbEnterrado.addItem(null);
        for (Enterrado var : Enterrado.getLista()) {
            cbEnterrado.addItem(var.getNome());
        }
    }

    private Enterrado cbEnterradoItensTrans(String valor) {
        Enterrado ins = null;
        for (Enterrado var : Enterrado.getLista()) {
            if (var.getNome().equals(valor)) {
                ins = var;
                break;
            }
        }
        return ins;
    }

    private void cbAgrupamento() {
        cbAgrupamento.removeAllItems();
        cbAgrupamento.addItem(null);
        for (AgrupaConduto var : AgrupaConduto.getLista()) {
            cbAgrupamento.addItem(var.getNome());
        }
    }

    private AgrupaConduto cbAgrupamentoTrans(String valor) {
        AgrupaConduto ins = null;
        for (AgrupaConduto var : AgrupaConduto.getLista()) {
            if (var.getNome().equals(valor)) {
                ins = var;
                break;
            }else{
                ins = AgrupaConduto.Agrupa6;
            }
        }
        return ins;
    }

    private void cbEspacamentoEletrodutosItens() {
        cbEspacamentoEletrodutos.removeAllItems();
        cbEspacamentoEletrodutos.addItem(null);
        for (EspacamentoEletrodutos var : EspacamentoEletrodutos.getLista()) {
            cbEspacamentoEletrodutos.addItem(var.getNome());
        }
    }

    private EspacamentoEletrodutos cbEspacamentoEletrodutosItensTrans(String valor) {
        EspacamentoEletrodutos ins = null;
        for (EspacamentoEletrodutos var : EspacamentoEletrodutos.getLista()) {
            if (var.getNome().equals(valor)) {
                ins = var;
                break;
            }else{
                ins = EspacamentoEletrodutos.NaoAplicavel;
            }
        }
        return ins;
    }

    private Condutor getDados() {

        condutor = new Condutor();

        condutor.setQuedaTensao(Numero.stringToDouble(campoQuedaTensao.getText(), 0));
        condutor.setResistiTermica(Numero.stringToDouble(campoResistividade.getText(), 0));
        condutor.setModoInstalacao(cbInstalacaoItensTrans(cbInstalacao.getModel().getSelectedItem().toString()));
        condutor.setMaterial((String) cbMaterial.getModel().getSelectedItem());
        condutor.setIsolacao((String) cbIsolacao.getModel().getSelectedItem());
        condutor.setEnterrado(cbEnterradoItensTrans(cbEnterrado.getModel().getSelectedItem().toString()));
        condutor.setEspacoCabos(cbEspacamentoCabosItensTrans(cbEspacamentoCabos.getModel().getSelectedItem().toString()));
        condutor.setEspacoEletrodutos(cbEspacamentoEletrodutosItensTrans(cbEspacamentoEletrodutos.getModel().getSelectedItem().toString()));
        condutor.setMultipolar((String) cbMultipolar.getModel().getSelectedItem());
        condutor.setFormaAgrupa(cbAgrupamentoTrans(cbAgrupamento.getModel().getSelectedItem().toString()));
        condutor.setBitolaSucessiva((String) cbBitolasSucessivas.getModel().getSelectedItem());
        condutor.setnCirAgrupa(Numero.stringToInteger(cbNCircuitosAgrupados.getModel().getSelectedItem().toString(), 1));
        condutor.setnCamadas(Numero.stringToInteger(cbNCamadas.getModel().getSelectedItem().toString(), 0));
        condutor.setTemperatura(Numero.stringToInteger(campoTemperatura.getText(), 0));
        condutor.setComprimento(Numero.stringToDouble(campoComprimento.getText(), 0));

        return condutor;
    }

    public Condutor setDados(Condutor condutor) {
        if (condutor != null) {
            campoQuedaTensao.setText(Numero.decimal(condutor.getQuedaTensao(), "##.##"));
            campoResistividade.setText(Numero.decimal(condutor.getResistiTermica(), "##.##"));
            campoTemperatura.setText(Integer.toString(condutor.getTemperatura()));
            campoComprimento.setText(Numero.decimal(condutor.getComprimento(), "##.##"));
            cbInstalacao.getModel().setSelectedItem(condutor.getModoInstalacao().name());
            cbMaterial.getModel().setSelectedItem(condutor.getMaterial());
            cbIsolacao.getModel().setSelectedItem(condutor.getIsolacao());
            cbEnterrado.getModel().setSelectedItem(condutor.getEnterrado().getNome());
            cbEspacamentoCabos.getModel().setSelectedItem(condutor.getEspacoCabos().getNome());
            cbEspacamentoEletrodutos.getModel().setSelectedItem(condutor.getEspacoEletrodutos().getNome());
            cbMultipolar.getModel().setSelectedItem(condutor.getMultipolar());
            cbAgrupamento.getModel().setSelectedItem(condutor.getFormaAgrupa().getNome());
            cbBitolasSucessivas.getModel().setSelectedItem(condutor.getBitolaSucessiva());
            cbNCircuitosAgrupados.getModel().setSelectedItem(condutor.getnCirAgrupa());
            cbNCamadas.getModel().setSelectedItem(condutor.getnCamadas());
        }
        return condutor;
    }

    private void adicionarKeyListener() {
        this.campoQuedaTensao.addKeyListener(this);
        this.campoResistividade.addKeyListener(this);
        this.campoTemperatura.addKeyListener(this);
        this.campoComprimento.addKeyListener(this);
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoComprimento;
    private static javax.swing.JTextField campoQuedaTensao;
    private static javax.swing.JTextField campoResistividade;
    private javax.swing.JTextField campoTemperatura;
    private static javax.swing.JComboBox<String> cbAgrupamento;
    private static javax.swing.JComboBox<String> cbBitolasSucessivas;
    private static javax.swing.JComboBox<String> cbEnterrado;
    private static javax.swing.JComboBox<String> cbEspacamentoCabos;
    private static javax.swing.JComboBox<String> cbEspacamentoEletrodutos;
    private static javax.swing.JComboBox<String> cbInstalacao;
    private static javax.swing.JComboBox<String> cbIsolacao;
    private static javax.swing.JComboBox<String> cbMaterial;
    private static javax.swing.JComboBox<String> cbMultipolar;
    private static javax.swing.JComboBox<String> cbNCamadas;
    private static javax.swing.JComboBox<String> cbNCircuitosAgrupados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JScrollPane scrollEsquerdo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        ApenasNumero.campo(e, "campoQuedaTensao");
        ApenasNumero.campo(e, "campoResistividade");
        ApenasNumero.campo(e, "campoTemperatura");
        ApenasNumero.campo(e, "campoComprimento");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
