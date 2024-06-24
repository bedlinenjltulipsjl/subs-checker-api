package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.deposit.mapper.DepositMapper;
import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.model.user.mapper.UserMapper;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCredentialsRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final DepositMapper depositMapper;
    private final UserCredentialsRepository userCredentialsRepository;
    @Value("${bot.link}")
    private String botLink;

    public GetUserCredentialsDto addUser(PostUserDto user, RoleStatus role) {
        UserCredentials model = userMapper.toModel(user);
        model.setRole(role);
        model.setPassword(passwordEncoder.encode(user.getPassword()));

        UserCredentials save = repository.save(model);
        return userMapper.toGetCredentialsDto(model);
    }

    public GetUserCredentialsDto getByCredentialsByLogin(String login) {
        UserCredentials userCredentials = repository.findByLogin(login).orElseThrow();
        return userMapper.toGetCredentialsDto(userCredentials);
    }

    public String generateRefLinkForUser(String userTelegramId) {
        return botLink + Base64.getUrlEncoder().encodeToString(userTelegramId.getBytes(StandardCharsets.UTF_8));
    }

    public GetContentUserDto findByLogin(String tgid) {
        UserCredentials userCredentials = userCredentialsRepository.findByLogin(tgid).orElseThrow();
        return userMapper.toGetDto(userCredentials);
    }

    public UserCredentials findByLoginModel(String tgid) {
        return userCredentialsRepository.findByLogin(tgid).orElseThrow();
    }
}
