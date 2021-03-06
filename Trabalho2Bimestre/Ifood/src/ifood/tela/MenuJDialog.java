/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.tela;

import ifood.DAO.ClienteDAO;
import ifood.DAO.EmpresaDAO;
import ifood.DAO.PedidoDAO;
import ifood.DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Henrique Martins Stulp
 */
public class MenuJDialog extends javax.swing.JDialog {

    ClienteDAO clienteDao;
    EmpresaDAO empresaDao;
    PedidoDAO pedidoDao;
    ProdutoDAO produtoDao;

    /**
     * Creates new form MenuJDialog
     */
    public MenuJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        clienteDao = new ClienteDAO();
        empresaDao = new EmpresaDAO();
        pedidoDao = new PedidoDAO();
        produtoDao = new ProdutoDAO();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btCadCliente = new javax.swing.JButton();
        btCadEmpresa = new javax.swing.JButton();
        btCadProduto = new javax.swing.JButton();
        btCadPedido = new javax.swing.JButton();
        btRelatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ifood");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("ifood");

        btCadCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        btCadCliente.setText("Cadastrar Cliente");
        btCadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadClienteActionPerformed(evt);
            }
        });

        btCadEmpresa.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        btCadEmpresa.setText("Cadastrar Empresa");
        btCadEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadEmpresaActionPerformed(evt);
            }
        });

        btCadProduto.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        btCadProduto.setText("Cadastrar Produto");
        btCadProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadProdutoActionPerformed(evt);
            }
        });

        btCadPedido.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        btCadPedido.setText("Cadastrar Pedido");
        btCadPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadPedidoActionPerformed(evt);
            }
        });

        btRelatorio.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        btRelatorio.setText("Relatório");
        btRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCadEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(btCadCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btCadProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btCadPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btCadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCadEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCadProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCadPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadClienteActionPerformed
        ClienteJDialog dialog = new ClienteJDialog(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btCadClienteActionPerformed

    private void btCadEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadEmpresaActionPerformed
        EmpresaJDialog dialog = new EmpresaJDialog(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btCadEmpresaActionPerformed

    private void btCadProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadProdutoActionPerformed
        ProdutoJDialog dialog = new ProdutoJDialog(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btCadProdutoActionPerformed

    private void btCadPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadPedidoActionPerformed
        try {
            if (clienteDao.getAll().size() > 0
                    && empresaDao.getAll().size() > 0
                    && produtoDao.getAll().size() > 0) {
                PedidoJDialog dialog = new PedidoJDialog(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Favor cadastrar Cliente, Empresa e Produto "
                        + "antes de cadastrar um Pedido!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCadPedidoActionPerformed

    private void btRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelatorioActionPerformed
        try {
            if (pedidoDao.getAll().size() > 0) {
                RelatorioJDialog dialog = new RelatorioJDialog(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Favor cadastrar Pedidos antes "
                        + "de ver os Relatórios", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRelatorioActionPerformed

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
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuJDialog dialog = new MenuJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btCadCliente;
    private javax.swing.JButton btCadEmpresa;
    private javax.swing.JButton btCadPedido;
    private javax.swing.JButton btCadProduto;
    private javax.swing.JButton btRelatorio;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
