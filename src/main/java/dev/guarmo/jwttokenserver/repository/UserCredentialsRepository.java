package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, String> {
    Optional<UserCredentials> findByLogin(String login);

    Optional<UserCredentials> findByUserContentId(Long userContentId);
}
