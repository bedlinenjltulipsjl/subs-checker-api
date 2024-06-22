package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@Controller
public class InstantPayNotifyController {

    private final TransactionService transactionService;

    @PostMapping(value = "/pay/ipn", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> handlePaymentNotification(@RequestBody MultiValueMap<String, String> formData) {
        log.info("Received Payment Notification: {}", transactionService.addTransactionToUser(formData));
        return ResponseEntity.ok("Thanks, notification received");
    }
}
