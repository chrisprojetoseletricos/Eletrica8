/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.janelas;

import chc.eletrica8.calculos.CalculaDados;
import chc.eletrica8.controle.DesktopPane;
import chc.eletrica8.controle.Ids;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.entidades.Quadro;
import chc.eletrica8.enums.Ligacao;
import chc.eletrica8.enums.Usabilidade;
import chc.eletrica8.servico.CircuitoService;
import chc.eletrica8.servico.QuadroService;
import chc.eletrica8.servico.tableModel.CircuitoTableModel;
import chc.eletrica8.uteis.TrataID;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chris
 */
public class CircuitoFrm extends javax.swing.JInternalFrame {

    //private GenericTableModel tabelaModeloCircuito;
    private CircuitoTableModel tabelaModeloCircuito;
    private CondutorFrm condutorFrm;
    private CurtoCircuitoFrm curtoFrm;

    /**
     * Creates new form ProjetoFrm
     */
    public CircuitoFrm() {
        initComponents();
        this.iniciaTabelaCircuitos();
        eventoSelecaoTabelaCircuito();
        this.cbUsabilidadeItens();
        this.cbLigacaoItens();
        Ids.setIdCircuito(0);
        Ids.setIdCarga(0);
        Ids.imprimiIds();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoes = new javax.swing.JPanel();
        btnSalvarCircuito = new javax.swing.JButton();
        btnExcluirCircuito = new javax.swing.JButton();
        btnCopiarCircuito = new javax.swing.JButton();
        btnNovoCircuito = new javax.swing.JButton();
        btnRelatoriosCircuto = new javax.swing.JButton();
        scrollEsquerdo = new javax.swing.JScrollPane();
        painelEsquerdo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbUsabilidade = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbLigacao = new javax.swing.JComboBox<>();
        painelDireito = new javax.swing.JPanel();
        scrollDireito = new javax.swing.JScrollPane();
        tabelaCircuito = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Circuito");
        setAutoscrolls(true);
        setName("ProjetoFrm"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        painelBotoes.setAutoscrolls(true);

        btnSalvarCircuito.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnSalvarCircuito.setText("Salvar");
        btnSalvarCircuito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalvarCircuito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvarCircuito.setIconTextGap(2);
        btnSalvarCircuito.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnSalvarCircuito.setMaximumSize(new java.awt.Dimension(71, 32));
        btnSalvarCircuito.setMinimumSize(new java.awt.Dimension(71, 32));
        btnSalvarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCircuitoActionPerformed(evt);
            }
        });

        btnExcluirCircuito.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnExcluirCircuito.setText("Excluir");
        btnExcluirCircuito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExcluirCircuito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluirCircuito.setIconTextGap(2);
        btnExcluirCircuito.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnExcluirCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCircuitoActionPerformed(evt);
            }
        });

        btnCopiarCircuito.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnCopiarCircuito.setText("Copiar");
        btnCopiarCircuito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCopiarCircuito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCopiarCircuito.setIconTextGap(2);
        btnCopiarCircuito.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCopiarCircuito.setMaximumSize(new java.awt.Dimension(71, 32));
        btnCopiarCircuito.setMinimumSize(new java.awt.Dimension(71, 32));
        btnCopiarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarCircuitoActionPerformed(evt);
            }
        });

        btnNovoCircuito.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnNovoCircuito.setText("Novo");
        btnNovoCircuito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNovoCircuito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovoCircuito.setIconTextGap(2);
        btnNovoCircuito.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnNovoCircuito.setMaximumSize(new java.awt.Dimension(71, 32));
        btnNovoCircuito.setMinimumSize(new java.awt.Dimension(71, 32));
        btnNovoCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCircuitoActionPerformed(evt);
            }
        });

        btnRelatoriosCircuto.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnRelatoriosCircuto.setText("Relatórios");
        btnRelatoriosCircuto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRelatoriosCircuto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRelatoriosCircuto.setIconTextGap(2);
        btnRelatoriosCircuto.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnRelatoriosCircuto.setMaximumSize(new java.awt.Dimension(71, 32));
        btnRelatoriosCircuto.setMinimumSize(new java.awt.Dimension(71, 32));
        btnRelatoriosCircuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosCircutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovoCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirCircuito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCopiarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRelatoriosCircuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirCircuito)
                    .addComponent(btnCopiarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelatoriosCircuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        scrollEsquerdo.setBorder(null);
        scrollEsquerdo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEsquerdo.setMinimumSize(new java.awt.Dimension(15, 5));
        scrollEsquerdo.setPreferredSize(new java.awt.Dimension(100, 100));

        painelEsquerdo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        painelEsquerdo.setAutoscrolls(true);
        painelEsquerdo.setPreferredSize(new java.awt.Dimension(50, 100));

        jLabel1.setText("Nome:");

        jLabel2.setText("Ligação:");

        jLabel3.setText("Usabilidade:");

        cbLigacao.setName("cbLigacao"); // NOI18N

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbUsabilidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbLigacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLigacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbUsabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        scrollEsquerdo.setViewportView(painelEsquerdo);

        painelDireito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CIR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        scrollDireito.setBorder(null);

        tabelaCircuito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaCircuito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCircuitoMouseClicked(evt);
            }
        });
        scrollDireito.setViewportView(tabelaCircuito);

        javax.swing.GroupLayout painelDireitoLayout = new javax.swing.GroupLayout(painelDireito);
        painelDireito.setLayout(painelDireitoLayout);
        painelDireitoLayout.setHorizontalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        painelDireitoLayout.setVerticalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 921, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(scrollEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1233, 553);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCircuitoActionPerformed

        Circuito circuito = this.getDados();
        condutorFrm = new CondutorFrm(null, true);
        curtoFrm = new CurtoCircuitoFrm(null, true);
        try {
            condutorFrm.setDados(CircuitoService.getById(Ids.getIdCircuito()).getCondutor());
            curtoFrm.setDados(CircuitoService.getById(Ids.getIdCircuito()).getCurto());
        } catch (Exception e) {

        }
        condutorFrm.setVisible(true);
        curtoFrm.setVisible(true);
        try {
            circuito.setCondutor(condutorFrm.getCondutor());
            circuito.setCurto(curtoFrm.getCurto());
            circuito.setResultados(CircuitoService.getById(Ids.getIdCircuito()).getResultados());
        } catch (Exception e) {
        }

       CalculaDados.calculaCircuito(circuito);

        this.apagaDadosFrm();
        this.iniciaTabelaCircuitos();
        Ids.setIdCircuito(0);
        
    }//GEN-LAST:event_btnSalvarCircuitoActionPerformed

    private void btnExcluirCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCircuitoActionPerformed
        CircuitoService.removeById(Ids.getIdCircuito());
        //CalculaDados.quadro(QuadroService.getById(Ids.getIdQuadro()));
        this.iniciaTabelaCircuitos();
        this.apagaDadosFrm();
        Ids.setIdCircuito(0);
        Ids.setIdCarga(0);

    }//GEN-LAST:event_btnExcluirCircuitoActionPerformed

    private void btnCopiarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarCircuitoActionPerformed

        Circuito cir = CircuitoService.getById(Ids.getIdCircuito()).clonarSemID();
        CalculaDados.calculaCircuito(cir);

        this.iniciaTabelaCircuitos();
        this.apagaDadosFrm();
        Ids.setIdCircuito(0);

    }//GEN-LAST:event_btnCopiarCircuitoActionPerformed

    private void eventoSelecaoTabelaCircuito() {
        tabelaCircuito.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                int linha = tabelaCircuito.getSelectedRow();
                if (evt.getValueIsAdjusting() == true && linha > -1) {
                    Circuito circuito = (Circuito) tabelaModeloCircuito.getCircuito(linha);
                    setDados(circuito);
                    Ids.setIdCircuito(circuito.getId());

                }
            }
        }
        );
    }


    private void btnNovoCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCircuitoActionPerformed
        this.apagaDadosFrm();
        this.iniciaTabelaCircuitos();
        Ids.setIdCircuito(0);


    }//GEN-LAST:event_btnNovoCircuitoActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        QuadroFrm frm = new QuadroFrm();
       
        int num = Ids.getIdQuadro();
        Quadro quadro = QuadroService.getById(num);
        CalculaDados.calculaQuadro(quadro);
        DesktopPane.desktop.add(frm);
        frm.setVisible(true);
        Ids.setIdCircuito(0);
        Ids.setIdCarga(0);

    }//GEN-LAST:event_formInternalFrameClosing

    private void btnRelatoriosCircutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosCircutoActionPerformed
        RelatorioCircuitoFrm frm = new RelatorioCircuitoFrm();
        DesktopPane.desktop.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_btnRelatoriosCircutoActionPerformed

    private void tabelaCircuitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCircuitoMouseClicked
        if (evt.getClickCount() == 1) {

        }
        if (evt.getClickCount() == 2) {
            if (Ids.getIdCircuito() > 0) {
                this.dispose();
                CargaFrm frm = new CargaFrm();
                DesktopPane.desktop.add(frm);
                frm.setVisible(true);

            }
        }
    }//GEN-LAST:event_tabelaCircuitoMouseClicked

    public final void iniciaTabelaCircuitos() {
        List<Circuito> lista = new ArrayList<>();
        lista = CircuitoService.getByExpres("from Circuito where quadro = :quadro", new Object[]{"quadro", QuadroService.getById(Ids.getIdQuadro())});
        if (!lista.isEmpty()) {
            tabelaModeloCircuito = new CircuitoTableModel(lista);
            tabelaCircuito.setModel(tabelaModeloCircuito);
        } else {
            DefaultTableModel model = new DefaultTableModel();
            this.tabelaCircuito.setModel(model);
        }

    }

    private void cbUsabilidadeItens() {
        cbUsabilidade.removeAllItems();
        cbUsabilidade.addItem(null);
        for (Usabilidade usa : Usabilidade.getLista()) {
            cbUsabilidade.addItem(usa);

        }
    }

    private void cbLigacaoItens() {
        cbLigacao.removeAllItems();
        cbLigacao.addItem(null);
        for (Ligacao usa : Ligacao.getLista()) {
            cbLigacao.addItem(usa);
        }
        cbLigacao.setSelectedIndex(1);
    }

    private Circuito getDados() {
        Circuito circuito;
        if (Ids.getIdCircuito() > 0) {
            circuito = CircuitoService.getById(TrataID.IntegerToInteger(Ids.getIdCircuito()));
        } else {
            circuito = new Circuito();
            circuito.setQuadro(QuadroService.getById(TrataID.IntegerToInteger(Ids.getIdQuadro())));
        }
        circuito.setNome(this.campoNome.getText());
        circuito.setUsabilidade((Usabilidade) cbUsabilidade.getModel().getSelectedItem());
        circuito.getResultados().setLigacao((Ligacao) cbLigacao.getModel().getSelectedItem());
        circuito.setQuadro(QuadroService.getById(Ids.getIdQuadro()));

        return circuito;

    }

    public void apagaDadosFrm() {
        this.campoNome.setText("");
        this.cbUsabilidade.setSelectedIndex(-1);
        this.cbLigacao.setSelectedIndex(-1);
    }

    public void setDados(Circuito circuito) {
        if (circuito != null) {
            Ids.setIdCircuito(circuito.getId());
            this.campoNome.setText(circuito.getNome());
            this.cbLigacao.getModel().setSelectedItem(circuito.getResultados().getLigacao());
            this.cbUsabilidade.getModel().setSelectedItem(circuito.getUsabilidade());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopiarCircuito;
    private javax.swing.JButton btnExcluirCircuito;
    private javax.swing.JButton btnNovoCircuito;
    private javax.swing.JButton btnRelatoriosCircuto;
    private javax.swing.JButton btnSalvarCircuito;
    private javax.swing.JTextField campoNome;
    private javax.swing.JComboBox<Ligacao> cbLigacao;
    private javax.swing.JComboBox<Usabilidade> cbUsabilidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelDireito;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JScrollPane scrollDireito;
    private javax.swing.JScrollPane scrollEsquerdo;
    private javax.swing.JTable tabelaCircuito;
    // End of variables declaration//GEN-END:variables

}
