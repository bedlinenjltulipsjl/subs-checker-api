package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.invoice.dto.GenerateInvoiceDto;
import dev.guarmo.jwttokenserver.model.invoice.dto.GetInvoiceDto;
import dev.guarmo.jwttokenserver.model.deposit.dto.GetDepositDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.service.InvoiceGeneratorService;
import dev.guarmo.jwttokenserver.service.DepositService;
import dev.guarmo.jwttokenserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposits")
@CrossOrigin(allowedHeaders = "*")
public class DepositController {
    private final InvoiceGeneratorService invoiceGeneratorService;
    private final UserService userService;
    private final DepositService depositService;

    @GetMapping("/{tgid}")
    public List<GetDepositDto> getAllTransactionsFromUser(@PathVariable String tgid) {
        return depositService.findAllTransactionsByLogin(tgid);
    }

    @PostMapping("/getlink")
    public GetInvoiceDto generateInvoiceLink(@RequestBody GenerateInvoiceDto generateInvoiceDto) {
        GetUserCredentialsDto userCredentialsDto = userService.getByCredentialsByLogin(generateInvoiceDto.getLogin());
        return invoiceGeneratorService.generateInvoicePageObject(
                userCredentialsDto.getLogin(),
                generateInvoiceDto.getCurrencyCode(),
                generateInvoiceDto.getAmount(),
                generateInvoiceDto.getEmail()
        );
    }
}
