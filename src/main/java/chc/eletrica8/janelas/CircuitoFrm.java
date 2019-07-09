/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.janelas;

import chc.eletrica8.controle.DesktopPane;
import chc.eletrica8.controle.Ids;
import chc.eletrica8.entidades.Carga;
import chc.eletrica8.entidades.Circuito;
import chc.eletrica8.servico.CargaService;
import chc.eletrica8.servico.CircuitoService;
import chc.eletrica8.servico.QuadroService;
import chc.eletrica8.servico.tableModel.GenericTableModel;
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

    private GenericTableModel tabelaModeloCircuito;
    private GenericTableModel tabelaModeloCarga;
    private CondutorFrm condutorFrm;
    private CurtoCircuitoFrm curtoFrm;

    /**
     * Creates new form ProjetoFrm
     */
    public CircuitoFrm() {
        initComponents();
        this.iniciaTabelaCircuitos();
        eventoSelecaoTabelaCircuito();
        eventoSelecaoTabelaCarga();
        Ids.setIdCircuito(0);
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
        painelDireito = new javax.swing.JPanel();
        scrollDireito = new javax.swing.JScrollPane();
        tabelaCircuito = new javax.swing.JTable();
        painelDireito1 = new javax.swing.JPanel();
        scrollDireito1 = new javax.swing.JScrollPane();
        tabelaEquipamentos = new javax.swing.JTable();
        btnNovoCarga = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        btnCopiarCarga = new javax.swing.JButton();
        btnRelatoriosCarga = new javax.swing.JButton();

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

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 180, Short.MAX_VALUE))
                    .addComponent(campoNome))
                .addContainerGap())
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        scrollEsquerdo.setViewportView(painelEsquerdo);

        painelDireito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        scrollDireito.setBorder(null);

        tabelaCircuito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollDireito.setViewportView(tabelaCircuito);

        javax.swing.GroupLayout painelDireitoLayout = new javax.swing.GroupLayout(painelDireito);
        painelDireito.setLayout(painelDireitoLayout);
        painelDireitoLayout.setHorizontalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        painelDireitoLayout.setVerticalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        painelDireito1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carga", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        scrollDireito1.setBorder(null);

        tabelaEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaEquipamentos.setName("tabelaEquipamentos"); // NOI18N
        tabelaEquipamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEquipamentosMouseClicked(evt);
            }
        });
        scrollDireito1.setViewportView(tabelaEquipamentos);

        javax.swing.GroupLayout painelDireito1Layout = new javax.swing.GroupLayout(painelDireito1);
        painelDireito1.setLayout(painelDireito1Layout);
        painelDireito1Layout.setHorizontalGroup(
            painelDireito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        painelDireito1Layout.setVerticalGroup(
            painelDireito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDireito1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        btnNovoCarga.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnNovoCarga.setText("Novo");
        btnNovoCarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNovoCarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovoCarga.setIconTextGap(2);
        btnNovoCarga.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnNovoCarga.setMaximumSize(new java.awt.Dimension(71, 32));
        btnNovoCarga.setMinimumSize(new java.awt.Dimension(71, 32));
        btnNovoCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCargaActionPerformed(evt);
            }
        });

        btnExcluir1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExcluir1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluir1.setIconTextGap(2);
        btnExcluir1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCargaActionPerformed(evt);
            }
        });

        btnCopiarCarga.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnCopiarCarga.setText("Copiar");
        btnCopiarCarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCopiarCarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCopiarCarga.setIconTextGap(2);
        btnCopiarCarga.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCopiarCarga.setMaximumSize(new java.awt.Dimension(71, 32));
        btnCopiarCarga.setMinimumSize(new java.awt.Dimension(71, 32));
        btnCopiarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarCargaActionPerformed(evt);
            }
        });

        btnRelatoriosCarga.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnRelatoriosCarga.setText("Relatórios");
        btnRelatoriosCarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRelatoriosCarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRelatoriosCarga.setIconTextGap(2);
        btnRelatoriosCarga.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnRelatoriosCarga.setMaximumSize(new java.awt.Dimension(71, 32));
        btnRelatoriosCarga.setMinimumSize(new java.awt.Dimension(71, 32));
        btnRelatoriosCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosCargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(painelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(painelDireito1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNovoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCopiarCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRelatoriosCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir1)
                    .addComponent(btnCopiarCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelatoriosCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDireito1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 571, 458);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCircuitoActionPerformed

        Circuito circuito = this.getDados();
        condutorFrm = new CondutorFrm(null, true);
        curtoFrm = new CurtoCircuitoFrm(null, true);
        //DesktopPane.desktop.add(condutorFrm);
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
        } catch (Exception e) {
        }

        CircuitoService.salva(circuito);

        this.apagaDadosFrm();
        this.iniciaTabelaCircuitos();
        Ids.setIdCircuito(0);
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnSalvarCircuitoActionPerformed

    private void btnExcluirCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCircuitoActionPerformed
        CircuitoService.removeById(Ids.getIdCircuito());
        this.iniciaTabelaCircuitos();
        this.iniciaTabelaCarga();
        this.apagaDadosFrm();
        Ids.setIdCircuito(0);
        Ids.setIdCarga(0);
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnExcluirCircuitoActionPerformed

    private void btnCopiarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarCircuitoActionPerformed

        Circuito cir = CircuitoService.getById(Ids.getIdCircuito()).clonarSemID();
        
        for(int i = 0;i<cir.getListaCarga().size();i++){
            cir.getListaCarga().get(i).setCircuito(cir);
        }
        

        CircuitoService.salva(cir);

        this.iniciaTabelaCircuitos();
        this.apagaDadosFrm();
        Ids.setIdCircuito(0);
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnCopiarCircuitoActionPerformed

    private void eventoSelecaoTabelaCircuito() {
        tabelaCircuito.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                int linha = tabelaCircuito.getSelectedRow();
                if (evt.getValueIsAdjusting() == true && linha > -1) {
                    Circuito circuito = (Circuito) tabelaModeloCircuito.loadItem(linha);
                    setDados(circuito);
                    Ids.setIdCircuito(circuito.getId());
                    try {
                        Ids.setIdCondutor(circuito.getCondutor().getId());
                        Ids.setIdCurto(circuito.getCurto().getId());
                    } catch (Exception e) {
                    }
                    iniciaTabelaCarga();
                }
            }
        }
        );
        Ids.imprimiIds();
    }

    private void eventoSelecaoTabelaCarga() {
        tabelaEquipamentos.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                int linha = tabelaEquipamentos.getSelectedRow();
                if (evt.getValueIsAdjusting() == true && linha > -1) {
                    Carga carga = (Carga) tabelaModeloCarga.loadItem(linha);
                    Ids.setIdCarga(carga.getId());

                    // iniciaTabelaCarga();
                }
            }
        }
        );
        Ids.imprimiIds();
    }

    private void btnNovoCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCircuitoActionPerformed
        this.apagaDadosFrm();
        this.iniciaTabelaCircuitos();
        Ids.setIdCircuito(0);
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnNovoCircuitoActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        QuadroFrm frm = new QuadroFrm();
        DesktopPane.desktop.add(frm);
        frm.setVisible(true);
        Ids.setIdCircuito(0);
        Ids.setIdCarga(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnRelatoriosCircutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosCircutoActionPerformed
        RelatorioCircuitoFrm frm = new RelatorioCircuitoFrm();
        DesktopPane.desktop.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_btnRelatoriosCircutoActionPerformed

    private void btnNovoCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCargaActionPerformed
        this.setVisible(false);
        CargaFrm equipamento = new CargaFrm();
        DesktopPane.desktop.add(equipamento);
        equipamento.setVisible(true);
        Ids.setIdCarga(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnNovoCargaActionPerformed

    private void btnExcluirCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCargaActionPerformed
        Ids.imprimiIds();
        CargaService.removeById(Ids.getIdCarga());
        this.iniciaTabelaCarga();
        this.apagaDadosFrm();
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);

    }//GEN-LAST:event_btnExcluirCargaActionPerformed

    private void btnCopiarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarCargaActionPerformed
        Carga q = CargaService.getById(Ids.getIdCarga()).clonarSemID();
        CargaService.salva(q);

        this.iniciaTabelaCarga();
        this.apagaDadosFrm();
        Ids.setIdCondutor(0);
        Ids.setIdCurto(0);
        Ids.imprimiIds();
    }//GEN-LAST:event_btnCopiarCargaActionPerformed

    private void btnRelatoriosCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosCargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRelatoriosCargaActionPerformed

    private void tabelaEquipamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEquipamentosMouseClicked
        if (evt.getClickCount() == 1) {

        }
        if (evt.getClickCount() == 2) {
            if (Ids.getIdCarga() > 0) {
                this.setVisible(false);
                int linha = tabelaEquipamentos.getSelectedRow();
                CargaFrm equipamento = new CargaFrm((Carga) tabelaModeloCarga.loadItem(linha));
                equipamento.getLblAtualizacao().setText("Atualização");
                DesktopPane.desktop.add(equipamento);
                equipamento.setVisible(true);
                Ids.setIdCondutor(0);
                Ids.setIdCurto(0);
                Ids.imprimiIds();
            }
        }
    }//GEN-LAST:event_tabelaEquipamentosMouseClicked

    public final void iniciaTabelaCircuitos() {
        List<Circuito> lista = new ArrayList<>();
        lista = CircuitoService.getByExpres("from Circuito where quadro = :quadro", new Object[]{"quadro", QuadroService.getById(Ids.getIdQuadro())});

        if (!lista.isEmpty()) {
            tabelaModeloCircuito = new GenericTableModel(lista, Circuito.class);
            tabelaCircuito.setModel(tabelaModeloCircuito);
        } else {
            DefaultTableModel model = new DefaultTableModel();
            this.tabelaCircuito.setModel(model);
        }
        Ids.imprimiIds();
    }

    public final void iniciaTabelaCarga() {
        List<Carga> lista = new ArrayList<>();
        lista = CargaService.getByExpres("from Carga where circuito = :circuito", new Object[]{"circuito", CircuitoService.getById(Ids.getIdCircuito())});

        if (!lista.isEmpty()) {
            tabelaModeloCarga = new GenericTableModel(lista, Carga.class);
            tabelaEquipamentos.setModel(tabelaModeloCarga);
        } else {
            DefaultTableModel model = new DefaultTableModel();
            this.tabelaEquipamentos.setModel(model);
        }
        Ids.imprimiIds();
    }

    private Circuito getDados() {
        Circuito circuito;
        if (Ids.getIdCircuito() > 0) {
            circuito = CircuitoService.getById(TrataID.IntegerToInteger(Ids.getIdCircuito()));
        } else {
            circuito = new Circuito();
            circuito.setQuadro(QuadroService.getById(TrataID.IntegerToInteger(Ids.getIdQuadro())));
        }
        //circuito.setId(TrataID.IntegerToInteger(Ids.getIdCircuito()));
        circuito.setNome(this.campoNome.getText());
        circuito.setQuadro(QuadroService.getById(Ids.getIdQuadro()));
        Ids.imprimiIds();
        return circuito;

    }

    public void apagaDadosFrm() {
        this.campoNome.setText("");
        //Ids.setIdCircuito(0);
        Ids.imprimiIds();
    }

    public void setDados(Circuito circuito) {
        if (circuito != null) {
            Ids.setIdCircuito(circuito.getId());
            this.campoNome.setText(circuito.getNome());
            Ids.imprimiIds();
            //circuito.getCorrenteIB();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopiarCarga;
    private javax.swing.JButton btnCopiarCircuito;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnExcluirCircuito;
    private javax.swing.JButton btnNovoCarga;
    private javax.swing.JButton btnNovoCircuito;
    private javax.swing.JButton btnRelatoriosCarga;
    private javax.swing.JButton btnRelatoriosCircuto;
    private javax.swing.JButton btnSalvarCircuito;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelDireito;
    private javax.swing.JPanel painelDireito1;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JScrollPane scrollDireito;
    private javax.swing.JScrollPane scrollDireito1;
    private javax.swing.JScrollPane scrollEsquerdo;
    private javax.swing.JTable tabelaCircuito;
    private javax.swing.JTable tabelaEquipamentos;
    // End of variables declaration//GEN-END:variables

}
