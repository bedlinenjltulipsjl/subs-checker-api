package dev.guarmo.jwttokenserver;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;

import static dev.guarmo.jwttokenserver.ApiRequest.calculateHMAC;

public class HttpClientExample {
    public static void main(String[] args) {
        // Create HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Replace these values with your actual API key, sign, and timestamp
            String apiKey = "qlzEPr8gyYHVX-gVn9xDrNy8eMqnIKCelH0GkycB";
            String secretKeyString = "qm5xvJrtjVR0PIFRm4nubR-XGTJHRmKWmHFwSIhEgxTZtVIZnPMfgQ";
            String timestamp = ; // Replace with your actual timestamp

            // URL and JSON data for creating invoice
            String url = "https://api.westwallet.io/address/create_invoice";
            String json = """
                {"currencies": ["DASH"], "amount": "0.06", "ipn_url": "https://teslainvestgroup.com:6090/pay/ipn", "success_url": "https://www.youtube.com/", "ttl": 15}""";
            String data = timestamp + json;

            byte[] encodedBytes1 = secretKeyString.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes2 = data.getBytes(StandardCharsets.UTF_8);
            String sign = calculateHMAC(encodedBytes1, encodedBytes2);


            // Create a POST request
            HttpPost postRequest = new HttpPost(url);

            // Set headers (optional)
            postRequest.addHeader("Content-Type", "application/json");
            postRequest.addHeader("X-API-KEY", apiKey);
            postRequest.addHeader("X-ACCESS-SIGN", sign);
            postRequest.addHeader("X-ACCESS-TIMESTAMP", timestamp);

            StringEntity entity = new StringEntity(json);
            postRequest.setEntity(entity);

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                // Check response status
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response Status Code: " + statusCode);

                // Get the response body
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity);
                    System.out.println("Response Body: " + responseBody);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
