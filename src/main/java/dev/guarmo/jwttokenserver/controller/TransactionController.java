package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.invoice.dto.GenerateInvoiceDto;
import dev.guarmo.jwttokenserver.model.invoice.dto.GetInvoiceDto;
import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.model.user.dto.GetUserCredentialsDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.PostWithdrawDto;
import dev.guarmo.jwttokenserver.service.InvoiceGeneratorService;
import dev.guarmo.jwttokenserver.service.TransactionService;
import dev.guarmo.jwttokenserver.service.UserService;
import dev.guarmo.jwttokenserver.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tran")
public class TransactionController {
    private final InvoiceGeneratorService invoiceGeneratorService;
    private final UserService userService;
    private final WithdrawService withdrawService;

    @GetMapping("/{tgid}")
    public List<GetTransactionDto> getAllTransactionsFromUser(@PathVariable String tgid) {
        return userService.findAllTransactionsByLogin(tgid);
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
