package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.service.TransactionService;
import dev.guarmo.jwttokenserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;

    @GetMapping("/reflink/{userTelegramId}")
    public String check1(@PathVariable String userTelegramId) {
        return userService.generateRefLinkForUser(userTelegramId);
    }

    @PostMapping("/register")
    public UserCredentials addUser(@RequestBody PostUserDto postUserDto) {
        return userService.addUser(postUserDto, RoleStatus.USER);
    }

    @GetMapping("/reftree")
    public String check2(Authentication authentication) {
        return "Hallo Numa Numa 2";
    }

//    @GetMapping("/{tgid}")
//    public GetContentUserDto check3(@PathVariable String tgid) {
//        return userService.findByLogin(tgid);
//    }
}
