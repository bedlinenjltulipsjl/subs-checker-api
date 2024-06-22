package dev.guarmo.jwttokenserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;

public class Test {
    public static void main(String[] args) throws Exception {
        // Define your data for creating invoice
        Map<String, Object> dataForCreatingInvoice = new HashMap<>();
        dataForCreatingInvoice.put("currencies", new String[] { "DASH" });
        dataForCreatingInvoice.put("amount", "0.06");
        dataForCreatingInvoice.put("ipn_url", "https://teslainvestgroup.com:6090/pay/ipn");
        dataForCreatingInvoice.put("success_url", "https://www.youtube.com/");
        dataForCreatingInvoice.put("ttl", 15);

        String secretKeyString = "qm5xvJrtjVR0PIFRm4nubR-XGTJHRmKWmHFwSIhEgxTZtVIZnPMfgQ";
        byte[] encodedBytes1 = secretKeyString.getBytes(StandardCharsets.UTF_8);

        String data = getData();
        byte[] encodedBytes2 = data.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(encodedBytes1));
        System.out.println(Arrays.toString(encodedBytes2));
        String string = calculateHMAC(encodedBytes1, encodedBytes2);
    }

    private static String getData() {
        Integer timestamp = 1718987015;

//        String s1 = Base64.getUrlEncoder().withoutPadding().encodeToString(encodedBytes1);
        String data = timestamp + """
                {"currencies": ["DASH"], "amount": "0.06", "ipn_url": "https://teslainvestgroup.com:6090/pay/ipn", "success_url": "https://www.youtube.com/", "ttl": 15}""";
        return data;
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

    public static String hmacSHA256(byte[] key, byte[] data) throws Exception {

        // Define the algorithm and key
//        String algorithm = "SHA256";
//        SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);

        // Initialize the Mac instance with the secret key
//        Mac mac = Mac.getInstance(algorithm);
//        mac.init(secretKeySpec);

        // Compute the HMAC
//        byte[] rawHmac = mac.doFinal(message);

//         Convert the byte array to a hexadecimal string
//        return DatatypeConverter.printHexBinary(rawHmac).toLowerCase();

        // Create a new SecretKeySpec for the given key and algorithm
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");

        // Get an instance of the Mac object for HmacSHA256 algorithm
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);

        // Compute the HMAC on the input data bytes
        byte[] hmacBytes = mac.doFinal(data);

        // Convert the HMAC bytes to a hexadecimal string
        return bytesToHex(hmacBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
