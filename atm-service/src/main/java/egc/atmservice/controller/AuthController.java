package egc.atmservice.controller;

import egc.atmservice.model.AuthResult;
import egc.atmservice.model.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping(path="api/atm/auth")
public interface AuthController {
    @PostMapping("login/")
    ResponseEntity<AuthResult> auth(@RequestBody AuthRequest cardAuthRequest) ;
}
