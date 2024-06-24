package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.bonus.MoneyBonus;
import dev.guarmo.jwttokenserver.model.bonus.dto.GetBonusDto;
import dev.guarmo.jwttokenserver.model.bonus.mapper.BonusMapper;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.BonusRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusService {
    private final UserCredentialsRepository userCredentialsRepository;
    private final BonusMapper bonusMapper;
    private final BonusRepository bonusRepository;
    @Value("${referral.lvl1.part}")
    private Double referralLvl1PartInPercents;
    @Value("${referral.lvl2.part}")
    private Double referralLvl2PartInPercents;

    public List<GetBonusDto> addBonusUpperReferrals(Double amount,
                                                String bonusFromUserWithLogin) {

        UserCredentials userThatSendsBonuses = userCredentialsRepository.findByLogin(bonusFromUserWithLogin).orElseThrow();
        UserCredentials bonusToLvl1 = userThatSendsBonuses.getUpperReferral();
        UserCredentials bonusToLvl2 = bonusToLvl1.getUpperReferral();

        MoneyBonus moneyBonus1 = bonusMapper.createBonusWithSenderAndAmount(
                amount*referralLvl2PartInPercents,
                userThatSendsBonuses);

        moneyBonus1.setBonusAmount(amount*referralLvl1PartInPercents);
        MoneyBonus bonusLvl1 = bonusRepository.save(moneyBonus1);
        bonusToLvl1.getBonuses().add(bonusLvl1);
        userCredentialsRepository.save(bonusToLvl1);

        MoneyBonus moneyBonus2 = bonusMapper.createBonusWithSenderAndAmount(
                amount*referralLvl2PartInPercents,
                userThatSendsBonuses);
        MoneyBonus bonusLvl2 = bonusRepository.save(moneyBonus2);
        bonusToLvl2.getBonuses().add(bonusLvl2);
        userCredentialsRepository.save(bonusToLvl2);

        // Set some additional information to be crystal clear
        GetBonusDto getBonusDto1 = bonusMapper.toGetDto(bonusLvl1);
        getBonusDto1.setBonusFromUserTgName(userThatSendsBonuses.getName());
        getBonusDto1.setBonusOwnerUpperReferralTgName(bonusToLvl1.getName());

        // Set some additional information to be crystal clear
        GetBonusDto getBonusDto2 = bonusMapper.toGetDto(bonusLvl1);
        getBonusDto2.setBonusFromUserTgName(userThatSendsBonuses.getName());
        getBonusDto2.setBonusOwnerUpperReferralTgName(bonusToLvl1.getName());
        return List.of(getBonusDto1, getBonusDto2);
    }
}
