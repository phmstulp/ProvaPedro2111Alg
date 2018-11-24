/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.Util.DateUtil;
import ifood.jdbc.ConnectionFactory;
import ifood.model.Cliente;
import ifood.model.Pessoa;
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
public class ClienteDAO implements GenericDAO<Cliente> {
    
    private Connection connection = null;

    @Override
    public void save(Cliente entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            Pessoa pessoa = new Pessoa() {};
            PessoaDAO pessoaDao = new PessoaDAO();
            pessoa.setId(entity.getId());
            pessoa.setNome(entity.getNome());
            pessoaDao.save(pessoa);
            StringBuilder sql = new StringBuilder();
            String dataNascimento = DateUtil.dateToString(entity.getDtnascimento());
            dataNascimento.replace("/", "-");
            sql.append("INSERT INTO CLIENTE (ID, CPF, DTNASCIMENTO, TELEFONE, ENDERECO, RG) ")
                    .append("VALUES (?, ?, '" + dataNascimento + "', ?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getId());
            pstm.setString(2, entity.getCpf());
            pstm.setInt(3, entity.getTelefone());
            pstm.setString(4, entity.getEndereco());
            pstm.setString(5, entity.getRg());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Cliente!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Cliente!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(Cliente entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            Pessoa pessoa = new Pessoa() {};
            PessoaDAO pessoaDao = new PessoaDAO();
            pessoa.setId(entity.getId());
            pessoa.setNome(entity.getNome());
            pessoaDao.update(pessoa);
            String dataNascimento = DateUtil.dateToString(entity.getDtnascimento());
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTE SET CPF = ?, DTNASCIMENTO = '" + dataNascimento + "', TELEFONE = ?, ")
                    .append("ENDERECO = ?, RG = ? WHERE ID = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getCpf());
            pstm.setInt(2, entity.getTelefone());
            pstm.setString(3, entity.getEndereco());
            pstm.setString(4, entity.getRg());
            pstm.setInt(5, entity.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Cliente!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Cliente!");
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
            String sql = "DELETE FROM CLIENTE WHERE ID = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Cliente!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Cliente!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public Cliente getById(int id) throws SQLException {
        Cliente cliente = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, C.CPF, C.DTNASCIMENTO, C.TELEFONE, ")
                    .append("C.ENDERECO, C.RG FROM CLIENTE AS C ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = C.ID) ")
                    .append("WHERE C.ID = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            cliente = new Cliente();
            while (rs.next()) {
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDtnascimento(rs.getDate("DTNASCIMENTO"));
                cliente.setTelefone(rs.getInt("TELEFONE"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setRg(rs.getString("RG"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Cliente pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Cliente pelo ID!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return cliente;
    }

    @Override
    public List<Cliente> getByName(String name) throws SQLException {
        List<Cliente> listCliente = null;
        Cliente cliente = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, C.CPF, C.DTNASCIMENTO, C.TELEFONE, ")
                    .append("C.ENDERECO, C.RG FROM CLIENTE AS C ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = C.ID) ")
                    .append("WHERE UPPER(P.NOME) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listCliente = new ArrayList<>();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDtnascimento(rs.getDate("DTNASCIMENTO"));
                cliente.setTelefone(rs.getInt("TELEFONE"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setRg(rs.getString("RG"));;
                listCliente.add(cliente);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Cliente pelo Nome!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Cliente pelo Nome!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listCliente;
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        List<Cliente> listCliente = null;
        Cliente cliente = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, C.CPF, C.DTNASCIMENTO, C.TELEFONE, ")
                    .append("C.ENDERECO, C.RG FROM CLIENTE AS C ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = C.ID) ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listCliente = new ArrayList<>();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDtnascimento(rs.getDate("DTNASCIMENTO"));
                cliente.setTelefone(rs.getInt("TELEFONE"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setRg(rs.getString("RG"));
                listCliente.add(cliente);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Clientes!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Clientes!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listCliente;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(ID),0)+1 AS MAIOR FROM PESSOA ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Cliente!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Cliente!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            //this.connection.close();
        }
        return 1;
    }
    
}
