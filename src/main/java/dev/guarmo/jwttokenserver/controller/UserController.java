package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/reflink/{userTelegramId}")
    public String check1(@PathVariable String userTelegramId) {
        return "Hallo Numa Numa 1";
    }

    @PostMapping("/register")
    public UserCredentials addUser(@RequestBody PostUserDto postUserDto) {
        return userService.addUser(postUserDto, RoleStatus.USER);
    }

    @GetMapping("/reftree")
    public String check2(Authentication authentication) {
        return "Hallo Numa Numa 2";
    }
}
