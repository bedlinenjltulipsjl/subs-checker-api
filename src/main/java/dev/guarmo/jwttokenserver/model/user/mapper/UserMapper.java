package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserWithReferralsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    GetContentUserDto toGetDto(UserCredentials user);

    GetUserWithReferralsDto toGetWithReferralsDto(UserCredentials user);

    @Mapping(target = "userContent.name", source = "name")
    @Mapping(target = "userContent.username", source = "username")
//    @Mapping(target = "userContent.balance", source = "name", defaultValue = "0")
    UserCredentials toModel(PostUserDto postUserDto);
}
