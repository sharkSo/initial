package com.silvershark.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class PhoenixJDBCConnection {

    private static Connection connection = null;
    
    private static String status = "Não conectou...";

    public static Connection createConnection() {
        
    
        
        //String url = "jdbc:mysql://localhost:3306/aula1";
       String url = "jdbc:avatica:remote:url=http://silvershark.westus.cloudapp.azure.com:8765;serialization=PROTOBUF";
        //String url = "jdbc:phoenix:sandbox:2181:/hbase-unsecure"; //Nome da base de dados
        String user = ""; //nome do usuário do MySQL
        String password = ""; //senha do MySQL

        connection = null;
        
        try {

            Class.forName("org.apache.calcite.avatica.remote.Driver").newInstance();

            //Class.forName("org.apache.phoenix.jdbc.PhoenixDriver").newInstance();
                
            connection = DriverManager.getConnection(url, user, password);
            
            if (connection != null) {
 
                status = ("STATUS--->Connected successfully!");
 
            } else {
 
                status = ("STATUS--->Unable to connect!");
 
            }
             System.out.println(status);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PhoenixJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not connect to database.");
            
        } catch (InstantiationException ex) {
            Logger.getLogger(PhoenixJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PhoenixJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhoenixJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Specified driver could not be found.");
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {

        connection.close();
    }

}
