package engine.nexus.service;

import lombok.extern.slf4j.Slf4j;

import engine.nexus.model.Account;
import engine.nexus.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(String holderName, String currency) {
        log.info("Creating new account for holder: {} with currency: {}", holderName, currency);
        Account account = Account.builder()
                .accountId(UUID.randomUUID())
                .holderName(holderName)
                .balance(BigDecimal.ZERO)
                .currency(currency)
                .status(Account.AccountStatus.ACTIVE)
                .build();
        return accountRepository.save(account);
    }

    public Account getAccount(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
