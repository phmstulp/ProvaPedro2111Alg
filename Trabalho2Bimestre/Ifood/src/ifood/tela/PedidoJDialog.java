/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.tela;

import ifood.DAO.ItemPedidoDAO;
import ifood.DAO.ClienteDAO;
import ifood.DAO.EmpresaDAO;
import ifood.DAO.ProdutoDAO;
import ifood.DAO.PedidoDAO;
import ifood.model.Cliente;
import ifood.model.Empresa;
import ifood.model.ItemPedido;
import ifood.model.Pedido;
import ifood.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro Henrique Martins Stulp
 */
public class PedidoJDialog extends javax.swing.JDialog {

    ClienteDAO clienteDao;
    EmpresaDAO empresaDao;
    ItemPedidoDAO itemPedidoDao;
    PedidoDAO pedidoDao;
    ProdutoDAO produtoDao;
    private List<ItemPedido> itemPedidoList = new ArrayList<>();

    /**
     * Creates new form PedidoJDialog
     */
    public PedidoJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        clienteDao = new ClienteDAO();
        empresaDao = new EmpresaDAO();
        itemPedidoDao = new ItemPedidoDAO();
        pedidoDao = new PedidoDAO();
        produtoDao = new ProdutoDAO();
        setTfCodigo();
        cbCliente.setEnabled(true);
        cbEmpresa.setEnabled(true);
        try {
            carregaTable(pedidoDao.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        btSalvar.setEnabled(false);
        btAddProduto.setEnabled(false);
        tfCodigo.setEnabled(false);
        tfVlTotal.setEnabled(false);
        desabilitaCampos(false);
        carregaComboCliente();
        carregaComboEmpresa();
        carregaComboProduto();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCadastro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        btNovo = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        cbEmpresa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox<>();
        btAddProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfVlTotal = new javax.swing.JTextField();
        chPago = new javax.swing.JCheckBox();
        tfQuantidade = new javax.swing.JFormattedTextField();
        pnTabela = new javax.swing.JScrollPane();
        tbPedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Pedidos");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        pnCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12))); // NOI18N
        pnCadastro.setToolTipText("");
        pnCadastro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnCadastroMouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setText("Código");

        btNovo.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btSalvar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btRemover.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btRemover.setText("Remover");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel4.setText("Cliente");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel5.setText("Empresa");

        cbCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbEmpresa.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cbEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setText("Produto");

        cbProduto.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btAddProduto.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btAddProduto.setText("Adicionar Produto");
        btAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddProdutoActionPerformed(evt);
            }
        });

        tbProdutos.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Valor Unitário", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProdutos.setEnabled(false);
        jScrollPane1.setViewportView(tbProdutos);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel3.setText("Valor Total");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel6.setText("Quantidade");

        chPago.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        chPago.setText("Pago");

        tfQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout pnCadastroLayout = new javax.swing.GroupLayout(pnCadastro);
        pnCadastro.setLayout(pnCadastroLayout);
        pnCadastroLayout.setHorizontalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfVlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfQuantidade))
                        .addGroup(pnCadastroLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(chPago, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnCadastroLayout.setVerticalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chPago))
                .addGap(18, 18, 18)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(tfQuantidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddProduto)
                    .addComponent(jLabel3)
                    .addComponent(tfVlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo)
                    .addComponent(btSalvar)
                    .addComponent(btRemover)))
        );

        tbPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Empresa", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPedidoMouseClicked(evt);
            }
        });
        pnTabela.setViewportView(tbPedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        btSalvar.setText("Salvar");
        cbCliente.setEnabled(true);
        cbEmpresa.setEnabled(true);
        limpaCampos();
        novo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (validaCampos() == true) {
            salvar();
            cbCliente.setEnabled(true);
            cbEmpresa.setEnabled(true);
            itemPedidoList.clear();
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        remover();
        limpaCampos();
        desabilitaCampos(false);
    }//GEN-LAST:event_btRemoverActionPerformed

    private void pnCadastroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnCadastroMouseMoved
        if (tfCodigo.getText().trim().length() > 0
                && cbCliente.getSelectedItem().toString().trim().length() > 0
                && cbEmpresa.getSelectedItem().toString().trim().length() > 0
                && tfVlTotal.getText().trim().length() > 0) {
            btSalvar.setEnabled(true);
        } else {
            btSalvar.setEnabled(false);
        }
        if (tbPedido.getSelectedRow() != -1) {
            btRemover.setEnabled(true);
        } else {
            btRemover.setEnabled(false);
        }
    }//GEN-LAST:event_pnCadastroMouseMoved

    private void tbPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidoMouseClicked
        Pedido pedido = new Pedido();
        cbCliente.setEnabled(false);
        cbEmpresa.setEnabled(false);
        try {
            pedido = pedidoDao.getById((int) tbPedido.getValueAt(tbPedido.getSelectedRow(), 0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        novo();
        btSalvar.setText("Atualizar");
        tfCodigo.setText(String.valueOf(pedido.getCodigo()));
        tfVlTotal.setText(String.valueOf(pedido.getVlTotal()));
        chPago.setSelected(pedido.isPago());
        cbCliente.setSelectedItem(pedido.getCliente());
        cbEmpresa.setSelectedItem(pedido.getEmpresa());
        carregaTableItempedido(pedido.getItemPedidoList());
        itemPedidoList.addAll(pedido.getItemPedidoList());
        btSalvar.setEnabled(true);
        btRemover.setEnabled(true);
    }//GEN-LAST:event_tbPedidoMouseClicked

    private void btAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddProdutoActionPerformed
        try {
            cbCliente.setEnabled(false);
            cbEmpresa.setEnabled(false);
            ItemPedido itemPedido = new ItemPedido();
            Produto produto = new Produto();
            produto = (Produto) cbProduto.getSelectedItem();
            //itemPedido.setCodigo(itemPedidoDao.getLastId());
            itemPedido.setCodigoPedido(Integer.parseInt(tfCodigo.getText().trim()));
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(Integer.parseInt(tfQuantidade.getText().trim()));
            itemPedido.setVlTotal(produto.getVlUnitario() * Integer.parseInt(tfQuantidade.getText().trim()));
            itemPedidoList.add(itemPedido);
            tfVlTotal.setText(String.valueOf(calculaTotalPedido(itemPedidoList)));
            //} catch (SQLException ex) {
            //Logger.getLogger(PedidoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Caracter Inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        carregaTableItempedido(itemPedidoList);
    }//GEN-LAST:event_btAddProdutoActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (tfCodigo.getText().trim().length() > 0
                && cbCliente.getSelectedItem().toString().trim().length() > 0
                && cbEmpresa.getSelectedItem().toString().trim().length() > 0
                && tfVlTotal.getText().trim().length() > 0) {
            btSalvar.setEnabled(true);
        } else {
            btSalvar.setEnabled(false);
        }
        if (tbPedido.getSelectedRow() != -1) {
            btRemover.setEnabled(true);
        } else {
            btRemover.setEnabled(false);
        }
    }//GEN-LAST:event_formMouseMoved

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
            java.util.logging.Logger.getLogger(PedidoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoJDialog dialog = new PedidoJDialog(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddProduto;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbEmpresa;
    private javax.swing.JComboBox<String> cbProduto;
    private javax.swing.JCheckBox chPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCadastro;
    private javax.swing.JScrollPane pnTabela;
    private javax.swing.JTable tbPedido;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JFormattedTextField tfQuantidade;
    private javax.swing.JTextField tfVlTotal;
    // End of variables declaration//GEN-END:variables

    private void carregaTable(List<Pedido> pedidoList) {
        if (pedidoList == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbPedido.getModel();
        model.setRowCount(0);
        for (Pedido p : pedidoList) {
            model.addRow(new Object[]{p.getCodigo(), p.getCliente().toString(),
                p.getEmpresa().toString(), p.getVlTotal()});
        }
    }

    private void setTfCodigo() {
        try {
            tfCodigo.setText(String.valueOf(pedidoDao.getLastId()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void desabilitaCampos(boolean ativo) {
        tfCodigo.setEnabled(ativo);
        tfQuantidade.setEnabled(ativo);
        tfVlTotal.setEnabled(ativo);
    }

    private boolean validaCampos() {
        if (tfCodigo.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe um código para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (tfQuantidade.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe uma quantidade para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (tfVlTotal.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe um valor unitario para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (Integer.parseInt(tfQuantidade.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(null, "Informe uma quantidade maior que zero para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void salvar() {
        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(tfCodigo.getText().trim()));
            pedido.setCliente((Cliente) cbCliente.getSelectedItem());
            pedido.setEmpresa((Empresa) cbEmpresa.getSelectedItem());
            pedido.setPago(chPago.isSelected());
            pedido.setItemPedidoList(itemPedidoList);
            carregaTableItempedido(pedido.getItemPedidoList());
            pedido.setVlTotal(Double.parseDouble(tfVlTotal.getText().trim().replace(",", ".")));
            try {
                if (pedidoDao.getLastId() == Integer.parseInt(tfCodigo.getText())) {
                    pedidoDao.save(pedido);
                    for (ItemPedido i : pedido.getItemPedidoList()) {
                        i.setCodigo(itemPedidoDao.getLastId());
                        itemPedidoDao.save(i);
                    }
                } else {
                    pedidoDao.update(pedido);
                    btSalvar.setText("Salvar");
                    itemPedidoDao.delete(pedido.getCodigo());
                    for (ItemPedido ip : itemPedidoList) {
                        ip.setCodigo(itemPedidoDao.getLastId());
                        itemPedidoDao.save(ip);
                    }
                }
                JOptionPane.showMessageDialog(null, "Pedido Salvo com Sucesso");

                itemPedidoList = new ArrayList<>();
                limpaCampos();
                desabilitaCampos(false);
                carregaTableItempedido(itemPedidoList);
                carregaTable(pedidoDao.getAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Caracter Inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpaCampos() {
        tfCodigo.setText("");
        tfQuantidade.setText("");
        tfVlTotal.setText("");
    }

    private void remover() {
        int linhaSelecionada = tbPedido.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Deve-se selecionar uma linha para ser removido", "Atenção", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int codigoRemover = (int) tbPedido.getValueAt(linhaSelecionada, 0);
        try {
            pedidoDao.delete(codigoRemover);
            JOptionPane.showMessageDialog(null, "Pedido Removido com Sucesso!");
            carregaTable(pedidoDao.getAll());
            itemPedidoList.clear();
            carregaTableItempedido(itemPedidoList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void novo() {
        try {
            itemPedidoList.clear();
            carregaTableItempedido(itemPedidoList);
            tfCodigo.setText(String.valueOf(pedidoDao.getLastId()));
            tfVlTotal.setText("0.00");
            tfCodigo.setEditable(false);
            tfVlTotal.setEditable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        desabilitaCampos(true);
        btSalvar.setEnabled(true);
        btAddProduto.setEnabled(true);
    }

    private void carregaTableItempedido(List<ItemPedido> itemPedidoList) {
        if (itemPedidoList == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbProdutos.getModel();
        model.setRowCount(0);
        for (ItemPedido ip : itemPedidoList) {
            model.addRow(new Object[]{ip.getCodigo(), ip.getProduto().getDescricao(),
                ip.getProduto().getVlUnitario(), ip.getQuantidade()});
        }
    }

    private double calculaTotalPedido(List<ItemPedido> listaItemPedido) {
        double total = 0;
        for (ItemPedido ip : listaItemPedido) {
            total += ip.getVlTotal();
        }
        return total;
    }

    private void carregaComboCliente() {
        try {
            DefaultComboBoxModel modeloComboCliente;
            modeloComboCliente = new DefaultComboBoxModel(clienteDao.getAll().toArray());
            cbCliente.setModel(modeloComboCliente);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregaComboEmpresa() {
        try {
            DefaultComboBoxModel modeloComboEmpresa;
            modeloComboEmpresa = new DefaultComboBoxModel(empresaDao.getAll().toArray());
            cbEmpresa.setModel(modeloComboEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregaComboProduto() {
        try {
            DefaultComboBoxModel modeloComboProduto;
            modeloComboProduto = new DefaultComboBoxModel(produtoDao.getAll().toArray());
            cbProduto.setModel(modeloComboProduto);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
