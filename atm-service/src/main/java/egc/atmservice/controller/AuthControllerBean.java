package egc.atmservice.controller;

import egc.atmservice.cardOperationServices.AuthService;
import egc.atmservice.model.AuthResult;
import egc.atmservice.model.AuthRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller")
@RestController
public class AuthControllerBean implements AuthController{

    @Autowired
    private AuthService cardAuth;

    @Override
    public ResponseEntity<AuthResult> auth(AuthRequest cardAuthRequest) {
        return  ResponseEntity.ok(cardAuth.auth(cardAuthRequest));
    }
}
