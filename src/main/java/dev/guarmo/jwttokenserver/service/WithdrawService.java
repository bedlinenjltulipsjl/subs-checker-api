package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import dev.guarmo.jwttokenserver.model.withdraw.WithdrawStatus;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.PostWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.mapper.WithdrawMapper;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import dev.guarmo.jwttokenserver.repository.WithdrawRepository;
import dev.guarmo.jwttokenserver.teleg.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final WithdrawMapper withdrawMapper;
    private final TelegramService telegramService;

    public GetWithdrawDto addWithdrawRequest(PostWithdrawDto postWithdrawDto, String login) {
        MoneyWithdraw model = withdrawMapper.toModel(postWithdrawDto);
        model.setWithdrawStatus(WithdrawStatus.PENDING);
        MoneyWithdraw saved = withdrawRepository.save(model);

        UserCredentials userCredentials = userCredentialsRepository.findByLogin(login).orElseThrow();
        userCredentials.getWithdraws().add(model);
        userCredentials.setBalanceAmount(userCredentials.getBalanceAmount() - model.getWithdrawAmount());
        userCredentialsRepository.save(userCredentials);

        GetWithdrawDto withdrawGetDto = withdrawMapper.toGetDto(saved);

        telegramService.sendNotificationAboutWithdraw(userCredentials, withdrawGetDto);
        return withdrawGetDto;
    }

    public List<GetWithdrawDto> getWithdrawsOfUserByLogin(String tgid) {
        UserCredentials userCredentials = userCredentialsRepository.findByLogin(tgid).orElseThrow();
        return userCredentials.getWithdraws().stream().map(withdrawMapper::toGetDto).toList();
    }
}
