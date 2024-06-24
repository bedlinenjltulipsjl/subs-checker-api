package dev.guarmo.jwttokenserver.model.user.dto;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GetUserWithReferralsDto {
    private String id;
    private String login;
    private String name;
    private String username;
    private Double balanceAmount;
    private List<Long> depositIds;
    private List<Long> withdrawIds;
    private List<Long> incomeIds;
    private List<Long> purchaseIds;
    private UserCredentials upperReferral;
    private List<UserCredentials> bottomReferral;
}
