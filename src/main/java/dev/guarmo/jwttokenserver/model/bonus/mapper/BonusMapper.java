package dev.guarmo.jwttokenserver.model.bonus.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.bonus.MoneyBonus;
import dev.guarmo.jwttokenserver.model.bonus.dto.GetBonusDto;
import dev.guarmo.jwttokenserver.model.bonus.dto.PostBonusDto;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = BonusMapperHelper.class)
public interface BonusMapper {
    GetBonusDto toGetDto(MoneyBonus moneyBonus);

    @Mapping(target = "bonusFrom", source = "bonusFromLogin", qualifiedByName = "createUserFromLogin")
    MoneyBonus toModel(PostBonusDto postBonusDto);

    default MoneyBonus createBonusWithSenderAndAmount(Double amount, UserCredentials bonusFrom) {
        MoneyBonus moneyBonus = new MoneyBonus();
        moneyBonus.setBonusFrom(bonusFrom);
        moneyBonus.setBonusAmount(amount);
        return moneyBonus;
    }
}
