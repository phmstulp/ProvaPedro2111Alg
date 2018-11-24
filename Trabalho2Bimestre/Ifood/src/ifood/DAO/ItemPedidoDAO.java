/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.jdbc.ConnectionFactory;
import ifood.model.ItemPedido;
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
public class ItemPedidoDAO implements GenericDAO<ItemPedido> {
    
    private Connection connection = null;

    @Override
    public void save(ItemPedido entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ITEMPEDIDO (CD_ITEMPEDIDO, CD_PEDIDO, CD_PRODUTO, ")
                    .append("QUANTIDADE, VL_TOTAL) ")
                    .append("VALUES (?, ?, ?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setInt(2, entity.getCodigoPedido());
            pstm.setInt(3, entity.getProduto().getCodigo());
            pstm.setInt(4, entity.getQuantidade());
            pstm.setDouble(5, entity.getVlTotal());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Item Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Item Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(ItemPedido entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ITEMPEDIDO SET QUANTIDADE = ?, VL_TOTAL = ? ")
                    .append("WHERE CD_PEDIDO = ? AND CD_PRODUTO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getQuantidade());
            pstm.setDouble(2, entity.getVlTotal());
            pstm.setInt(3, entity.getCodigo());
            pstm.setInt(4, entity.getProduto().getCodigo());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Item Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Item Pedido!");
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
            String sql = "DELETE FROM ITEMPEDIDO WHERE CD_PEDIDO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Item Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Item Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public ItemPedido getById(int id) throws SQLException {
        ItemPedido itemPedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT I.CD_ITEMPEDIDO, I.CD_PEDIDO, I.CD_PRODUDO, ")
                    .append("I.QUANTDADE, I.VL_TOTAL, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("INNER JOIN PRODUTO AS P ON (P.CD_PRODUTO = I.CD_PRODUTO) ")
                    .append("WHERE I.CD_ITEMPEDIDO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            itemPedido = new ItemPedido();
            while (rs.next()) {
                itemPedido.setCodigo(rs.getInt("CD_ITEMPEDIDO"));
                itemPedido.setCodigoPedido(rs.getInt("CD_PEDIDO"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                itemPedido.setProduto(produto);
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Item Pedido pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Item Pedido pelo ID!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return itemPedido;
    }

    @Override
    public List<ItemPedido> getByName(String name) throws SQLException {
        List<ItemPedido> listItemPedido = null;
        ItemPedido itemPedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT I.CD_ITEMPEDIDO, I.CD_PEDIDO, I.CD_PRODUDO, ")
                    .append("I.QUANTDADE, I.VL_TOTAL, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("INNER JOIN PRODUTO AS P ON (P.CD_PRODUTO = I.CD_PRODUTO) ")
                    .append("WHERE UPPER(P.DESCRICAO) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listItemPedido = new ArrayList<ItemPedido>();
            while (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setCodigo(rs.getInt("CD_ITEMPEDIDO"));
                itemPedido.setCodigoPedido(rs.getInt("CD_PEDIDO"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                itemPedido.setProduto(produto);
                listItemPedido.add(itemPedido);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Item Pedido pela "
                    + "Descricao do Produto!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Item "
                    + "Pedido pela Descricao do Produto!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listItemPedido;
    }

    @Override
    public List<ItemPedido> getAll() throws SQLException {
        List<ItemPedido> listItemPedido = null;
        ItemPedido itemPedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT I.CD_ITEMPEDIDO, I.CD_PEDIDO, I.CD_PRODUDO, ")
                    .append("I.QUANTDADE, I.VL_TOTAL, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("INNER JOIN PRODUTO AS P ON (P.CD_PRODUTO = I.CD_PRODUTO) ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listItemPedido = new ArrayList<>();
            while (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setCodigo(rs.getInt("CD_ITEMPEDIDO"));
                itemPedido.setCodigoPedido(rs.getInt("CD_PEDIDO"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                itemPedido.setProduto(produto);
                listItemPedido.add(itemPedido);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Itens Pedidos!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Itens Pedidos!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listItemPedido;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_ITEMPEDIDO),0)+1 AS MAIOR FROM ITEMPEDIDO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Item Pedido!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Item Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            //this.connection.close();
        }
        return 1;
    }
    
    public List<ItemPedido> getListaItemPedidoById(int id) throws SQLException {
        List<ItemPedido> listItemPedido = null;
        ItemPedido itemPedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT I.CD_ITEMPEDIDO, I.CD_PEDIDO, I.CD_PRODUTO, ")
                    .append("I.QUANTIDADE, I.VL_TOTAL, P.DESCRICAO, P.VL_UNITARIO ")
                    .append("FROM ITEMPEDIDO AS I ")
                    .append("INNER JOIN PRODUTO AS P ON (P.CD_PRODUTO = I.CD_PRODUTO) ")
                    .append("WHERE I.CD_PEDIDO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listItemPedido = new ArrayList<ItemPedido>();
            while (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setCodigo(rs.getInt("CD_ITEMPEDIDO"));
                itemPedido.setCodigoPedido(rs.getInt("CD_PEDIDO"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("CD_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setVlUnitario(rs.getDouble("VL_UNITARIO"));
                itemPedido.setProduto(produto);
                listItemPedido.add(itemPedido);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Item Pedido pelo "
                    + "Codigo do Pedido!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Item "
                    + "Pedido pelo Codigo do Pedido!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listItemPedido;
    }
    
}
