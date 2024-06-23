package dev.guarmo.jwttokenserver.model.user.mapper;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperHelper {

    private final UserCredentialsRepository userCredentialsRepository;

    @Named("mapUpperReferralByLogin")
    public UserCredentials mapUpperReferralByLogin(String login) {
        return userCredentialsRepository.findByLogin(login).orElseThrow();
    }

    @Named("mapUpperReferralToLogin")
    public String mapUpperReferralByLogin(UserCredentials userCredentials) {
        return userCredentials.getLogin();
    }
}