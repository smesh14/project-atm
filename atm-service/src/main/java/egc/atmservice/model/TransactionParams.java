package egc.atmservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionParams {

    private String cardNumber;

    private long amount;
}
