/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.jdbc.ConnectionFactory;
import ifood.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Henrique Martins Stulp
 */
public class ProdutoDAO implements GenericDAO<Produto> {
    
    private Connection connection = null;

    @Override
    public void save(Produto entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PRODUTO (CD_PRODUTO, DESCRICAO, VL_UNITARIO) ")
                    .append("VALUES (?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setString(2, entity.getDescricao());
            pstm.setDouble(3, entity.getVlUnitario());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Produto!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Produto!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(Produto entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PRODUTO SET DESCRICAO = ?, VL_UNITARIO = ? ")
                    .append("WHERE CD_PRODUTO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getDescricao());
            pstm.setDouble(2, entity.getVlUnitario());;
            pstm.setInt(3, entity.getCodigo());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Produto!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Produto!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            String sql = "DELETE FROM PRODUTO WHERE CD_PRODUTO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Produto!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Produto!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public Produto getById(int id) throws SQLException {
        Produto produto = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.CD_PRODUTO, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("FROM PRODUTO AS P ")
                    .append("WHERE P.CD_PRODUTO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            produto = new Produto();
            while (rs.next()) {
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Produto pelo Codigo!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Produto pelo Codigo!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return produto;
    }

    @Override
    public List<Produto> getByName(String name) throws SQLException {
        List<Produto> listProduto = null;
        Produto produto = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.CD_PRODUTO, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("FROM PRODUTO AS P ")
                    .append("WHERE UPPER(P.DESCRICAO) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listProduto = new ArrayList<>();
            while (rs.next()) {
                produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                listProduto.add(produto);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Produto pela Descricao!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Produto pela Descricao!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listProduto;
    }

    @Override
    public List<Produto> getAll() throws SQLException {
        List<Produto> listProduto = null;
        Produto produto = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM PRODUTO";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listProduto = new ArrayList<>();
            while (rs.next()) {
                produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                listProduto.add(produto);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Produtos!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Produtos!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listProduto;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_PRODUTO),0)+1 AS MAIOR FROM PRODUTO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID do Produto!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID do Produto!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            //this.connection.close();
        }
        return 1;
    }
    
}
