package dev.guarmo.jwttokenserver;

//import okhttp3.*;
//import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;

@SpringBootApplication
public class JwtTokenServerApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(JwtTokenServerApplication.class, args);
//    }

//    public static void main(String[] args) throws Exception {
//        // Replace with your actual API key and secret key
//        String apiKey = "qlzEPr8gyYHVX-gVn9xDrNy8eMqnIKCelH0GkycB";
//        String secretKey = "qm5xvJrtjVR0PIFRm4nubR-XGTJHRmKWmHFwSIhEgxTZtVIZnPMfgQ";
//
//        // Data for creating the invoice
//        JSONObject dataForCreatingInvoice = new JSONObject();
//        dataForCreatingInvoice.put("currencies", new String[]{"DASH"});
//        dataForCreatingInvoice.put("amount", "0.06");
//        dataForCreatingInvoice.put("ipn_url", "https://teslainvestgroup.com:6090/pay/ipn");
//        dataForCreatingInvoice.put("success_url", "https://www.youtube.com/");
//        dataForCreatingInvoice.put("ttl", 15);
//
//        long timestamp = System.currentTimeMillis() / 1000;
//        System.out.println(timestamp);
//        // Convert data to string
//        String dumped = dataForCreatingInvoice.toString();
//
//        // Create the HMAC-SHA256 signature
//        String message = timestamp + dumped;
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
//        String sign = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8)));
//
//        // Create the request headers
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(dumped, MediaType.get("application/json; charset=utf-8"));
//        Request request = new Request.Builder()
//                .url("https://api.westwallet.io/address/create_invoice")
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("X-API-KEY", apiKey)
//                .addHeader("X-ACCESS-SIGN", sign)
//                .addHeader("X-ACCESS-TIMESTAMP", String.valueOf(timestamp))
//                .build();
//
//        // Send the request and print the response
//        try (Response response = client.newCall(request).execute()) {
//            if (response.body() != null) {
//                System.out.println(response.body().string());
//            } else {
//                System.out.println("Response body is null");
//            }
//        }
//    }
}
