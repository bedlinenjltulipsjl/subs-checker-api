package dev.guarmo.jwttokenserver.model.income.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.income.Income;
import dev.guarmo.jwttokenserver.model.income.dto.GetIncomeDto;
import dev.guarmo.jwttokenserver.model.income.dto.PostIncomeDto;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = IncomeMapperHelper.class)
public interface IncomeMapper {
    GetIncomeDto toGetDto(Income income);

    @Mapping(target = "incomeCausedByUser", source = "incomeCausedByUserTgName", qualifiedByName = "createUserFromLogin")
    Income toModel(PostIncomeDto postIncomeDto);

    default Income createBonusWithSenderAndAmount(Double amount, UserCredentials incomeCausedByUser) {
        Income income = new Income();
        income.setIncomeCausedByUser(incomeCausedByUser);
        income.setBonusAmount(amount);
        return income;
    }
}
