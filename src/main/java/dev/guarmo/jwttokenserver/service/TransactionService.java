package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.model.transaction.dto.PostTransactionDto;
import dev.guarmo.jwttokenserver.model.transaction.mapper.TransactionMapper;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.TransactionRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository tranRepo;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public GetTransactionDto addTransactionToUser(MultiValueMap<String, String> formData) {
        // Take transaction values from data send to us from west wallet
        PostTransactionDto postTranDto = transactionMapper.toPostModel(formData);

        PayTransaction savedTran = tranRepo.save(transactionMapper.toModel(postTranDto));

        String userTgIdIdentifierInLabel = postTranDto.getLabel();
        UserCredentials userCredentials = userCredentialsRepository.findByLogin(userTgIdIdentifierInLabel).orElseThrow();

        List<PayTransaction> transactions = userCredentials.getTransactions();
        transactions.add(savedTran);
        userCredentials.setTransactions(transactions);

        userCredentialsRepository.save(userCredentials);
        return transactionMapper.toGetDto(savedTran);
    }

    public List<GetTransactionDto> findAllByIds(List<Long> transactionIds) {
        return transactionRepository.findAllById(transactionIds)
                .stream()
                .map(transactionMapper::toGetDto)
                .toList();
    }
}
