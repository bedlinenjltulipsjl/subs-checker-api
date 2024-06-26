package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.income.Income;
import dev.guarmo.jwttokenserver.model.income.dto.GetIncomeDto;
import dev.guarmo.jwttokenserver.model.income.mapper.IncomeMapper;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.IncomeRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final UserCredentialsRepository userCredentialsRepository;
    private final IncomeMapper incomeMapper;
    private final IncomeRepository incomeRepository;
    private final UserService userService;
    @Value("${referral.lvl1.part}")
    private Double referralLvl1PartInPercents;
    @Value("${referral.lvl2.part}")
    private Double referralLvl2PartInPercents;

    public List<GetIncomeDto> addBonusUpperReferrals(Double amount,
                                                     String bonusFromUserWithLogin) {

        UserCredentials userThatSendsBonuses = userCredentialsRepository.findByLogin(bonusFromUserWithLogin).orElseThrow();
        UserCredentials bonusToLvl1 = userThatSendsBonuses.getUpperReferral();
        UserCredentials bonusToLvl2 = bonusToLvl1.getUpperReferral();

        Income income1 = incomeMapper.createBonusWithSenderAndAmount(
                amount*referralLvl2PartInPercents,
                userThatSendsBonuses);

        income1.setBonusAmount(amount*referralLvl1PartInPercents);
        Income bonusLvl1 = incomeRepository.save(income1);
        bonusToLvl1.setBalanceAmount(bonusToLvl1.getBalanceAmount() + bonusLvl1.getBonusAmount());
        bonusToLvl1.getIncomes().add(bonusLvl1);
        userCredentialsRepository.save(bonusToLvl1);

        Income income2 = incomeMapper.createBonusWithSenderAndAmount(
                amount*referralLvl2PartInPercents,
                userThatSendsBonuses);
        Income bonusLvl2 = incomeRepository.save(income2);
        bonusToLvl2.setBalanceAmount(bonusToLvl2.getBalanceAmount() + bonusLvl2.getBonusAmount());
        bonusToLvl2.getIncomes().add(bonusLvl2);
        userCredentialsRepository.save(bonusToLvl2);

        // Set some additional information to be crystal clear
        GetIncomeDto getIncomeDto1 = incomeMapper.toGetDto(bonusLvl1);
        getIncomeDto1.setIncomeCausedByUserTgName(userThatSendsBonuses.getName());
        getIncomeDto1.setIncomeSendToTgName(bonusToLvl1.getName());

        // Set some additional information to be crystal clear
        GetIncomeDto getIncomeDto2 = incomeMapper.toGetDto(bonusLvl2);
        getIncomeDto2.setIncomeCausedByUserTgName(userThatSendsBonuses.getName());
        getIncomeDto2.setIncomeSendToTgName(bonusToLvl1.getName());
        return List.of(getIncomeDto1, getIncomeDto2);
    }

    public List<GetIncomeDto> findAllIncomesByLogin(String tgid) {
        return userService.findByLoginModel(tgid).getIncomes()
                .stream()
                .map(incomeMapper::toGetDto)
                .toList();
    }
}
