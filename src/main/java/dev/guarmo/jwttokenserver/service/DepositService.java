package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.deposit.Deposit;
import dev.guarmo.jwttokenserver.model.deposit.dto.GetDepositDto;
import dev.guarmo.jwttokenserver.model.deposit.dto.PostDepositDto;
import dev.guarmo.jwttokenserver.model.deposit.mapper.DepositMapper;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.DepositRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository tranRepo;
    private final DepositMapper depositMapper;
    private final DepositRepository depositRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public GetDepositDto addTransactionToUser(MultiValueMap<String, String> formData) {
        // Take transaction values from data send to us from west wallet
        PostDepositDto postTranDto = depositMapper.toPostModel(formData);

        Deposit savedTran = tranRepo.save(depositMapper.toModel(postTranDto));

        String userTgIdIdentifierInLabel = postTranDto.getLabel();
        UserCredentials userCredentials = userCredentialsRepository.findByLogin(userTgIdIdentifierInLabel).orElseThrow();

        List<Deposit> transactions = userCredentials.getDeposits();
        transactions.add(savedTran);
        userCredentials.setDeposits(transactions);

        userCredentialsRepository.save(userCredentials);
        return depositMapper.toGetDto(savedTran);
    }

    public List<GetDepositDto> findAllByIds(List<Long> transactionIds) {
        return depositRepository.findAllById(transactionIds)
                .stream()
                .map(depositMapper::toGetDto)
                .toList();
    }

    public List<GetDepositDto> findAllTransactionsByLogin(String login) {
        UserCredentials userCredentials = userCredentialsRepository.findByLogin(login).orElseThrow();
        return userCredentials.getDeposits().stream().map(depositMapper::toGetDto).toList();
    }
}
