package dev.guarmo.jwttokenserver.model.user.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GetUserDto {
    private String id;
    private String login;
    private String balance;
    private List<Long> transactions;
}
