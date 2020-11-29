package org.jlu.atm.mapper;

import org.jlu.atm.pojo.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminAccountMapper extends JpaRepository<AdminAccount, Integer> {
    @Query("select password from AdminAccount where username=?1")
    String findPasswordByUsername(String username);

    boolean existsByUsername(String username);
}
