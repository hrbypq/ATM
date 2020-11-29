package org.jlu.atm.pojo;

import javax.persistence.*;

@Entity
public class UserAccount {
    //自增逻辑主键
    private int id;
    //卡号 最大16位 非空 唯一
    private String cardID;
    //密码 最大16位 非空
    private String password;

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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccount(int id, String cardID, String password) {
        this.id = id;
        this.cardID = cardID;
        this.password = password;
    }

    public UserAccount() {
    }

}
