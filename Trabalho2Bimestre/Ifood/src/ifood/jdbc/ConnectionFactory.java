/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifood.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pedro Henrique Martins Stulp
 */
public class ConnectionFactory {
    
    private static Connection connection;

    private static String dsn = "jdbc:postgresql://localhost:5433/ifood";
    private static String login = "postgres";
    private static String senha = "postgres";

    public static Connection getConnection() { //Utilização de Singleton
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dsn, login, senha);
            } catch (SQLException ex) {
                System.out.println("Houve um erro ao conectar com o Banco de Dados!");
            }
        }

        return connection;
    }
    
}
