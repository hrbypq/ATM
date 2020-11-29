package org.jlu.atm.pojo;

import javax.persistence.*;

@Entity
public class UserInfo {
    //自增逻辑主键
    private int id;
    //卡号 最大16位 非空 唯一
    private String cardID;
    //用户名 最大16位 非空
    private String username;
    //账户余额 非空
    private double balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 16, nullable = false, unique = true)
    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    @Column(length = 16, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
