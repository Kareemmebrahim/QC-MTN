package io.paysky.qc.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Get_Request_Id {

    public static String getRequestId(String orderNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String requestId = null;

        try {
            // Get connection using DBConnectionManager
            connection = DBConnectionManager.getConnection();

            if (connection != null) {
                // SQL query to fetch RequestId
                String query = "SELECT RequestId FROM Shipments WHERE OrderId = ?";

                // Prepare and set the parameter
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, orderNumber);

                // Execute the query
                resultSet = preparedStatement.executeQuery();

                // Fetch RequestId
                if (resultSet.next()) {
                    requestId = resultSet.getString("RequestId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cleanup resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnectionManager.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requestId;
    }
    // Main method to test the functionality
   /*public static void main(String[] args) {
        // Test the getRequestId method with a sample OrderNumber
        String orderNumber = "10554";  // valid order number for testing
        String requestId = getRequestId(orderNumber);

        if (requestId != null) {
            System.out.println("RequestId for OrderNumber " + orderNumber + " is: " + requestId);
        } else {
            System.out.println("No RequestId found for OrderNumber " + orderNumber);
        }
    }*/
}