package org.jlu.atm.mapper;

import org.jlu.atm.pojo.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountMapper extends JpaRepository<UserAccount, Integer> {
    boolean existsByCardID(String cardID);

    @Query("select password from UserAccount where cardID=?1")
    String findPasswordByCardID(String cardID);

    Optional<UserAccount> findByCardID(String cardID);
}
