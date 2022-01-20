package egc.atmservice.controller;

import egc.atmservice.cardOperationServices.CardOperationService;
import egc.atmservice.model.TransactionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Operation Controller")
@RestController
public class OperationControllerBean implements  OperationController{

    @Autowired
    private CardOperationService cardOperationService;

    @Override
    public String checkBalance() {
        return cardOperationService.checkBalance();
    }

    @Override
    public TransactionResponse deposit(long amount) {
        return cardOperationService.deposit(amount);
    }

    @Override
    public TransactionResponse withdrawal(long amount) {
        return  cardOperationService.withdrawal(amount);
    }




}
