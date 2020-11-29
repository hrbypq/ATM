package org.jlu.atm.service;

import org.jlu.atm.mapper.UserAccountMapper;
import org.jlu.atm.mapper.UserInfoMapper;
import org.jlu.atm.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserAccountMapper userAccountMapper;
    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserAccountMapper(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    //用户登录 卡号不存在返回1 密码不正确返回2 成功返回0
    public int userLogIn(String cardID, String password) {
        if (!userAccountMapper.existsByCardID(cardID)) return 1;
        if (!userAccountMapper.findPasswordByCardID(cardID).equals(password)) return 2;
        return 0;
    }

    public Optional<UserInfo> getUserInfo(String cardID){
        return userInfoMapper.existsByCardID(cardID)?userInfoMapper.findByCardID(cardID):Optional.empty();
    }

    //使用卡号查询余额
    public double getBalance(String cardID) {
        return userInfoMapper.findBalanceByCardID(cardID);
    }

    //余额变化 查不到卡号返回false 成功返回true
    private boolean changeBalance(String cardID, double amount) {
        Optional<UserInfo> userinfo = userInfoMapper.findByCardID(cardID);
        if (userinfo.isEmpty()) return false;
        double previous = userinfo.get().getBalance();
        userinfo.get().setBalance(previous + amount);
        userInfoMapper.save(userinfo.get());
        return true;
    }

    //存款 存款额小于或等于零返回1 查不到返回2 成功返回0
    public int deposit(String cardID, double amount) {
        if (amount <= 0) return 1;
        if (!this.changeBalance(cardID, amount)) return 2;
        return 0;
    }

    //取款 取款额取正数 取款额小于或等于零返回1 当前余额小于取款额返回2 查不到返回3 成功返回0
    public int withdraw(String cardID, double amount) {
        if (amount <= 0) return 1;
        if (userInfoMapper.findBalanceByCardID(cardID) < amount) return 2;
        if (!this.changeBalance(cardID, -1 * amount)) return 3;
        return 0;
    }

    //转账 转账额取正数 转账额小于或等于零返回1 当前余额小于转账额返回2 汇款方查不到返回3 收款方查不到返回4 成功返回0
    public int transfer(String cardID, String targetCardID, double amount) {
        if (amount <= 0) return 1;
        if (userInfoMapper.findBalanceByCardID(cardID) < amount) return 2;
        if (!this.changeBalance(cardID, -1 * amount)) return 3;
        if (!this.changeBalance(targetCardID, amount)) return 4;
        return 0;
    }
}
