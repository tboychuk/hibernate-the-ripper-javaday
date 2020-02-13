package ua.org.javaday.completed;

import ua.org.javaday.entity.Account;
import ua.org.javaday.util.data.TestDataGenerator;
import ua.org.javaday.util.jpa.JpaUtil;

public class LoadEntityExample {

    public static void main(String[] args) {
        JpaUtil.init();
        try {
            loadEntityById();
        } finally {
            JpaUtil.close();
        }
    }

    private static void loadEntityById() {
        Long accountId = saveRandomAccount();
        Account loadedAccount = JpaUtil.performReturningWithinPersistenceContext(entityManager -> entityManager.find(Account.class, accountId));
        System.out.printf("> Loaded account: %s%n", loadedAccount);
    }

    private static Long saveRandomAccount() {
        Account account = TestDataGenerator.generateAccount();
        JpaUtil.performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        return account.getId();
    }
}
