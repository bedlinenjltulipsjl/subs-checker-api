package dev.guarmo.jwttokenserver.service;

import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import dev.guarmo.jwttokenserver.model.purchase.dto.GetPurchaseDto;
import dev.guarmo.jwttokenserver.model.purchase.dto.PostPurchaseDto;
import dev.guarmo.jwttokenserver.model.purchase.mapper.PurchaseMapper;
import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.PurchaseRepository;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;
    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final UserCredentialsRepository userCredentialsRepository;

    public List<GetPurchaseDto> getPurchaseDtoList(String tgid) {
        return userService.findByLoginModel(tgid)
                .getPurchases()
                .stream()
                .map(purchaseMapper::toGetDto)
                .toList();
    }

    public GetPurchaseDto addPurchaseToUser(PostPurchaseDto dto, String tgid) {
        Purchase model = purchaseRepository.save(purchaseMapper.toModel(dto));

        UserCredentials byLoginModel = userService.findByLoginModel(tgid);
        byLoginModel.getPurchases().add(model);

        userCredentialsRepository.save(byLoginModel);
        return purchaseMapper.toGetDto(model);

    }
}
