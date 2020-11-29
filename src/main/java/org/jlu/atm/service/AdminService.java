package org.jlu.atm.service;

import org.jlu.atm.mapper.AdminAccountMapper;
import org.jlu.atm.mapper.UserAccountMapper;
import org.jlu.atm.mapper.UserInfoMapper;
import org.jlu.atm.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private AdminAccountMapper adminAccountMapper;
    private UserAccountMapper userAccountMapper;

    @Autowired
    public void setAdminAccountMapper(AdminAccountMapper adminAccountMapper) {
        this.adminAccountMapper = adminAccountMapper;
    }

    @Autowired
    public void setUserAccountMapper(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
    }

    //管理员登录 卡号不存在返回1 密码不正确返回2 成功返回0
    public int adminLogIn(String username, String password) {
        if (!adminAccountMapper.existsByUsername(username)) return 1;
        if (!adminAccountMapper.findPasswordByUsername(password).equals(password)) return 2;
        return 0;
    }

    //更改用户密码 查不到返回1 旧密码不符返回2 成功返回0
    public int changeUserPassword(String cardID, String previous, String current) {
        Optional<UserAccount> userAccount = userAccountMapper.findByCardID(cardID);
        if (userAccount.isEmpty()) return 1;
        if (!userAccount.get().getPassword().equals(previous)) return 2;
        userAccount.get().setPassword(current);
        userAccountMapper.save(userAccount.get());
        return 0;
    }

}
