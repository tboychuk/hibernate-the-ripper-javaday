package ua.org.javaday;

import ua.org.javaday.entity.Account;
import ua.org.javaday.util.data.TestDataGenerator;

import static ua.org.javaday.util.jpa.JpaUtil.close;
import static ua.org.javaday.util.jpa.JpaUtil.init;
import static ua.org.javaday.util.jpa.JpaUtil.performWithinPersistenceContext;

public class RemoveEntityExample {

    public static void main(String[] args) {
        init();
        try {
            removeEntity();
        } finally {
            close();
        }
    }

    private static void removeEntity() {
        Account account = saveRandomAccount();
        performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.merge(account);
            entityManager.remove(managedAccount);
        });
    }

    private static Account saveRandomAccount() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        System.out.printf("> Persisted account: %s%n", account);
        return account;
    }
}
