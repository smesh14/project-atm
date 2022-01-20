package egc.atmservice.cardOperationServices;

import egc.atmservice.model.TransactionResponse;

public interface CardOperationService {
    TransactionResponse deposit(long amount);
    String checkBalance();
    TransactionResponse withdrawal(long amount);

}
