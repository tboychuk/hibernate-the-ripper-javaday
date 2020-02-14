package ua.org.javaday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.org.javaday.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
