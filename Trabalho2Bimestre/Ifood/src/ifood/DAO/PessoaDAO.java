/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.jdbc.ConnectionFactory;
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
public class PessoaDAO implements GenericDAO<Pessoa> {
    
    private Connection connection = null;

    @Override
    public void save(Pessoa entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            String sql ="INSERT INTO PESSOA (ID, NOME) VALUES (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getId());
            pstm.setString(2, entity.getNome());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Pessoa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Pessoa!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(Pessoa entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            String sql ="UPDATE PESSOA SET NOME = ? WHERE ID = ?";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getNome());
            pstm.setInt(2, entity.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Pessoa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Pessoa!");
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
            String sql = "DELETE FROM PESSOA WHERE ID = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Pessoa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Pessoa!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public Pessoa getById(int id) throws SQLException {
        Pessoa pessoa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM PESSOA WHERE ID = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            pessoa = new Pessoa() {};
            while (rs.next()) {
                pessoa.setId(rs.getInt("ID"));
                pessoa.setNome(rs.getString("NOME"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Pessoa pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Pessoa pelo ID!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return pessoa;
    }

    @Override
    public List<Pessoa> getByName(String name) throws SQLException {
        List<Pessoa> listPessoa = null;
        Pessoa pessoa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM PESSOA WHERE UPPER(NOME) LIKE UPPER('%" + name + "%') ";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listPessoa = new ArrayList<>();
            while (rs.next()) {
                pessoa = new Pessoa() {};
                pessoa.setId(rs.getInt("ID"));
                pessoa.setNome(rs.getString("NOME"));
                listPessoa.add(pessoa);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Pessoa pelo Nome!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Pessoa pelo Nome!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listPessoa;
    }

    @Override
    public List<Pessoa> getAll() throws SQLException {
        List<Pessoa> listPessoa = null;
        Pessoa pessoa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM PESSOA";
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listPessoa = new ArrayList<>();
            while (rs.next()) {
                pessoa = new Pessoa() {};
                pessoa.setId(rs.getInt("ID"));
                pessoa.setNome(rs.getString("NOME"));
                listPessoa.add(pessoa);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todas as Pessoas!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todas as Pessoas!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listPessoa;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            String sql = "SELECT COALESCE(MAX(ID),0) + 1 AS MAIOR FROM PESSOA ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Pessoa!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Pessoa!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return 1;
    }
    
}
