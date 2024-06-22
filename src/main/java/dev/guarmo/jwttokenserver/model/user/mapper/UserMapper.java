package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
//import dev.guarmo.jwttokenserver.model.user.UserContent;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserWithReferralsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    GetUserCredentialsDto toGetCredentialsDto(UserCredentials user);

    UserCredentials toModel(PostUserDto postUserDto);

    @Mapping(target = "transactionIds", ignore = true)
    GetContentUserDto toGetDto(UserCredentials user);
    @AfterMapping
    default void initIdsFromResponses(@MappingTarget GetContentUserDto dto,
                                      UserCredentials model) {
        List<Long> transactionIds = model.getTransactions()
                .stream()
                .map(PayTransaction::getId)
                .toList();
        dto.setTransactionIds(transactionIds);
    }

    GetUserWithReferralsDto toGetWithReferralsDto(UserCredentials user);
}
