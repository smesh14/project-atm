package egc.atmservice.cardOperationServices;


import egc.atmservice.configuration.ExceptionHandler;
import egc.atmservice.exception.AtmException;
import egc.atmservice.model.*;
import egc.atmservice.configuration.ValidateCard.ValidatedCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

import static egc.atmservice.configuration.ConstantVariables.*;

@Service
@Slf4j
public class BankService {

    private final ValidatedCard validatedCard;


    public BankService(ValidatedCard validatedCard) {
        this.validatedCard = validatedCard;
    }

    private RestTemplate getRestTemplate(){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        restTemplate.setErrorHandler(new ExceptionHandler());
        return restTemplate;
    }


    public AuthResult authCard(AuthRequest cardAuth) {
        RestTemplate restTemplate = getRestTemplate();
        String AUTH = "/auth/authCard";
        String url = ADDRESS + AUTH;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<AuthRequest> entity = new HttpEntity<>(cardAuth, headers);
        ResponseEntity<AuthResult> response = restTemplate.postForEntity(url, entity, AuthResult.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("card authenticated successfully");
            String jwt = Objects.requireNonNull(response.getBody()).getJwt();
            validatedCard.setJwtToken(jwt);
            validatedCard.setCardNumber(cardAuth.getCardNumber());
            return response.getBody();
        } else {
            log.error("authentication failed");
            throw new AtmException("authentication failed");
        }
    }


    public String checkBalance() {
        RestTemplate restTemplate = getRestTemplate();
        String url = ADDRESS + BALANCE;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jwt = validatedCard.getJwtToken();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            log.error("can't check balance");
            throw new AtmException("can't check balance");
        }

    }

    public TransactionResponse makeTransaction(long amount, TransactionType transactionType) {
        RestTemplate restTemplate = getRestTemplate();
        String url = "";
        switch (transactionType){
            case DEPOSIT: url = ADDRESS + DEPOSIT;
                break;
            case WITHDRAWAL: url = ADDRESS + WITHDRAWAL;
                break;
        }
        TransactionParams transactionParams = new TransactionParams();
        transactionParams.setAmount(amount);
        transactionParams.setCardNumber(validatedCard.getCardNumber());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String jwt = validatedCard.getJwtToken();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        HttpEntity<TransactionParams> entity = new HttpEntity<>(transactionParams, headers);
        ResponseEntity<TransactionResponse> response = restTemplate.postForEntity(url, entity, TransactionResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            if(Objects.requireNonNull(response.getBody()).getCode()!=SuccessCode.OK){
                log.info("operation failed by reason :"+response.getBody().getErrorMessage()+"");
                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setCode(SuccessCode.ERROR);
                transactionResponse.setErrorMessage(response.getBody().getErrorMessage());
                return transactionResponse;
            }
            return response.getBody();
        } else return null;
    }

}
