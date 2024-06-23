package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import dev.guarmo.jwttokenserver.model.withdraw.WithdrawStatus;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.mapper.WithdrawMapper;
import dev.guarmo.jwttokenserver.repository.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawServiceHelper {

    private final WithdrawRepository withdrawRepository;
    private final WithdrawMapper withdrawMapper;

    public GetWithdrawDto updateWithdrawStatus(String moneyWithdrawIdAsString, WithdrawStatus status) {
        Long moneyWithdrawId = Long.parseLong(moneyWithdrawIdAsString);
        MoneyWithdraw moneyWithdraw = withdrawRepository.findById(moneyWithdrawId).orElseThrow();
        moneyWithdraw.setWithdrawStatus(status);
        MoneyWithdraw saved = withdrawRepository.save(moneyWithdraw);
        return withdrawMapper.toGetDto(saved);
    }
}
