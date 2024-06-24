package dev.guarmo.jwttokenserver.model.bonus.mapper;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BonusMapperHelper {
    private final UserCredentialsRepository userCredentialsRepository;

    @Named("createUserFromLogin")
    public UserCredentials createUserFromLogin(final String userLogin) {
        return userCredentialsRepository.findByLogin(userLogin).orElseThrow();
    }
}
