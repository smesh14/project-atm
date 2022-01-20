package egc.atmservice.cardOperationServices;

import egc.atmservice.model.AuthResult;
import egc.atmservice.model.AuthRequest;



public interface AuthService {
    AuthResult auth(AuthRequest cardAuthRequest);
}
