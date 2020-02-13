package ua.org.javaday.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import ua.org.javaday.repository.AccountRepository;
import ua.org.javaday.util.data.TestDataGenerator;

@Configuration
@RequiredArgsConstructor
public class DataGeneratorConfig implements InitializingBean {
    private final AccountRepository accountRepository;

    @Override
    public void afterPropertiesSet() {
        accountRepository.saveAll(TestDataGenerator.generateAccountList(10));
    }
}
