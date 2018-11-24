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
import provapedro2111.Util.DateUtil;
import provapedro2111.jdbc.ConnectionFactory;
import provapedro2111.model.Motorista;

/**
 *
 * @author fag
 */
public class MotoristaDAO implements GenericDAO<Motorista>{

    private Connection connection = null;
    FuncionarioDAO funcionarioDao = null;
    
    @Override
    public void save(Motorista entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            String dtVencimento = DateUtil.dateToString(entity.getDtVencimento());
            dtVencimento.replace("/", "-");
            sql.append("INSERT INTO MOTORISTA (CD_MOTORISTA, CD_FUNCIONARIO, NR_CNH, DT_VENCIMENTO) ")
                    .append("VALUES (?, ?, ?, '" + dtVencimento + "')");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getId());
            pstm.setInt(2, entity.getFuncionario().getId());
            pstm.setString(3, entity.getCnh());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Motorista!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Motorista!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public void update(Motorista entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            String dtVencimento = DateUtil.dateToString(entity.getDtVencimento());
            dtVencimento.replace("/", "-");
            sql.append("UPDATE MOTORISTA SET NR_CNH = ?, CD_FUNCIONARIO = ?, DT_VENCIMENTO = '" + dtVencimento + "' ")
                    .append("WHERE CD_MOTORISTA = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getCnh());
            pstm.setInt(2, entity.getFuncionario().getId());
            pstm.setInt(3, entity.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Motorista!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Motorista!");
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
            String sql = "DELETE FROM MOTORISTA WHERE CD_MOTORISTA = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Motorista!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Motorista!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public Motorista getById(int id) throws SQLException {
        Motorista motorista = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT M.CD_MOTORISTA, M.NR_CNH, M.CD_FUNCIONARIO, M.DT_VENCIMENTO ")
                    .append("FROM MOTORISTA AS M ")
                    .append("WHERE M.CD_MOTORISTA = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            motorista = new Motorista();
            while (rs.next()) {
                motorista.setId(rs.getInt("CD_MOTORISTA"));
                motorista.setCnh(rs.getString("NR_CNH"));
                motorista.setDtVencimento(rs.getDate("DT_VENCIMENTO"));
                funcionarioDao = new FuncionarioDAO();
                motorista.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Motorista pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Motorista pelo ID!");
            ex.printStackTrace();
        }
        return motorista;
    }

    @Override
    public List<Motorista> getByName(String name) throws SQLException {
        List<Motorista> listMotorista = null;
        Motorista motorista = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT M.CD_MOTORISTA, F.NM_FUNCIONARIO, M.NR_CNH, M.CD_FUNCIONARIO, M.DT_VENCIMENTO ")
                    .append("FROM MOTORISTA AS M ")
                    .append("INNER JOIN FUNCIONARIO AS F ON (F.CD_FUNCIONARIO = M.CD_FUNCIONARIO) ")
                    .append("WHERE UPPER(F.NM_FUNCIONARIO) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listMotorista = new ArrayList<>();
            while (rs.next()) {
                motorista = new Motorista();
                motorista.setId(rs.getInt("CD_MOTORISTA"));
                motorista.setCnh(rs.getString("NR_CNH"));
                motorista.setDtVencimento(rs.getDate("DT_VENCIMENTO"));
                funcionarioDao = new FuncionarioDAO();
                motorista.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                listMotorista.add(motorista);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Motorista pelo Nome do Funcionario!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Motorista pelo Nome do Funcionario!");
            ex.printStackTrace();
        }
        return listMotorista;
    }

    @Override
    public List<Motorista> getAll() throws SQLException {
        List<Motorista> listMotorista = null;
        Motorista motorista = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT M.CD_MOTORISTA, M.CD_FUNCIONARIO, M.NR_CNH, M.CD_FUNCIONARIO, M.DT_VENCIMENTO ")
                    .append("FROM MOTORISTA AS M ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listMotorista = new ArrayList<>();
            while (rs.next()) {
                motorista = new Motorista();
                motorista.setId(rs.getInt("CD_MOTORISTA"));
                motorista.setCnh(rs.getString("NR_CNH"));
                motorista.setDtVencimento(rs.getDate("DT_VENCIMENTO"));
                funcionarioDao = new FuncionarioDAO();
                motorista.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                listMotorista.add(motorista);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Motoristas!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Motoristas!");
            ex.printStackTrace();
        }
        return listMotorista;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_MOTORISTA),0)+1 AS MAIOR FROM MOTORISTA ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Motorista!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Motorista!");
            ex.printStackTrace();
        }
        return 1;
    }
    
}
