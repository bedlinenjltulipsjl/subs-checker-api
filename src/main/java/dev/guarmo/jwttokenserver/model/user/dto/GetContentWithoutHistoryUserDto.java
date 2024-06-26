package dev.guarmo.jwttokenserver.model.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetContentWithoutHistoryUserDto {
    private String id;
    private String login;
    private String name;
    private String username;
    private Double balanceAmount;
}
