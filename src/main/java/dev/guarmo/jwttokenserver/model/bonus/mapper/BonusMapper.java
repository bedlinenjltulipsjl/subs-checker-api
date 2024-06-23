package dev.guarmo.jwttokenserver.model.bonus.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import dev.guarmo.jwttokenserver.model.purchase.dto.GetPurchaseDto;
import dev.guarmo.jwttokenserver.model.purchase.dto.PostPurchaseDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BonusMapper {
    GetPurchaseDto toGetDto(Purchase user);

    Purchase toModel(PostPurchaseDto crmUserDto);
}
