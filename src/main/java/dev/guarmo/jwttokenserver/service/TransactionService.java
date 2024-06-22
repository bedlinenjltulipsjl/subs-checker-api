package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.model.transaction.dto.PostTransactionDto;
import dev.guarmo.jwttokenserver.model.transaction.mapper.TransactionMapper;
import dev.guarmo.jwttokenserver.model.user.UserContent;
import dev.guarmo.jwttokenserver.repository.TransactionRepository;
import dev.guarmo.jwttokenserver.repository.UserContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository tranRepo;
    private final TransactionMapper transactionMapper;
    private final UserContentRepository userContentRepo;

    public GetTransactionDto addTransactionToUser(MultiValueMap<String, String> formData) {
        // Take transaction values from data send to us from west wallet
        PostTransactionDto postTranDto = transactionMapper.toPostModel(formData);

        PayTransaction savedTran = tranRepo.save(transactionMapper.toModel(postTranDto));
        // We save in label user content details
        UserContent userContent = userContentRepo
                .findById(postTranDto.getLabel()).orElseThrow();

        List<PayTransaction> transactions = userContent.getTransactions();
        transactions.add(savedTran);
        userContent.setTransactions(transactions);

        userContentRepo.save(userContent);
        return transactionMapper.toGetDto(savedTran);
    }
}
