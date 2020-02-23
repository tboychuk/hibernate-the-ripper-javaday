package ua.org.javaday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.javaday.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("update Account set firstName = :name where id = :id")
    void updateFirstNameById(@Param("id") long id, @Param("name") String name);

    void deleteById(long id);

    @Modifying
    @Query("delete from Account where id =:id")
    void deleteByIdWithHQL(@Param("id") long id);
}
