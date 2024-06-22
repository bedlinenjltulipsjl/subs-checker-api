package dev.guarmo.jwttokenserver.controller;

import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tran")
public class TransactionController {
    private final TransactionService transactionService;

    public List<GetTransactionDto> getAllTransactionsByIds(List<Long> transactionIds) {
        return null;
    }

    public String generateInvoiceLink(String telegramId) {
        return null;
    }
}
