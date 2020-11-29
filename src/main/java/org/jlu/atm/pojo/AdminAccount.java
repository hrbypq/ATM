package org.jlu.atm.pojo;

import javax.persistence.*;

@Entity
public class AdminAccount {
    //自增逻辑主键
    private int id;
    //用户名 最大16位 非空 唯一
    private String username;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 16, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
