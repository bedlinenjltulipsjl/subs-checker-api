package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserWithReferralsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import dev.guarmo.jwttokenserver.service.UserService;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = {UserMapperHelper.class})
public interface UserMapper {
    @Mapping(target = "upperReferralLogin", source = "upperReferral", qualifiedByName = "mapUpperReferralToLogin")
    GetUserCredentialsDto toGetCredentialsDto(UserCredentials user);

    @Mapping(target = "upperReferral", source = "referralLogin", qualifiedByName = "mapUpperReferralByLogin")
    UserCredentials toModel(PostUserDto postUserDto);

    @Mapping(target = "transactionIds", ignore = true)
    GetContentUserDto toGetDto(UserCredentials user);
    @AfterMapping
    default void initArraysWithIdsAfterMapping(@MappingTarget GetContentUserDto dto,
                                      UserCredentials model) {
        List<Long> transactionIds = model.getTransactions()
                .stream()
                .map(PayTransaction::getId)
                .toList();
        dto.setTransactionIds(transactionIds);

        List<Long> withdrawIds = model.getWithdraws()
                .stream()
                .map(MoneyWithdraw::getId)
                .toList();
        dto.setWithdrawIds(withdrawIds);
    }

    GetUserWithReferralsDto toGetWithReferralsDto(UserCredentials user);
}
