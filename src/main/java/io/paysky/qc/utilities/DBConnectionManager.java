package io.paysky.qc.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    // Define Azure SQL Database URL, username, and password
    private static final String DB_URL = "jdbc:sqlserver://ivory.database.windows.net:1433;"
            + "database=StoreCloud_Shipment;"
            + "encrypt=true;trustServerCertificate=false;"
            + "loginTimeout=30;";
    private static final String DB_USERNAME = "MTN";
    private static final String DB_PASSWORD = "P@$$w0rdmt";

    // Load SQL Server JDBC driver
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("SQL Server JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    /**
     * Method to establish and validate a database connection.
     *
     * @return a valid database connection or null if failed
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establish connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Validate connection
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to the database successfully!");
            } else {
                throw new SQLException("Failed to establish a valid database connection.");
            }
        } catch (SQLException e) {
            System.err.println("Connection to the database failed!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method to close the database connection.
     *
     * @param connection the database connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                } else {
                    System.out.println("Connection was already closed.");
                }
            } catch (SQLException e) {
                System.err.println("Failed to close connection!");
                e.printStackTrace();
            }
        }
    }

    // Test the database connection
   /* public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Connection test successful!");
            closeConnection(connection);
        } else {
            System.err.println("Connection test failed!");
        }
    }*/
}
