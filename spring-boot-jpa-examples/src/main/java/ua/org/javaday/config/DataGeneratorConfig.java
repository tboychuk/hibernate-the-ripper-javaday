package ua.org.javaday.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import ua.org.javaday.entity.Account;
import ua.org.javaday.repository.AccountRepository;
import ua.org.javaday.util.data.TestDataGenerator;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataGeneratorConfig implements InitializingBean {
    private final AccountRepository accountRepository;

    @Override
    public void afterPropertiesSet() {
        List<Account> accountList = TestDataGenerator.generateAccountList(10);
        accountRepository.saveAll(accountList);
    }
}
