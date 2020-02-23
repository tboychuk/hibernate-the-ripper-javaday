package ua.org.javaday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/findby/{id}")
    public void updateAccountWithFindBy(@PathVariable Long id, @RequestParam String firstName) {
        accountService.updateFirstNameByIdWithFindBy(id, firstName);
    }

    @PutMapping("/getone/{id}")
    public void updateAccountWithGetOne(@PathVariable Long id, @RequestParam String firstName) {
        accountService.updateFirstNameByIdWithGetOne(id, firstName);
    }

    @PutMapping("/hql/{id}")
    public void updateAccountWithHQL(@PathVariable Long id, @RequestParam String firstName) {
        accountService.updateFirstNameByIdWithHql(id, firstName);
    }
}
