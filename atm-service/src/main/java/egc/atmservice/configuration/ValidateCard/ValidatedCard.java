package egc.atmservice.configuration.ValidateCard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatedCard {
    private String CardNumber;
    private String jwtToken;
}
