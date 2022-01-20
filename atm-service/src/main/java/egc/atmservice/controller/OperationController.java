package egc.atmservice.controller;

import egc.atmservice.model.TransactionResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RequestMapping(path="api/atm/operations")
@SecurityRequirement(name = "bearerAuth")
public interface OperationController {

    @GetMapping("checkBalance/")
    String checkBalance();


    @PostMapping("deposit/")
    TransactionResponse deposit(@RequestParam long amount);


    @PostMapping("withdrawal/")
    TransactionResponse withdrawal(@RequestParam long amount);
}
