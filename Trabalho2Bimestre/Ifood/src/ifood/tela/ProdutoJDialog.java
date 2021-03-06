/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.tela;

import ifood.DAO.ProdutoDAO;
import ifood.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro Henrique Martins Stulp
 */
public class ProdutoJDialog extends javax.swing.JDialog {

    ProdutoDAO produtoDao;

    /**
     * Creates new form ProdutoJDialog
     */
    public ProdutoJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        produtoDao = new ProdutoDAO();
        setTfCodigo();
        try {
            carregaTable(produtoDao.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        btSalvar.setEnabled(false);
        tfCodigo.setEnabled(false);
        desabilitaCampos(false);
        habilitaFiltroCodigo();
        tfFiltroCodigo.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        tfDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btNovo = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        tfVlUnitario = new javax.swing.JFormattedTextField();
        pnFiltro = new javax.swing.JPanel();
        rbCodigo = new javax.swing.JRadioButton();
        rbDescricao = new javax.swing.JRadioButton();
        tfFiltroCodigo = new javax.swing.JTextField();
        tfFiltroDescricao = new javax.swing.JTextField();
        btFiltrar = new javax.swing.JButton();
        btFiltrarTodos = new javax.swing.JButton();
        pnTabela = new javax.swing.JScrollPane();
        tbProduto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");

        pnCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12))); // NOI18N
        pnCadastro.setToolTipText("");
        pnCadastro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnCadastroMouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setText("Descrição");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel3.setText("Valor Unitário");

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

        tfVlUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout pnCadastroLayout = new javax.swing.GroupLayout(pnCadastro);
        pnCadastro.setLayout(pnCadastroLayout);
        pnCadastroLayout.setHorizontalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfVlUnitario))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        pnCadastroLayout.setVerticalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfVlUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo)
                    .addComponent(btSalvar)
                    .addComponent(btRemover)))
        );

        pnFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12))); // NOI18N

        rbCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        rbCodigo.setText("Código");
        rbCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbCodigoStateChanged(evt);
            }
        });

        rbDescricao.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        rbDescricao.setText("Descrição");
        rbDescricao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbDescricaoStateChanged(evt);
            }
        });

        btFiltrar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btFiltrar.setText("Filtrar");
        btFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFiltrarActionPerformed(evt);
            }
        });

        btFiltrarTodos.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btFiltrarTodos.setText("Mostrar Todos");
        btFiltrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFiltrarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFiltroLayout = new javax.swing.GroupLayout(pnFiltro);
        pnFiltro.setLayout(pnFiltroLayout);
        pnFiltroLayout.setHorizontalGroup(
            pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnFiltroLayout.createSequentialGroup()
                        .addComponent(rbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFiltroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFiltroLayout.createSequentialGroup()
                        .addComponent(rbDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFiltroDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btFiltrarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnFiltroLayout.setVerticalGroup(
            pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCodigo)
                    .addComponent(tfFiltroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFiltrar))
                .addGap(18, 18, 18)
                .addGroup(pnFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDescricao)
                    .addComponent(tfFiltroDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFiltrarTodos))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tbProduto.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        tbProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Valor Unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutoMouseClicked(evt);
            }
        });
        pnTabela.setViewportView(tbProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        btSalvar.setText("Salvar");
        limpaCampos();
        novo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (validaCampos() == true) {
            salvar();
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        remover();
        limpaCampos();
        desabilitaCampos(false);
    }//GEN-LAST:event_btRemoverActionPerformed

    private void pnCadastroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnCadastroMouseMoved
        if (tfVlUnitario.getText().trim().length() > 0
                && tfDescricao.getText().trim().length() > 0) {
            btSalvar.setEnabled(true);
        } else {
            btSalvar.setEnabled(false);
        }
        if (tbProduto.getSelectedRow() != -1) {
            btRemover.setEnabled(true);
        } else {
            btRemover.setEnabled(false);
        }
    }//GEN-LAST:event_pnCadastroMouseMoved

    private void rbCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbCodigoStateChanged
        habilitaFiltroCodigo();
    }//GEN-LAST:event_rbCodigoStateChanged

    private void rbDescricaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbDescricaoStateChanged
        habilitaFiltroNome();
    }//GEN-LAST:event_rbDescricaoStateChanged

    private void btFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFiltrarActionPerformed
        filtrar();
    }//GEN-LAST:event_btFiltrarActionPerformed

    private void btFiltrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFiltrarTodosActionPerformed
        try {
            carregaTable(produtoDao.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btFiltrarTodosActionPerformed

    private void tbProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutoMouseClicked
        Produto produto = new Produto();
        try {
            produto = produtoDao.getById((int) tbProduto.getValueAt(tbProduto.getSelectedRow(), 0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        novo();
        btSalvar.setText("Atualizar");
        tfCodigo.setText(String.valueOf(produto.getCodigo()));
        tfDescricao.setText(produto.getDescricao());
        tfVlUnitario.setText(String.valueOf(produto.getVlUnitario()));
        btSalvar.setEnabled(true);
        btRemover.setEnabled(true);
    }//GEN-LAST:event_tbProdutoMouseClicked

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
            java.util.logging.Logger.getLogger(ProdutoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProdutoJDialog dialog = new ProdutoJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btFiltrar;
    private javax.swing.JButton btFiltrarTodos;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pnCadastro;
    private javax.swing.JPanel pnFiltro;
    private javax.swing.JScrollPane pnTabela;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbDescricao;
    private javax.swing.JTable tbProduto;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfFiltroCodigo;
    private javax.swing.JTextField tfFiltroDescricao;
    private javax.swing.JFormattedTextField tfVlUnitario;
    // End of variables declaration//GEN-END:variables

    private void carregaTable(List<Produto> produtoList) {
        if (produtoList == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbProduto.getModel();
        model.setRowCount(0);
        for (Produto p : produtoList) {
            model.addRow(new Object[]{p.getCodigo(), p.getDescricao(), p.getVlUnitario()});
        }
    }

    private void setTfCodigo() {
        try {
            tfCodigo.setText(String.valueOf(produtoDao.getLastId()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void desabilitaCampos(boolean ativo) {
        tfCodigo.setEnabled(ativo);
        tfDescricao.setEnabled(ativo);
        tfVlUnitario.setEnabled(ativo);
    }

    private void habilitaFiltroCodigo() {
        tfFiltroDescricao.setText("");
        tfFiltroDescricao.setEnabled(false);
        tfFiltroCodigo.setEnabled(true);
    }

    private void habilitaFiltroNome() {
        tfFiltroCodigo.setText("");
        tfFiltroCodigo.setEnabled(false);
        tfFiltroDescricao.setEnabled(true);
    }

    private boolean validaCampos() {
        if (tfCodigo.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe um código para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (tfDescricao.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe uma descricao para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (tfVlUnitario.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Informe um valor unitario para salvar.", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }

    private void salvar() {
        try {
            Produto produto = new Produto();
            produto.setCodigo(Integer.parseInt(tfCodigo.getText().trim()));
            produto.setDescricao(tfDescricao.getText().trim());
            produto.setVlUnitario(Double.parseDouble(tfVlUnitario.getText().trim().replace(",", ".")));
            try {
                if (produtoDao.getLastId() == Integer.parseInt(tfCodigo.getText())) {
                    produtoDao.save(produto);
                } else {
                    produtoDao.update(produto);
                    btSalvar.setText("Salvar");
                }
                JOptionPane.showMessageDialog(null, "Produto Salvo com Sucesso");
                limpaCampos();
                desabilitaCampos(false);
                carregaTable(produtoDao.getAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Caracter Inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpaCampos() {
        tfCodigo.setText("");
        tfDescricao.setText("");
        tfVlUnitario.setText("");
    }

    private void remover() {
        int linhaSelecionada = tbProduto.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Deve-se selecionar uma linha para ser removido", "Atenção", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int codigoRemover = (int) tbProduto.getValueAt(linhaSelecionada, 0);
        try {
            produtoDao.delete(codigoRemover);
            JOptionPane.showMessageDialog(null, "Produto Removido com Sucesso!");
            carregaTable(produtoDao.getAll());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void novo() {
        try {
            tfCodigo.setText(String.valueOf(produtoDao.getLastId()));
            tfCodigo.setEditable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        desabilitaCampos(true);
        btSalvar.setEnabled(true);
    }

    private void filtrar() {
        try {
            if (rbCodigo.isSelected() && tfFiltroCodigo.getText().trim().length() > 0) {
                Produto produto = produtoDao.getById(Integer.parseInt(tfFiltroCodigo.getText()));
                List<Produto> produtoList = new ArrayList<>();
                produtoList.add(produto);
                carregaTable(produtoList);
            } else if (rbDescricao.isSelected() && tfFiltroDescricao.getText().trim().length() > 0) {
                carregaTable(produtoDao.getByName(tfFiltroDescricao.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Informe o filtro desejado para pesquisa", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Algum campo foi informado incorretamente", null, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
