package com.pdurasek.demo.repository;

import com.pdurasek.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    List<UserAccount> findAll();

    UserAccount findById(int id);

    @Query(value = "SELECT user_account.*, SUM(rr.overdue_days) FROM user_account " +
            "LEFT OUTER JOIN rent_record rr ON user_account.id = rr.user_account_id " +
            "WHERE (rr.returned_date IS NULL) " +
            "AND rr.overdue_days > 0 " +
            "GROUP BY user_account.id " +
            "ORDER BY SUM(rr.overdue_days) DESC", nativeQuery = true)
    List<UserAccount> findByOverdue();
}
