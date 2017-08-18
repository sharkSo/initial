package com.silvershark.connection;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class PhoenixExecuteSQL {

    private int[] types;

    public static ResultSet executeQUERY(String query, Connection conn) {

        try {
            String sql = query;
            //Prepara a instrução SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //stmt.close();
            conn.close();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(PhoenixExecuteSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void executeUPSERT(String query, Connection conn) {

        try {
            String sql = query;
            //Prepara a instrução SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Executa a instrução SQL
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(PhoenixExecuteSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void executeDDL(String query, Connection conn) {

        try {
            String sql = query;
            //Prepara a instrução SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Executa a instrução SQL
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(PhoenixExecuteSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + ": " + resultSet.getMetaData().getColumnName(i) + " Type: " + resultSet.getMetaData().getColumnTypeName(i));
        }
    }

    public static void writeResultSet(ResultSet resultSet) throws SQLException {

        int[] types = new int[]{java.sql.Types.ARRAY, java.sql.Types.BIGINT, java.sql.Types.BINARY,
            java.sql.Types.BIT, java.sql.Types.BLOB, java.sql.Types.BOOLEAN,
            java.sql.Types.CHAR, java.sql.Types.CLOB, java.sql.Types.DATALINK,
            java.sql.Types.DATE, java.sql.Types.DECIMAL, java.sql.Types.DISTINCT,
            java.sql.Types.DOUBLE, java.sql.Types.FLOAT, java.sql.Types.INTEGER,
            java.sql.Types.JAVA_OBJECT, java.sql.Types.LONGNVARCHAR, java.sql.Types.LONGVARBINARY,
            java.sql.Types.LONGVARCHAR, java.sql.Types.NCHAR, java.sql.Types.NCLOB, java.sql.Types.NULL,
            java.sql.Types.NUMERIC, java.sql.Types.NVARCHAR, java.sql.Types.OTHER, java.sql.Types.REAL,
            java.sql.Types.REF, java.sql.Types.REF_CURSOR, java.sql.Types.ROWID, java.sql.Types.SMALLINT,
            java.sql.Types.SQLXML, java.sql.Types.STRUCT, java.sql.Types.TIME, java.sql.Types.TIMESTAMP,
            java.sql.Types.TIMESTAMP_WITH_TIMEZONE, java.sql.Types.TIME_WITH_TIMEZONE, java.sql.Types.TINYINT,
            java.sql.Types.VARBINARY, java.sql.Types.VARCHAR};

        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {

                System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i) + " "
                        + resultSet.getMetaData().getColumnTypeName(i));

                int type = resultSet.getMetaData().getColumnType(i);

                for (int j = 0; j < types.length; j++) {
                    if (types[j] == type) {

                        //String s  = resultSet.getMetaData().getColumnTypeName(i);
                        String s = resultSet.getString(resultSet.getMetaData().getColumnName(i));

                        System.out.println(resultSet.getMetaData().getColumnName(i) + ": " + s);

                    }

                }

            }

        }
    }

}
