package egc.atmservice.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    private String cardNumber;

    private String pin;

}
