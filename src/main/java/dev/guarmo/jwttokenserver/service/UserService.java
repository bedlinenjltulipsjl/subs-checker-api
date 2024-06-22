package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.UserContent;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.model.user.mapper.UserMapper;
import dev.guarmo.jwttokenserver.repository.UserContentRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCredentialsRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserContentRepository userContentRepository;

    public UserCredentials addUser(PostUserDto user, RoleStatus role) {
        UserCredentials model = userMapper.toModel(user);
        model.setRole(role);
        model.setPassword(passwordEncoder.encode(user.getPassword()));

//        UserContent savedUserContent = userContentRepository.save(new UserContent());
//        model.setUserContent(savedUserContent);
        return repository.save(model);
    }
}
