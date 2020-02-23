package ua.org.javaday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.org.javaday.entity.Account;
import ua.org.javaday.repository.AccountRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

//    @Transactional
    public void removeById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        System.out.printf("> Removing account: %s%n", account);
        System.out.println("> Expecting SELECT!");
        accountRepository.delete(account);
    }

//    @Transactional
    public void removeByIdWithHQL(Long id) {
        System.out.printf("> Removing account by id: %s with HQL query%n", id);
        accountRepository.deleteByIdWithHQL(id);
    }

//    @Transactional
    public void removeByIdWithCrudMethod(Long id) {
        System.out.printf("> Removing account by id: %s with CRUD method%n", id);
        accountRepository.deleteById(id);
        System.out.println("> Expecting SELECT!"); //Even under transaction!
    }

//    @Transactional
    public void updateFirstNameByIdWithFindBy(Long id, String firstName) {
        Account one = accountRepository.findById(id).orElseThrow();
        System.out.println("> Expecting SELECT!");

        one.setFirstName(firstName);
        accountRepository.save(one);
        System.out.println("> Ooops! Expecting one more SELECT!");

        System.out.printf("> Updated firstName %d %s as Entity %n", id, firstName);
    }

//    @Transactional
    public void updateFirstNameByIdWithGetOne(Long id, String firstName) {
        Account one = accountRepository.getOne(id);
        System.out.println("> Ooops! Got a proxy, not an object here!");
        one.setFirstName(firstName);

        accountRepository.save(one);
        System.out.printf("> Updated firstName %d %s as Entity %n", id, firstName);
    }

//    @Transactional
    public void updateFirstNameByIdWithHql(Long id, String firstName) {
        accountRepository.updateFirstNameById(id, firstName);
        System.out.printf("> Updated firstName %d %s with HQL query%n", id, firstName);
    }
}
