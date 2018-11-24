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
import provapedro2111.model.Agendamento;

/**
 *
 * @author fag
 */
public class AgendamentoDAO implements GenericDAO<Agendamento>{
    
    private Connection connection = null;
    FuncionarioDAO funcionarioDao = null;
    MotoristaDAO motoristaDao = null;
    VeiculoDAO veiculoDao = null;

    @Override
    public void save(Agendamento entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            String dtSaida = DateUtil.dateToString(entity.getDtSaida());
            String dtRetorno = DateUtil.dateToString(entity.getDtRetorno());
            dtSaida.replace("/", "-");
            dtRetorno.replace("/", "-");
            sql.append("INSERT INTO AGENDAMENTO (CD_AGENDAMENTO, CD_VEICULO, DS_ORIGEM, ")
                    .append("DS_DESTINO, CD_MOTORISTA, CD_FUNCIONARIO, DT_SAIDA, DT_RETORNO, ")
                    .append("NR_PASSAGEIROS, DS_OBSERVACAO) ")
                    .append("VALUES (?, ?, ?, ?, ?, ?, '" + dtSaida + "', '" + dtRetorno + "', ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setInt(2, entity.getVeiculo().getCodigo());
            pstm.setString(3, entity.getOrigem());
            pstm.setString(4, entity.getDestino());
            pstm.setInt(5, entity.getMotorista().getId());
            pstm.setInt(6, entity.getMotorista().getFuncionario().getId());
            pstm.setInt(7, entity.getNrPassageiros());
            pstm.setString(8, entity.getObservacao());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Agendamento!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Agendamento!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public void update(Agendamento entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            String dtSaida = DateUtil.dateToString(entity.getDtSaida());
            String dtRetorno = DateUtil.dateToString(entity.getDtRetorno());
            dtSaida.replace("/", "-");
            dtRetorno.replace("/", "-");
            sql.append("UPDATE AGENDAMENTO SET DS_ORIGEM = ?, DS_DESTINO = ?, CD_VEICULO = ?, CD_MOTORISTA = ?, CD_FUNCIONARIO = ?, ")
                    .append("DT_SAIDA = '" + dtSaida + "', DT_RETORNO = '" + dtRetorno + "', ")
                    .append("NR_PASSAGEIROS = ?, DS_OBSERVACAO = ? ")
                    .append("WHERE CD_AGENDAMENTO = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getOrigem());
            pstm.setString(2, entity.getDestino());
            pstm.setInt(3, entity.getVeiculo().getCodigo());
            pstm.setInt(4, entity.getMotorista().getId());
            pstm.setInt(5, entity.getMotorista().getFuncionario().getId());
            pstm.setInt(6, entity.getNrPassageiros());
            pstm.setString(7, entity.getObservacao());
            pstm.setInt(8, entity.getCodigo());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Agendamento!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Agendamento!");
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
            String sql = "DELETE FROM AGENDAMENTO WHERE CD_AGENDAMENTO = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Agendamento!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Agendamento!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
        }
    }

    @Override
    public Agendamento getById(int id) throws SQLException {
        Agendamento agendamento = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.CD_AGENDAMENTO, A.CD_VEICULO, A.DS_ORIGEM, A.DS_DESTINO, ")
                    .append("A.CD_MOTORISTA, A.CD_FUNCIONARIO, A.DT_SAIDA, A.DT_RETORNO, ")
                    .append("A.NR_PASSAGEIROS, A.DS_OBSERVACAO ")
                    .append("FROM AGENDAMENTO AS A ")
                    .append("WHERE A.CD_AGENDAMENTO = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            agendamento = new Agendamento();
            while (rs.next()) {
                agendamento.setCodigo(rs.getInt("CD_AGENDAMENTO"));
                veiculoDao = new VeiculoDAO();
                agendamento.setVeiculo(veiculoDao.getById(rs.getInt("CD_VEICULO")));
                agendamento.setOrigem(rs.getString("DS_ORIGEM"));
                agendamento.setDestino(rs.getString("DS_DESTINO"));
                motoristaDao = new MotoristaDAO();
                agendamento.setMotorista(motoristaDao.getById(rs.getInt("CD_MOTORISTA")));
                funcionarioDao = new FuncionarioDAO();
                agendamento.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                agendamento.setDtSaida(rs.getDate("DT_SAIDA"));
                agendamento.setDtRetorno(rs.getDate("DT_RETORNO"));
                agendamento.setNrPassageiros(rs.getInt("NR_PASSAGEIROS"));
                agendamento.setObservacao(rs.getString("DS_OBSERVACAO"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Agendamento pelo Codigo!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Agendamento pelo Codigo!");
            ex.printStackTrace();
        }
        return agendamento;
    }

    @Override
    public List<Agendamento> getByName(String name) throws SQLException {
        List<Agendamento> listAgendamento = null;
        Agendamento agendamento = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.CD_AGENDAMENTO, A.CD_VEICULO, A.DS_ORIGEM, A.DS_DESTINO, ")
                    .append("A.CD_MOTORISTA, A.CD_FUNCIONARIO, A.DT_SAIDA, A.DT_RETORNO, ")
                    .append("A.NR_PASSAGEIROS, A.DS_OBSERVACAO ")
                    .append("FROM AGENDAMENTO AS A ")
                    .append("WHERE UPPER(A.DS_ORIGEM) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listAgendamento = new ArrayList<>();
            while (rs.next()) {
                agendamento = new Agendamento();
                agendamento.setCodigo(rs.getInt("CD_AGENDAMENTO"));
                veiculoDao = new VeiculoDAO();
                agendamento.setVeiculo(veiculoDao.getById(rs.getInt("CD_VEICULO")));
                agendamento.setOrigem(rs.getString("DS_ORIGEM"));
                agendamento.setDestino(rs.getString("DS_DESTINO"));
                motoristaDao = new MotoristaDAO();
                agendamento.setMotorista(motoristaDao.getById(rs.getInt("CD_MOTORISTA")));
                funcionarioDao = new FuncionarioDAO();
                agendamento.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                agendamento.setDtSaida(rs.getDate("DT_SAIDA"));
                agendamento.setDtRetorno(rs.getDate("DT_RETORNO"));
                agendamento.setNrPassageiros(rs.getInt("NR_PASSAGEIROS"));
                agendamento.setObservacao(rs.getString("DS_OBSERVACAO"));
                listAgendamento.add(agendamento);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Agendamento pela Origem!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar agendamento pela Origem!");
            ex.printStackTrace();
        }
        return listAgendamento;
    }

    @Override
    public List<Agendamento> getAll() throws SQLException {
        List<Agendamento> listAgendamento = null;
        Agendamento agendamento = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.CD_AGENDAMENTO, A.CD_VEICULO, A.DS_ORIGEM, A.DS_DESTINO, ")
                    .append("A.CD_MOTORISTA, A.CD_FUNCIONARIO, A.DT_SAIDA, A.DT_RETORNO, ")
                    .append("A.NR_PASSAGEIROS, A.DS_OBSERVACAO ")
                    .append("FROM AGENDAMENTO AS A ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listAgendamento = new ArrayList<>();
            while (rs.next()) {
                agendamento = new Agendamento();
                agendamento.setCodigo(rs.getInt("CD_AGENDAMENTO"));
                veiculoDao = new VeiculoDAO();
                agendamento.setVeiculo(veiculoDao.getById(rs.getInt("CD_VEICULO")));
                agendamento.setOrigem(rs.getString("DS_ORIGEM"));
                agendamento.setDestino(rs.getString("DS_DESTINO"));
                motoristaDao = new MotoristaDAO();
                agendamento.setMotorista(motoristaDao.getById(rs.getInt("CD_MOTORISTA")));
                funcionarioDao = new FuncionarioDAO();
                agendamento.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                agendamento.setDtSaida(rs.getDate("DT_SAIDA"));
                agendamento.setDtRetorno(rs.getDate("DT_RETORNO"));
                agendamento.setNrPassageiros(rs.getInt("NR_PASSAGEIROS"));
                agendamento.setObservacao(rs.getString("DS_OBSERVACAO"));
                listAgendamento.add(agendamento);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todos os Agendamentos!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todos os Agendamentos!");
            ex.printStackTrace();
        }
        return listAgendamento;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_AGENDAMENTO),0)+1 AS MAIOR FROM AGENDAMENTO ";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar ultimo Codigo do Agendamento!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar ultimo Codigo do Agendamento!");
            ex.printStackTrace();
            this.connection.rollback();
        }
        return 1;
    }
    
    public List<Agendamento> getByDestino(String destino) throws SQLException {
        List<Agendamento> listAgendamento = null;
        Agendamento agendamento = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.CD_AGENDAMENTO, A.CD_VEICULO, A.DS_ORIGEM, A.DS_DESTINO, ")
                    .append("A.CD_MOTORISTA, A.CD_FUNCIONARIO, A.DT_SAIDA, A.DT_RETORNO, ")
                    .append("A.NR_PASSAGEIROS, A.DS_OBSERVACAO ")
                    .append("FROM AGENDAMENTO AS A ")
                    .append("WHERE UPPER(A.DS_DESTINO) LIKE UPPER('%" + destino + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listAgendamento = new ArrayList<>();
            while (rs.next()) {
                agendamento = new Agendamento();
                agendamento.setCodigo(rs.getInt("CD_AGENDAMENTO"));
                veiculoDao = new VeiculoDAO();
                agendamento.setVeiculo(veiculoDao.getById(rs.getInt("CD_VEICULO")));
                agendamento.setOrigem(rs.getString("DS_ORIGEM"));
                agendamento.setDestino(rs.getString("DS_DESTINO"));
                motoristaDao = new MotoristaDAO();
                agendamento.setMotorista(motoristaDao.getById(rs.getInt("CD_MOTORISTA")));
                funcionarioDao = new FuncionarioDAO();
                agendamento.setFuncionario(funcionarioDao.getById(rs.getInt("CD_FUNCIONARIO")));
                agendamento.setDtSaida(rs.getDate("DT_SAIDA"));
                agendamento.setDtRetorno(rs.getDate("DT_RETORNO"));
                agendamento.setNrPassageiros(rs.getInt("NR_PASSAGEIROS"));
                agendamento.setObservacao(rs.getString("DS_OBSERVACAO"));
                listAgendamento.add(agendamento);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Agendamento pelo Destino!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar agendamento pelo Destino!");
            ex.printStackTrace();
        }
        return listAgendamento;
    }
    
}
