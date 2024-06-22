package dev.guarmo.jwttokenserver;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

import static dev.guarmo.jwttokenserver.ApiRequest.calculateHMAC;

public class WestWalletAPIClient {
    public static void main(String[] args) throws Exception {
        // Replace these values with your actual API key, sign, and timestamp
        String apiKey = "qlzEPr8gyYHVX-gVn9xDrNy8eMqnIKCelH0GkycB";
        String secretKeyString = "qm5xvJrtjVR0PIFRm4nubR-XGTJHRmKWmHFwSIhEgxTZtVIZnPMfgQ";
//        long timestamp = Instant.now().getEpochSecond(); // Replace with your actual timestamp
        String timestamp = "1718987015"; // Replace with your actual timestamp

        // URL and JSON data for creating invoice
        String url = "https://api.westwallet.io/address/create_invoice";
        String data = timestamp + """
                {"currencies": ["DASH"], "amount": "0.06", "ipn_url": "https://teslainvestgroup.com:6090/pay/ipn", "success_url": "https://www.youtube.com/", "ttl": 15}""";

        byte[] encodedBytes1 = secretKeyString.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes2 = data.getBytes(StandardCharsets.UTF_8);
        String sign = calculateHMAC(encodedBytes1, encodedBytes2);

        // Prepare headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-API-KEY", apiKey);
        headers.put("X-ACCESS-SIGN", sign);
        headers.put("X-ACCESS-TIMESTAMP", String.valueOf(timestamp));

        // Make the POST request
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // Set request headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // Send POST request
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Get the response
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        // Read response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            // Print result
            System.out.println(response.toString());
        }
    }
}
