package dev.guarmo.jwttokenserver.teleg;

import dev.guarmo.jwttokenserver.model.income.dto.GetIncomeDto;
import dev.guarmo.jwttokenserver.model.invoice.dto.GetInvoiceDto;
import dev.guarmo.jwttokenserver.model.deposit.dto.GetDepositDto;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final Bot bot;
    @Value("${bot.notifchannel.id}")
    private Long DELIVERY_CHAT_ID;

    public void sendNotificationAboutWithdraw(UserCredentials userCredentials, GetWithdrawDto getWithdrawDto) {
        String text = userCredentials.getLogin() + "\n\n" + getWithdrawDto.toString();

        bot.sendMessageWithKeyboard(text, DELIVERY_CHAT_ID, String.valueOf(getWithdrawDto.getId()));
    }

    public void sendNotificationAboutStartingInvoice(GetInvoiceDto dto) {
        String text = dto.toString();

        bot.prepareAndSendMessage(DELIVERY_CHAT_ID, text);
    }

    public void sendNotificationAboutSuccessTransaction(GetDepositDto dto) {
        String text = dto.toString();

        bot.prepareAndSendMessage(DELIVERY_CHAT_ID, text);
    }

    public void sendNotificationAboutAssignedBonus(GetIncomeDto dto) {
        String text = dto.toString();
        bot.prepareAndSendMessage(DELIVERY_CHAT_ID, text);
    }

    public void sendNotificationAboutUserBuying(GetIncomeDto dto) {
        String text = dto.toString();
        bot.prepareAndSendMessage(DELIVERY_CHAT_ID, text);
    }
}
