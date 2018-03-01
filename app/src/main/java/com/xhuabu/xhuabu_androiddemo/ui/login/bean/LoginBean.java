package com.xhuabu.xhuabu_androiddemo.ui.login.bean;

/**
 * Created by UPC on 2018/3/1.
 */

public class LoginBean {

    /**
     * token : b47fee5f621c42c0b77a3d7476da16ac
     * headUrl : http://res.yibeidiao.com/user/userInfo_head.png
     * phone : 18683496757
     * cashAsset : 0
     */

    private String token;
    private String headUrl;
    private String phone;
    private int cashAsset;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCashAsset() {
        return cashAsset;
    }

    public void setCashAsset(int cashAsset) {
        this.cashAsset = cashAsset;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "token='" + token + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", cashAsset=" + cashAsset +
                '}';
    }
}
