package dev.guarmo.jwttokenserver.model.income.mapper;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeMapperHelper {
    private final UserCredentialsRepository userCredentialsRepository;

    @Named("createUserFromLogin")
    public UserCredentials createUserFromLogin(final String userLogin) {
        return userCredentialsRepository.findByLogin(userLogin).orElseThrow();
    }
}
