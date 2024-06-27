package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.income.Income;
import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import dev.guarmo.jwttokenserver.model.deposit.Deposit;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.*;
import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class, uses = {UserMapperHelper.class})
public interface UserMapper {
    @Mapping(target = "upperReferralLogin", source = "upperReferral", qualifiedByName = "mapUpperReferralToLogin")
    GetUserCredentialsDto toGetCredentialsDto(UserCredentials user);

    @Mapping(target = "upperReferral", source = "referralLogin", qualifiedByName = "mapUpperReferralByLogin")
    UserCredentials toModel(PostUserDto postUserDto);

    GetContentWithoutHistoryUserDto toGetWithoutHistoryDto(UserCredentials user);

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

    @Mapping(target = "bottomReferral", ignore = true)
    @Mapping(target = "treeLevel", ignore = true)
    GetUserWithReferralsDto toGetWithReferralsDto(UserCredentials user);

    @AfterMapping
    default void mapReferralsAfterMapping(@MappingTarget GetUserWithReferralsDto dto, UserCredentials model) {
        mapReferrals(dto, model, 0);
    }

    default void mapReferrals(GetUserWithReferralsDto dto, UserCredentials model, int level) {
        dto.setTreeLevel(level);

        // Sets recursion basis (how deep fetch referrals)
        if (level >= 3) {
            dto.setBottomReferral(Collections.emptyList());
            return;
        }

        List<GetUserWithReferralsDto> referrals = model.getBottomReferrals()
                .stream()
                .map(referral -> {
                    GetUserWithReferralsDto referralDto = toGetWithReferralsDto(referral);
                    mapReferrals(referralDto, referral, level + 1);
                    return referralDto;
                })
                .collect(Collectors.toList());
        dto.setBottomReferral(referrals);
    }
}
