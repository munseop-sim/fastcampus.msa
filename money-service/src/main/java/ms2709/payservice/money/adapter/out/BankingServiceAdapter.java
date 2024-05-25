package ms2709.payservice.money.adapter.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.GlobalHttpClient;
import ms2709.payservice.money.application.port.out.GetRegisteredBankAccountPort;
import ms2709.payservice.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankingServiceAdapter implements GetRegisteredBankAccountPort {
    private final GlobalHttpClient globalHttpClient;
    private final String bankingServiceUrl;
    private final ObjectMapper objectMapper;

    public BankingServiceAdapter(GlobalHttpClient globalHttpClient,
                                 @Value("${service.banking.url}") String membershipServiceUrl,
                                 ObjectMapper objectMapper) {
        this.globalHttpClient = globalHttpClient;
        this.bankingServiceUrl = membershipServiceUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    public RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "banking/account", membershipId);
        log.info("url: {}", url);
        try {
            String jsonResponse = globalHttpClient.sendGetRequest(url).body();
            // json RegisteredBankAccount
            log.info("jsonResponse: {}", jsonResponse);
            ObjectMapper mapper = new ObjectMapper();
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisteredBankAccountAggregateIdentifier(
                    registeredBankAccount.getRegisteredBankAccountId()
                    , registeredBankAccount.getAggregateIdentifier()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankAccountNumber()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
