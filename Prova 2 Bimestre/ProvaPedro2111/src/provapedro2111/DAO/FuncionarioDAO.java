/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provapedro2111.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import provapedro2111.jdbc.ConnectionFactory;
import provapedro2111.model.Funcionario;

/**
 *
 * @author fag
 */
public class FuncionarioDAO implements GenericDAO<Funcionario>{
    
    private Connection connection = null;

    @Override
    public void save(Funcionario entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO FUNCIONARIO (CD_FUNCIONARIO, NM_FUNCIONARIO, NR_MATRICULA) ")
                    .append("VALUES (?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getId());
            pstm.setString(2, entity.getNome());
            pstm.setInt(3, entity.getMatricula());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Funcionario!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Funcionario!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public void update(Funcionario entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE FUNCIONARIO SET NM_FUNCIONARIO = ?, NR_MATRICULA = ? ")
                    .append("WHERE CD_FUNCIONARIO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getNome());
            pstm.setInt(2, entity.getMatricula());
            pstm.setInt(3, entity.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Funcionario!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Funcionario!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            String sql = "DELETE FROM FUNCIONARIO WHERE CD_FUNCIONARIO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Funcionario!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Funcionario!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public Funcionario getById(int id) throws SQLException {
        Funcionario funcionario = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT F.CD_FUNCIONARIO, F.NM_FUNCIONARIO, F.NR_MATRICULA ")
                    .append("FROM FUNCIONARIO AS F ")
                    .append("WHERE F.CD_FUNCIONARIO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            funcionario = new Funcionario();
            while (rs.next()) {
                funcionario.setId(rs.getInt("CD_FUNCIONARIO"));
                funcionario.setNome(rs.getString("NM_FUNCIONARIO"));
                funcionario.setMatricula(rs.getInt("NR_MATRICULA"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Funcionario pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Funcionario pelo ID!");
            ex.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> getByName(String name) throws SQLException {
        List<Funcionario> listFuncionario = null;
        Funcionario funcionario = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT F.CD_FUNCIONARIO, F.NM_FUNCIONARIO, F.NR_MATRICULA ")
                    .append("FROM FUNCIONARIO AS F ")
                    .append("WHERE UPPER(F.NM_FUNCIONARIO) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listFuncionario = new ArrayList<>();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("CD_FUNCIONARIO"));
                funcionario.setNome(rs.getString("NM_FUNCIONARIO"));
                funcionario.setMatricula(rs.getInt("NR_MATRICULA"));
                listFuncionario.add(funcionario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Funcionario pelo Nome!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Funcionario pelo Nome!");
            ex.printStackTrace();
        }
        return listFuncionario;
    }

    @Override
    public List<Funcionario> getAll() throws SQLException {
        List<Funcionario> listFuncionario = null;
        Funcionario funcionario = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT F.CD_FUNCIONARIO, F.NM_FUNCIONARIO, F.NR_MATRICULA ")
                    .append("FROM FUNCIONARIO AS F ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listFuncionario = new ArrayList<>();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("CD_FUNCIONARIO"));
                funcionario.setNome(rs.getString("NM_FUNCIONARIO"));
                funcionario.setMatricula(rs.getInt("NR_MATRICULA"));
                listFuncionario.add(funcionario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Funcionarios!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Funcionarios!");
            ex.printStackTrace();
        }
        return listFuncionario;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_FUNCIONARIO),0)+1 AS MAIOR FROM FUNCIONARIO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Funcionario!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Funcionario!");
            ex.printStackTrace();
        }
        return 1;
    }
    
}
