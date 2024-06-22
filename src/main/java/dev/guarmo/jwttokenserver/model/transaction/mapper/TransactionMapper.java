package dev.guarmo.jwttokenserver.model.transaction.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import dev.guarmo.jwttokenserver.model.transaction.dto.GetTransactionDto;
import dev.guarmo.jwttokenserver.model.transaction.dto.PostTransactionDto;
import org.mapstruct.Mapper;
import org.springframework.util.MultiValueMap;

@Mapper(config = MapperConfig.class)
public interface TransactionMapper {
    default PostTransactionDto toPostModel(MultiValueMap<String, String> formData) {
        PostTransactionDto notification = new PostTransactionDto();
        notification.setTransactionId(Long.parseLong(formData.getFirst("id")));
        notification.setAmount(Double.parseDouble(formData.getFirst("amount")));
        notification.setAddress(formData.getFirst("address"));
        notification.setDestTag(formData.getFirst("dest_tag"));
        notification.setLabel(Long.parseLong(formData.getFirst("label")));
        notification.setCurrency(formData.getFirst("currency"));
        notification.setStatus(formData.getFirst("status"));
        notification.setBlockchainConfirmations(Integer.parseInt(formData.getFirst("blockchain_confirmations")));
        notification.setFee(formData.getFirst("fee"));
        notification.setBlockchainHash(formData.getFirst("blockchain_hash"));
        return notification;
    }

    GetTransactionDto toGetDto(PayTransaction user);

    PayTransaction toModel(PostTransactionDto crmUserDto);
}
