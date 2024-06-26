package dev.guarmo.jwttokenserver.model.deposit.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.deposit.Deposit;
import dev.guarmo.jwttokenserver.model.deposit.dto.GetDepositDto;
import dev.guarmo.jwttokenserver.model.deposit.dto.PostDepositDto;
import org.mapstruct.Mapper;
import org.springframework.util.MultiValueMap;

@Mapper(config = MapperConfig.class)
public interface DepositMapper {
    default PostDepositDto toPostModel(MultiValueMap<String, String> formData) {
        PostDepositDto notification = new PostDepositDto();
        notification.setTransactionId(Long.parseLong(formData.getFirst("id")));
        notification.setAmount(Double.parseDouble(formData.getFirst("amount")));
        notification.setAddress(formData.getFirst("address"));
        notification.setDestTag(formData.getFirst("dest_tag"));
        notification.setLabel(formData.getFirst("label"));
        notification.setCurrency(formData.getFirst("currency"));
        notification.setStatus(formData.getFirst("status"));
        notification.setBlockchainConfirmations(Integer.parseInt(formData.getFirst("blockchain_confirmations")));
        notification.setFee(formData.getFirst("fee"));
        notification.setBlockchainHash(formData.getFirst("blockchain_hash"));
        return notification;
    }

    GetDepositDto toGetDto(Deposit user);

    Deposit toModel(PostDepositDto crmUserDto);
}
