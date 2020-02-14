package ua.org.javaday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.javaday.entity.Account;
import ua.org.javaday.repository.AccountRepository;
import ua.org.javaday.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void removeAccount(@PathVariable Long id) {
        accountService.removeById(id);
    }
}
