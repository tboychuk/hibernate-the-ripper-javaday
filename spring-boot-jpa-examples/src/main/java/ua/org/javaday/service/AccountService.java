package ua.org.javaday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.org.javaday.entity.Account;
import ua.org.javaday.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

//    @Transactional
    public void removeAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow();
        System.out.printf("> Removing account: %s%n", account);
        System.out.println("> Expecting SELECT");
        accountRepository.delete(account);
    }
}
