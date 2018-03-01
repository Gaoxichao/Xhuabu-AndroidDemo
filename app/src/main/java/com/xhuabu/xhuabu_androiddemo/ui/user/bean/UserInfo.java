package com.xhuabu.xhuabu_androiddemo.ui.user.bean;

/**
 * Created by UPC on 2018/3/1.
 */

public class UserInfo {

    /**
     * phone : 18681490507
     * headUrl : http://jinniubao.oss-cn-shenzhen.aliyuncs.com/userAvatar/12583_1512369208602
     * cashAsset : 97599700
     * scoreAsset : 6000000
     * coinAsset : 0
     * realName : 李悦东
     * bankCardNo : 6212264000045832637
     * bankCardName : ⼯商银⾏
     * hasUserAuthFreeze : false
     * hasSetLoginPwd : true
     * hasSetWithdrawPwd : false
     */

    private String phone;
    private String headUrl;
    private int cashAsset;
    private int scoreAsset;
    private int coinAsset;
    private String realName;
    private String bankCardNo;
    private String bankCardName;
    private boolean hasUserAuthFreeze;
    private boolean hasSetLoginPwd;
    private boolean hasSetWithdrawPwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getCashAsset() {
        return cashAsset;
    }

    public void setCashAsset(int cashAsset) {
        this.cashAsset = cashAsset;
    }

    public int getScoreAsset() {
        return scoreAsset;
    }

    public void setScoreAsset(int scoreAsset) {
        this.scoreAsset = scoreAsset;
    }

    public int getCoinAsset() {
        return coinAsset;
    }

    public void setCoinAsset(int coinAsset) {
        this.coinAsset = coinAsset;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public boolean isHasUserAuthFreeze() {
        return hasUserAuthFreeze;
    }

    public void setHasUserAuthFreeze(boolean hasUserAuthFreeze) {
        this.hasUserAuthFreeze = hasUserAuthFreeze;
    }

    public boolean isHasSetLoginPwd() {
        return hasSetLoginPwd;
    }

    public void setHasSetLoginPwd(boolean hasSetLoginPwd) {
        this.hasSetLoginPwd = hasSetLoginPwd;
    }

    public boolean isHasSetWithdrawPwd() {
        return hasSetWithdrawPwd;
    }

    public void setHasSetWithdrawPwd(boolean hasSetWithdrawPwd) {
        this.hasSetWithdrawPwd = hasSetWithdrawPwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", cashAsset=" + cashAsset +
                ", scoreAsset=" + scoreAsset +
                ", coinAsset=" + coinAsset +
                ", realName='" + realName + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", bankCardName='" + bankCardName + '\'' +
                ", hasUserAuthFreeze=" + hasUserAuthFreeze +
                ", hasSetLoginPwd=" + hasSetLoginPwd +
                ", hasSetWithdrawPwd=" + hasSetWithdrawPwd +
                '}';
    }
}
