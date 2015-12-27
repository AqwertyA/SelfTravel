package org.selftravel.beans;

import android.net.Uri;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "user_info")
public class UserInfo {
    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.headimg = "";
        this.username = username;
        this.account = username;
        this.password = password;
        this.gender = "保密";
        this.birthday = "不详";
        this.address = "不详";
    }

    @Column(name = "headimg")
    public String headimg;

    public String getAccount() {
        return account;
    }

    @Column(name = "account", isId = true)
    private String account;
    @Column(name = "username")
    public String username;
    @Column(name = "password")
    public String password;
    @Column(name = "gender")
    public String gender;
    @Column(name = "birthday")
    public String birthday;
    @Column(name = "address")
    public String address;


}
