package dev.guarmo.jwttokenserver;

import okhttp3.*;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class ApiRequest {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // Replace these with your actual values
        String apiKey = "qlzEPr8gyYHVX-gVn9xDrNy8eMqnIKCelH0GkycB";
        String secretKeyString = "qm5xvJrtjVR0PIFRm4nubR-XGTJHRmKWmHFwSIhEgxTZtVIZnPMfgQ";
        String timestamp = "1718987015";
        String data = timestamp + """
                {"currencies": ["DASH"], "amount": "0.06", "ipn_url": "https://teslainvestgroup.com:6090/pay/ipn", "success_url": "https://www.youtube.com/", "ttl": 15}""";

        byte[] encodedBytes1 = secretKeyString.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes2 = data.getBytes(StandardCharsets.UTF_8);

        String sign = calculateHMAC(encodedBytes1, encodedBytes2);
        OkHttpClient client = new OkHttpClient();

        // Prepare the JSON body
        RequestBody body = RequestBody.create(data, MediaType.parse("application/json"));

        // Build the request
        Request request = new Request.Builder()
                .url("https://api.westwallet.io/address/create_invoice")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-KEY", apiKey)
                .addHeader("X-ACCESS-SIGN", sign)
                .addHeader("X-ACCESS-TIMESTAMP", timestamp)
                .build();

        // Make the request and handle the response
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                } else {
                    System.out.println("Request failed: " + response.body().string());
                }
            }
        });
    }

    public static String calculateHMAC(byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(message);
        String check = Hex.encodeHexString(hash);
        System.out.println(check);
        return check;
    }
}
