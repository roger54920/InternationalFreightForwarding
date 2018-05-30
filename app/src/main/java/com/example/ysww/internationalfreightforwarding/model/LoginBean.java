package com.example.ysww.internationalfreightforwarding.model;

/**
 * Created by ysww on 2018/5/8.
 */

public class LoginBean extends BaseBean {

    /**
     * expire : 43200
     * userId : 30
     * token : 33451d1e77631448d84159dfffe1f06a
     * username : 万渠道
     */

    private int expire;
    private int userId;
    private String token;
    private String username;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
