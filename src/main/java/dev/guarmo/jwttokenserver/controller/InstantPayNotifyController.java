package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.service.BonusService;
import dev.guarmo.jwttokenserver.service.TransactionService;
import dev.guarmo.jwttokenserver.teleg.TelegramService;
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
    private final BonusService bonusService;
    private final TelegramService telegramService;

    @PostMapping(value = "/pay/ipn", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> handlePaymentNotification(@RequestBody MultiValueMap<String, String> formData) {
        GetTransactionDto getTransactionDto = transactionService.addTransactionToUser(formData);
        var bonuses = bonusService.addBonusUpperReferrals(getTransactionDto.getAmount(), getTransactionDto.getLabel());

        telegramService.sendNotificationAboutSuccessTransaction(getTransactionDto);
        bonuses.forEach(telegramService::sendNotificationAboutAssignedBonus);

        log.info("Received Payment Notification: {}", getTransactionDto);
        log.info("Saved this bonus to this user: {}", bonuses);
        return ResponseEntity.ok("Thanks, notification received");
    }
}
