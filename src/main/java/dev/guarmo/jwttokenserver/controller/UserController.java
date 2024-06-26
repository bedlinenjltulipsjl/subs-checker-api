package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.user.RoleStatus;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetContentWithoutHistoryUserDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.user.dto.PostUserDto;
import dev.guarmo.jwttokenserver.service.DepositService;
import dev.guarmo.jwttokenserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DepositService depositService;

    @GetMapping("/reflink/{userTelegramId}")
    public String generateReferralLinkForUser(@PathVariable String userTelegramId) {
        return userService.generateRefLinkForUser(userTelegramId);
    }

    @PostMapping("/register")
    public GetUserCredentialsDto addUser(@RequestBody PostUserDto postUserDto) {
        return userService.addUser(postUserDto, RoleStatus.USER);
    }

    @GetMapping("/reftree/{tgid}")
    public List<GetContentWithoutHistoryUserDto> getReferralsTree(@PathVariable String tgid) {
        return userService.getFourLevelsReferralTree(tgid);
    }

    @GetMapping("/{tgid}")
    public GetContentWithoutHistoryUserDto getContentUserDtoByTgId(@PathVariable String tgid) {
        return userService.findByLogin(tgid);
    }
}
