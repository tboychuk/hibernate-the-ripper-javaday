package ua.org.javaday.completed;

import ua.org.javaday.entity.Account;
import ua.org.javaday.util.data.TestDataGenerator;

import static ua.org.javaday.util.jpa.JpaUtil.close;
import static ua.org.javaday.util.jpa.JpaUtil.init;
import static ua.org.javaday.util.jpa.JpaUtil.performWithinPersistenceContext;

public class UpdateEntityExample {

    public static void main(String[] args) {
        init();
        try {
            updateEntity();
        } finally {
            close();
        }
    }

    private static void updateEntity() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        System.out.printf("> Persisted account: %s%n", account);

        performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.find(Account.class, account.getId());
            managedAccount.setLastName("X-Man");
            System.out.printf("> Updated account: %s%n", managedAccount);
        });
    }
}
