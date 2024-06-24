package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.purchase.dto.GetPurchaseDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.service.PurchaseService;
import dev.guarmo.jwttokenserver.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/{tgid}")
    public List<GetPurchaseDto> getPurchaseDtoList(@PathVariable String tgid) {
        return purchaseService.getPurchaseDtoList(tgid);
    }
}
