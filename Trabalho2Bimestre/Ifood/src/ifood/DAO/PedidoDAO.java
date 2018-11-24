/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.jdbc.ConnectionFactory;
import ifood.model.ItemPedido;
import ifood.model.Pedido;
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
public class PedidoDAO implements GenericDAO<Pedido>{
    
    private Connection connection = null;

    @Override
    public void save(Pedido entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PEDIDO (CD_PEDIDO, VL_TOTAL, PAGO, ID_CLIENTE, ID_EMPRESA) ")
                    .append("VALUES (?, ?, ?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setDouble(2, entity.getVlTotal());
            pstm.setBoolean(3, entity.isPago());
            pstm.setInt(4, entity.getCliente().getId());
            pstm.setInt(5, entity.getEmpresa().getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(Pedido entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PEDIDO SET VL_TOTAL = ?, PAGO = ? ")
                    .append("WHERE CD_PEDIDO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setDouble(1, entity.getVlTotal());
            pstm.setBoolean(2, entity.isPago());;
            pstm.setInt(3, entity.getCodigo());
            pstm.execute();
            pstm.close();
            ItemPedidoDAO itemPedidoDao = new ItemPedidoDAO();
            /*itemPedidoDao.delete(entity.getCodigo());
            for (ItemPedido ip : entity.getItemPedidoList()) {
                itemPedidoDao.save(ip);
            }*/
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Pedido!");
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
            ItemPedidoDAO itemPedidoDao = new ItemPedidoDAO();
            itemPedidoDao.delete(id);
            String sql = "DELETE FROM PEDIDO WHERE CD_PEDIDO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Pedido!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public Pedido getById(int id) throws SQLException {
        Pedido pedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.CD_PEDIDO, P.VL_TOTAL, P.PAGO, ")
                    .append("P.ID_CLIENTE, P.ID_EMPRESA ")
                    .append("FROM PEDIDO AS P ")
                    .append("WHERE CD_PEDIDO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            pedido = new Pedido();
            while (rs.next()) {
                pedido.setCodigo(rs.getInt("CD_PEDIDO"));
                pedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                pedido.setPago(rs.getBoolean("PAGO"));
                ClienteDAO clienteDao = new ClienteDAO();
                pedido.setCliente(clienteDao.getById(rs.getInt("ID_CLIENTE")));
                EmpresaDAO empresaDao = new EmpresaDAO();
                pedido.setEmpresa(empresaDao.getById(rs.getInt("ID_EMPRESA")));
                ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
                pedido.setItemPedidoList(itemPedidoDAO.getListaItemPedidoById(rs.getInt("CD_PEDIDO")));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Pedido pelo Codigo!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Pedido pelo Codigo!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return pedido;
    }

    @Override
    public List<Pedido> getByName(String name) throws SQLException {
        List<Pedido> listPedido = null;
        Pedido pedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.CD_PEDIDO, P.VL_TOTAL, P.PAGO, ")
                    .append("P.ID_CLIENTE, P.ID_EMPRESA, ")
                    .append("PES.ID, PES.NOME, C.CPF, C.DTNASCIMENTO, C.TELEFONE, ")
                    .append("C.ENDERECO, C.RG ")
                    .append("FROM PEDIDO AS P ")
                    .append("INNER JOIN CLIENTE AS C ON (C.ID = P.ID_CLIENTE)")
                    .append("INNER JOIN PESSOA AS PES ON (PES.ID = C.ID) ")
                    .append("WHERE UPPER(PES.NOME) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listPedido = new ArrayList<>();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setCodigo(rs.getInt("CD_PEDIDO"));
                pedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                pedido.setPago(rs.getBoolean("PAGO"));
                ClienteDAO clienteDao = new ClienteDAO();
                pedido.setCliente(clienteDao.getById(rs.getInt("ID_CLIENTE")));
                EmpresaDAO empresaDao = new EmpresaDAO();
                pedido.setEmpresa(empresaDao.getById(rs.getInt("ID_EMPRESA")));
                listPedido.add(pedido);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Pedido pelo Nome do Cliente!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Pedido pelo Nome do Cliente!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listPedido;
    }

    @Override
    public List<Pedido> getAll() throws SQLException {
        List<Pedido> listPedido = null;
        Pedido pedido = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM PEDIDO";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listPedido = new ArrayList<>();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setCodigo(rs.getInt("CD_PEDIDO"));
                pedido.setVlTotal(rs.getDouble("VL_TOTAL"));
                pedido.setPago(rs.getBoolean("PAGO"));
                ClienteDAO clienteDao = new ClienteDAO();
                pedido.setCliente(clienteDao.getById(rs.getInt("ID_CLIENTE")));
                EmpresaDAO empresaDao = new EmpresaDAO();
                pedido.setEmpresa(empresaDao.getById(rs.getInt("ID_EMPRESA")));
                listPedido.add(pedido);
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
        return listPedido;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_PEDIDO),0)+1 AS MAIOR FROM PEDIDO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo Codigo do Pedido!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo Codigo do Pedido!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            //this.connection.close();
        }
        return 1;
    }
    

    
}
