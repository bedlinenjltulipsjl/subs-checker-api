package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.security.TokenService;
import dev.guarmo.jwttokenserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }


}
