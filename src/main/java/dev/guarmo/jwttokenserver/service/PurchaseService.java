package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.purchase.dto.GetPurchaseDto;
import dev.guarmo.jwttokenserver.model.purchase.mapper.PurchaseMapper;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final PurchaseMapper purchaseMapper;

    public List<GetPurchaseDto> getPurchaseDtoList(String tgid) {
        return userCredentialsRepository.findByLogin(tgid).orElseThrow()
                .getPurchases()
                .stream()
                .map(purchaseMapper::toGetDto)
                .toList();
    }
}
