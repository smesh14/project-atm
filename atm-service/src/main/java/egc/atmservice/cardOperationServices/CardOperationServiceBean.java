package egc.atmservice.cardOperationServices;

import egc.atmservice.model.TransactionResponse;
import egc.atmservice.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardOperationServiceBean implements CardOperationService {


    private final String checkBalance = "http://192.168.2.136:8080/api/bank/operations/checkbalance";
    private final String deposit = "http://192.168.2.136:8080/api/bank/operations/deposit";
    private final String withdrawal = "http://192.168.2.136:8080/api/bank/operations/withdrawal";


    @Autowired
    private BankService restOperation;




    @Override
    public TransactionResponse deposit(long amount) {
        return restOperation.makeTransaction(amount, TransactionType.DEPOSIT);
    }

    @Override
    public String checkBalance() {
        return restOperation.checkBalance();
    }

    @Override
    public TransactionResponse withdrawal(long amount) {
        return restOperation.makeTransaction(amount, TransactionType.WITHDRAWAL);
    }


}
