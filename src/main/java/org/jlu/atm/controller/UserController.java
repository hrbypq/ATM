package org.jlu.atm.controller;

import org.jlu.atm.pojo.UserInfo;
import org.jlu.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userLogIn/{cardID}/{password}")
    public int userLogIn(@PathVariable String cardID, @PathVariable String password) {
        return userService.userLogIn(cardID, password);
    }

    @GetMapping("/getUserInfo/{cardID}")
    public UserInfo getUserInfo(@PathVariable String cardID){
        Optional<UserInfo> userInfo = userService.getUserInfo(cardID);
        return userInfo.orElse(null);
    }

    @GetMapping("/getBalance/{cardID}")
    public double getBalance(@PathVariable String cardID) {
        return userService.getBalance(cardID);
    }

    @GetMapping("/deposit/{cardID}/{amount}")
    public int deposit(@PathVariable String cardID, @PathVariable double amount) {
        return userService.deposit(cardID, amount);
    }

    @GetMapping("/withdraw/{cardID}/{amount}")
    public int withdraw(@PathVariable String cardID, @PathVariable double amount) {
        return userService.withdraw(cardID, amount);
    }

    @GetMapping("/transfer/{cardID}/{targetCardID}/{amount}")
    public int transfer(@PathVariable String cardID, @PathVariable String targetCardID,
                        @PathVariable double amount) {
        return userService.transfer(cardID, targetCardID, amount);
    }

}
