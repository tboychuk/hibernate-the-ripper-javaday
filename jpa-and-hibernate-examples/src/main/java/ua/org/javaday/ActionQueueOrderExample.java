package ua.org.javaday;

import ua.org.javaday.entity.Account;
import ua.org.javaday.util.data.TestDataGenerator;
import ua.org.javaday.util.jpa.JpaUtil;

// Won't be presented during the talk
public class ActionQueueOrderExample {

    public static void main(String[] args) {
        JpaUtil.init();
        try {
            removeEntityAndSameAnotherWithSameEmail();
        } finally {
            JpaUtil.close();
        }
    }

    private static void removeEntityAndSameAnotherWithSameEmail() {
        Account account = TestDataGenerator.generateAccount();
        JpaUtil.performWithinPersistenceContext(entityManager -> entityManager.persist(account));

        JpaUtil.performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.merge(account);
            System.out.printf("> Removing entity: %s%n", managedAccount);
            entityManager.remove(managedAccount);
            Account newAccount = TestDataGenerator.generateAccount();
            newAccount.setEmail(account.getEmail());
            System.out.printf("> Persisting entity: %s%n", newAccount);
            entityManager.persist(newAccount);
        });
    }
}
