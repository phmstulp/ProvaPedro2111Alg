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
import provapedro2111.model.Veiculo;

/**
 *
 * @author fag
 */
public class VeiculoDAO implements GenericDAO<Veiculo>{
    
    private Connection connection = null;

    @Override
    public void save(Veiculo entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO VEICULO (CD_VEICULO, NR_PLACA, NR_ANO, NR_PASSAGEIROS) ")
                    .append("VALUES (?, ?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setString(2, entity.getPlaca());
            pstm.setInt(3, entity.getAno());
            pstm.setInt(4, entity.getPassageiros());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Veiculo!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Veiculo!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public void update(Veiculo entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VEICULO SET NR_PLACA = ?, NR_ANO = ?, NR_PASSAGEIROS = ? ")
                    .append("WHERE CD_VEICULO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getPlaca());
            pstm.setInt(2, entity.getAno());
            pstm.setInt(3, entity.getPassageiros());
            pstm.setInt(4, entity.getCodigo());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Veiculo!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Veiculoo!");
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
            String sql = "DELETE FROM VEICULO WHERE CD_VEICULO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Veiculo!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Veiculo!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public Veiculo getById(int id) throws SQLException {
        Veiculo veiculo = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT V.CD_VEICULO, V.NR_PLACA, V.NR_ANO, V.NR_PASSAGEIROS ")
                    .append("FROM VEICULO AS V ")
                    .append("WHERE V.CD_VEICULO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            veiculo = new Veiculo();
            while (rs.next()) {
                veiculo.setCodigo(rs.getInt("CD_VEICULO"));
                veiculo.setPlaca(rs.getString("NR_PLACA"));
                veiculo.setAno(rs.getInt("NR_ANO"));
                veiculo.setPassageiros(rs.getInt("NR_PASSAGEIROS"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Veiculo pelo Codigo!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Veiculo pelo Codigo!");
            ex.printStackTrace();
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> getByName(String name) throws SQLException {
        List<Veiculo> listVeiculo = null;
        Veiculo veiculo = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT V.CD_VEICULO, V.NR_PLACA, V.NR_ANO, V.NR_PASSAGEIROS ")
                    .append("FROM VEICULO AS V ")
                    .append("WHERE UPPER(V.NR_PLACA) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listVeiculo = new ArrayList<>();
            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("CD_VEICULO"));
                veiculo.setPlaca(rs.getString("NR_PLACA"));
                veiculo.setAno(rs.getInt("NR_ANO"));
                veiculo.setPassageiros(rs.getInt("NR_PASSAGEIROS"));
                listVeiculo.add(veiculo);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Veiculo pela Placa!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Veiculo pela Placa!");
            ex.printStackTrace();
        }
        return listVeiculo;
    }

    @Override
    public List<Veiculo> getAll() throws SQLException {
        List<Veiculo> listVeiculo = null;
        Veiculo veiculo = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT V.CD_VEICULO, V.NR_PLACA, V.NR_ANO, V.NR_PASSAGEIROS ")
                    .append("FROM VEICULO AS V ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listVeiculo = new ArrayList<>();
            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("CD_VEICULO"));
                veiculo.setPlaca(rs.getString("NR_PLACA"));
                veiculo.setAno(rs.getInt("NR_ANO"));
                veiculo.setPassageiros(rs.getInt("NR_PASSAGEIROS"));
                listVeiculo.add(veiculo);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Veiculos!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Veiculos!");
            ex.printStackTrace();
        }
        return listVeiculo;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_VEICULO),0)+1 AS MAIOR FROM VEICULO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo ID de Veiculo!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo ID de Veiculo!");
            ex.printStackTrace();
        }
        return 1;
    }
    
}
