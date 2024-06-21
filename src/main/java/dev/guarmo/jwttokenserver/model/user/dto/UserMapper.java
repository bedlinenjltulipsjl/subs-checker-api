package dev.guarmo.jwttokenserver.model.user.dto;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;

public interface UserMapper {
    GetUserDto toGetDto(UserCredentials user);

    UserCredentials toModel(PostUserDto crmUserDto);
}
