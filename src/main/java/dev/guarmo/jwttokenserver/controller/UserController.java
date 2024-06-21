package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @GetMapping("reflink")
    public String check1(Authentication authentication) {
        return "Hallo Numa Numa 1";
    }

    @PostMapping("/register")
    public UserCredentials addUser(@RequestBody PostUserDto postUserDto) {
        return null;
//        return userService.addUser(userCredentials);
    }

    @GetMapping("/reftree")
    public String check2(Authentication authentication) {
        return "Hallo Numa Numa 2";
    }
}
