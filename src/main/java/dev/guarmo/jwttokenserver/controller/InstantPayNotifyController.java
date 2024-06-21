package dev.guarmo.jwttokenserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Controller
public class InstantPayNotifyController {
    @PostMapping(value = "/pay/ipn", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> handlePaymentNotification(@RequestBody MultiValueMap<String, String> formData) {
        log.info("Received Payment Notification");
        // Convert form data to DTO
        PaymentNotification notification = new PaymentNotification();
        notification.setId(Long.parseLong(formData.getFirst("id")));
        notification.setAmount(Double.parseDouble(formData.getFirst("amount")));
        notification.setAddress(formData.getFirst("address"));
        notification.setDestTag(formData.getFirst("dest_tag"));
        notification.setLabel(formData.getFirst("label"));
        notification.setCurrency(formData.getFirst("currency"));
        notification.setStatus(formData.getFirst("status"));
        notification.setBlockchainConfirmations(Integer.parseInt(formData.getFirst("blockchain_confirmations")));
        notification.setFee(formData.getFirst("fee"));
        notification.setBlockchainHash(formData.getFirst("blockchain_hash"));

        log.info("Received payment notification: {}", notification);

        // Handle the notification based on the status
        if ("completed".equalsIgnoreCase(notification.getStatus())) {
            // Process completed payment
            log.info("Payment completed: {}", notification);
        } else if ("pending".equalsIgnoreCase(notification.getStatus())) {
            // Handle pending payment
            log.info("Payment pending: {}", notification);
        } else {
            log.warn("Unknown payment status: {}", notification.getStatus());
        }

        String s = "Notification received";
        return ResponseEntity.ok(s);
    }
}
