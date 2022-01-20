package egc.atmservice.cardOperationServices;

import egc.atmservice.configuration.ValidateCard.ValidatedCard;
import egc.atmservice.model.AuthResult;
import egc.atmservice.model.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceBean implements AuthService {

    @Autowired
    private final ValidatedCard validatedCard;

    public AuthServiceBean(ValidatedCard validatedCard) {
        this.validatedCard = validatedCard;
    }

    @Override
    public AuthResult auth(AuthRequest cardAuthRequest) {
        BankService restTemplate = new BankService(validatedCard);
        return restTemplate.authCard(cardAuthRequest);
    }
}
