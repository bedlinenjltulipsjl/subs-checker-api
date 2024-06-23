package dev.guarmo.jwttokenserver.model.purchase.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import dev.guarmo.jwttokenserver.model.purchase.dto.GetPurchaseDto;
import dev.guarmo.jwttokenserver.model.purchase.dto.PostPurchaseDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface PurchaseMapper {
    GetPurchaseDto toGetDto(Purchase user);

    Purchase toModel(PostPurchaseDto crmUserDto);
}
