package ua.org.javaday;

import ua.org.javaday.entity.Account;
import ua.org.javaday.util.data.TestDataGenerator;

import static ua.org.javaday.util.jpa.JpaUtil.close;
import static ua.org.javaday.util.jpa.JpaUtil.init;
import static ua.org.javaday.util.jpa.JpaUtil.performWithinPersistenceContext;

public class RemoveAndSaveBackEntityExample {

    public static void main(String[] args) {
        init();
        try {
            removeEntityAndSaveItBack();
        } finally {
            close();
        }
    }

    private static void removeEntityAndSaveItBack() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));

        performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.find(Account.class, account.getId());
            System.out.println("> Removing entity");
            entityManager.remove(managedAccount);
            System.out.println("> Persisting entity again");
            entityManager.persist(managedAccount);
        });
    }
}
