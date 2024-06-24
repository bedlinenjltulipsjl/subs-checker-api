package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.income.Income;
import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import dev.guarmo.jwttokenserver.model.deposit.Deposit;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserWithReferralsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = {UserMapperHelper.class})
public interface UserMapper {
    @Mapping(target = "upperReferralLogin", source = "upperReferral", qualifiedByName = "mapUpperReferralToLogin")
    GetUserCredentialsDto toGetCredentialsDto(UserCredentials user);

    @Mapping(target = "upperReferral", source = "referralLogin", qualifiedByName = "mapUpperReferralByLogin")
    UserCredentials toModel(PostUserDto postUserDto);

    GetContentUserDto toGetDto(UserCredentials user);
    @AfterMapping
    default void initArraysWithIdsAfterMapping(@MappingTarget GetContentUserDto dto,
                                      UserCredentials model) {
        List<Long> depositIds = model.getDeposits()
                .stream()
                .map(Deposit::getId)
                .toList();
        dto.setDepositIds(depositIds);

        List<Long> withdrawIds = model.getWithdraws()
                .stream()
                .map(MoneyWithdraw::getId)
                .toList();
        dto.setWithdrawIds(withdrawIds);

        List<Long> purchaseIds = model.getPurchases()
                .stream()
                .map(Purchase::getId)
                .toList();
        dto.setPurchaseIds(purchaseIds);

        List<Long> incomeIds = model.getIncomes()
                .stream()
                .map(Income::getId)
                .toList();
        dto.setIncomeIds(incomeIds);
    }

    GetUserWithReferralsDto toGetWithReferralsDto(UserCredentials user);
}
