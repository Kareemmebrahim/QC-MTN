package io.paysky.qc.utilities;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

public class ApiCaller {

    public static void disableSSLVerification() throws NoSuchAlgorithmException, KeyManagementException {
        // Create a TrustManager that does not perform any certificate verification
        TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing (bypass certificate validation)
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing (bypass certificate validation)
                    }
                }
        };

        // Create an SSLContext
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCertificates, new java.security.SecureRandom());

        // Set the default SSLSocketFactory to ignore certificate validation
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Disable hostname verification
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }

    public static void sendPostRequest(String orderNumber) {
        try {
            // Step 1: Get the RequestId using the order number
            String requestId = Get_Request_Id.getRequestId(orderNumber);

            if (requestId == null) {
                System.err.println("No RequestId found for OrderNumber: " + orderNumber);
                return;
            }

            // Step 2: Construct the API endpoint URL
            String apiUrl = "https://marketuat.momo.africa:1010/Shipment/EvaShipment/webhook";
            URL url = new URL(apiUrl);

            // Step 3: Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Step 4: Create the request body
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("event_id", 3);
            jsonBody.put("Webhook", true);
            jsonBody.put("ride_status_id", 8);
            jsonBody.put("event_description", "");
            jsonBody.put("tracking_id", requestId);
            jsonBody.put("business_tracking_id", "");
            jsonBody.put("order_number", orderNumber);
            jsonBody.put("utc_timestamp", "");
            jsonBody.put("data", new JSONObject[] {}); // Empty array

            // Debug: Print the request payload
            System.out.println("Request Payload: " + jsonBody.toString());

            // Step 5: Send the request
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonBody.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            // Step 6: Check and log the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Capture the response, both for successful and error status codes
            BufferedReader reader;
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Debug: Print the response
            System.out.println("Response Body: " + response.toString());

            // Step 7: Validate the response
            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.optBoolean("success", false)) {
                System.out.println("API request was successful.");
            } else {
                System.err.println("API request failed: " + jsonResponse.optString("error", "Unknown error"));
            }

        } catch (Exception e) {
            System.err.println("An error occurred while calling the API:");
            e.printStackTrace();
        }
    }

/*   public static void main(String[] args) {
        try {
            // Disable SSL verification (use this in development or testing environments only)
            disableSSLVerification();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            System.err.println("Error disabling SSL verification");
            e.printStackTrace();
        }

        String orderNumber = "10589"; // Test order number
        sendPostRequest(orderNumber);
    }*/
}
