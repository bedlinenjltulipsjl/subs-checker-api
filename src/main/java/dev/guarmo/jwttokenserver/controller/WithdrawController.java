package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.PostWithdrawDto;
import dev.guarmo.jwttokenserver.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/withdraws")
public class WithdrawController {
    private final WithdrawService withdrawService;

    @PostMapping("/{tgid}")
    public GetWithdrawDto generateInvoiceLink(@RequestBody PostWithdrawDto postWithdrawDto, @PathVariable String tgid) {
        return withdrawService.addWithdrawRequest(postWithdrawDto, tgid);
    }

    @GetMapping("/{tgid}")
    public List<GetWithdrawDto> getWithdrawsOfUser(@PathVariable String tgid) {
        return withdrawService.getWithdrawsOfUserByLogin(tgid);
    }
}
