/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.DAO;

import ifood.jdbc.ConnectionFactory;
import ifood.model.Empresa;
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
public class EmpresaDAO implements GenericDAO<Empresa> {

    private Connection connection = null;

    @Override
    public void save(Empresa entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            Pessoa pessoa = new Pessoa() {
            };
            PessoaDAO pessoaDao = new PessoaDAO();
            pessoa.setId(entity.getId());
            pessoa.setNome(entity.getNome());
            pessoaDao.save(pessoa);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO EMPRESA (ID, CNPJ, TELEFONE, IE, ENDERECO) ")
                    .append("VALUES (?, ?, ?, ?, ?)");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getId());
            pstm.setString(2, entity.getCnpj());
            pstm.setInt(3, entity.getTelefone());
            pstm.setString(4, entity.getIe());
            pstm.setString(5, entity.getEndereco());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar Empresa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao salvar Empresa!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public void update(Empresa entity) throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.connection.setAutoCommit(false);
            Pessoa pessoa = new Pessoa() {
            };
            PessoaDAO pessoaDao = new PessoaDAO();
            pessoa.setId(entity.getId());
            pessoa.setNome(entity.getNome());
            pessoaDao.update(pessoa);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE EMPRESA SET CNPJ = ?, TELEFONE = ?, ")
                    .append("IE = ?, ENDERECO = ? WHERE ID = ?");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getCnpj());
            pstm.setInt(2, entity.getTelefone());
            pstm.setString(3, entity.getIe());
            pstm.setString(4, entity.getEndereco());
            pstm.setInt(5, entity.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Empresa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao alterar Empresa!");
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
            String sql = "DELETE FROM EMPRESA WHERE ID = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.execute();
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar Empresa!");
            sqle.printStackTrace();
            this.connection.rollback();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao deletar Empresa!");
            ex.printStackTrace();
            this.connection.rollback();
        } finally {
            this.connection.commit();
            //this.connection.close();
        }
    }

    @Override
    public Empresa getById(int id) throws SQLException {
        Empresa empresa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, E.CNPJ, E.TELEFONE, ")
                    .append("E.IE, E.ENDERECO FROM EMPRESA AS E ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = E.ID) ")
                    .append("WHERE E.ID = " + id);
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            empresa = new Empresa();
            while (rs.next()) {
                empresa.setId(rs.getInt("ID"));
                empresa.setNome(rs.getString("NOME"));
                empresa.setCnpj(rs.getString("CNPJ"));
                empresa.setTelefone(rs.getInt("TELEFONE"));
                empresa.setIe(rs.getString("IE"));
                empresa.setEndereco(rs.getString("ENDERECO"));
            }
            pstm.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Empresa pelo ID!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Empresa pelo ID!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return empresa;
    }

    @Override
    public List<Empresa> getByName(String name) throws SQLException {
        List<Empresa> listEmpresa = null;
        Empresa empresa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, E.CNPJ, E.TELEFONE, ")
                    .append("E.IE, E.ENDERECO FROM EMPRESA AS E ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = E.ID) ")
                    .append("WHERE UPPER(P.NOME) LIKE UPPER('%" + name + "%') ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listEmpresa = new ArrayList<>();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("ID"));
                empresa.setNome(rs.getString("NOME"));
                empresa.setCnpj(rs.getString("CNPJ"));
                empresa.setTelefone(rs.getInt("TELEFONE"));
                empresa.setIe(rs.getString("IE"));
                empresa.setEndereco(rs.getString("ENDERECO"));
                listEmpresa.add(empresa);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Empresa pelo Nome!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar Empresa pelo Nome!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listEmpresa;
    }

    @Override
    public List<Empresa> getAll() throws SQLException {
        List<Empresa> listEmpresa = null;
        Empresa empresa = null;
        try {
            this.connection = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT P.ID, P.NOME, E.CNPJ, E.TELEFONE, ")
                    .append("E.IE, E.ENDERECO FROM EMPRESA AS E ")
                    .append("INNER JOIN PESSOA AS P ON (P.ID = E.ID) ");
            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            listEmpresa = new ArrayList<>();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("ID"));
                empresa.setNome(rs.getString("NOME"));
                empresa.setCnpj(rs.getString("CNPJ"));
                empresa.setTelefone(rs.getInt("TELEFONE"));
                empresa.setIe(rs.getString("IE"));
                empresa.setEndereco(rs.getString("ENDERECO"));
                listEmpresa.add(empresa);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar todas as Empresas!");
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha inesperada ao buscar todas as Empresas!");
            ex.printStackTrace();
        } finally {
            //this.connection.close();
        }
        return listEmpresa;
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
