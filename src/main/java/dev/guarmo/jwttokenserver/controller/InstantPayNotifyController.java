package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.deposit.dto.GetDepositDto;
import dev.guarmo.jwttokenserver.service.DepositService;
import dev.guarmo.jwttokenserver.service.IncomeService;
import dev.guarmo.jwttokenserver.teleg.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@Controller
@CrossOrigin(allowedHeaders = "*")
public class InstantPayNotifyController {

    private final DepositService depositService;
    private final IncomeService incomeService;
    private final TelegramService telegramService;

    @PostMapping(value = "/pay/ipn", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> handlePaymentNotification(@RequestBody MultiValueMap<String, String> formData) {
        GetDepositDto getDepositDto = depositService.addTransactionToUser(formData);
        var bonuses = incomeService.addBonusUpperReferrals(getDepositDto.getAmount(), getDepositDto.getLabel());

        telegramService.sendNotificationAboutSuccessTransaction(getDepositDto);
        bonuses.forEach(telegramService::sendNotificationAboutAssignedBonus);

        log.info("Received Payment Notification: {}", getDepositDto);
        log.info("Saved this bonus to this user: {}", bonuses);
        return ResponseEntity.ok("Thanks, notification received");
    }
}
