package dev.guarmo.jwttokenserver.model.withdraw.mapper;

import dev.guarmo.jwttokenserver.config.MapperConfig;
import dev.guarmo.jwttokenserver.model.user.mapper.UserMapperHelper;
import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import dev.guarmo.jwttokenserver.model.withdraw.dto.GetWithdrawDto;
import dev.guarmo.jwttokenserver.model.withdraw.dto.PostWithdrawDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface WithdrawMapper {
    GetWithdrawDto toGetDto(MoneyWithdraw moneyWithdraw);

    MoneyWithdraw toModel(PostWithdrawDto postWithdrawDto);
}
