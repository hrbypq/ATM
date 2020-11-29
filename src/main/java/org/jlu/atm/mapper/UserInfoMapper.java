package org.jlu.atm.mapper;

import org.jlu.atm.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoMapper extends JpaRepository<UserInfo, Integer> {
    @Query("select balance from UserInfo where cardID=?1")
    double findBalanceByCardID(String cardID);

    Optional<UserInfo> findByCardID(String cardID);

    boolean existsByCardID(String cardID);
}
