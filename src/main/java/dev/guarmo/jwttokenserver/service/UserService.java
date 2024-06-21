package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCredentialsRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserCredentials addUser(PostUserDto user, RoleStatus role) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
