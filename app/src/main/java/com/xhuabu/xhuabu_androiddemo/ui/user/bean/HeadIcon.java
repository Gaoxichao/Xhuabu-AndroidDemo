package com.xhuabu.xhuabu_androiddemo.ui.user.bean;

/**
 * Created by UPC on 2018/3/1.
 */

public class HeadIcon {

    /**
     * avatarLink : http://jinniubao.oss-cn-shenzhen.aliyuncs.com/userAvatar/defaultAvatar.png
     */

    private String avatarLink;

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    @Override
    public String toString() {
        return "HeadIcon{" +
                "avatarLink='" + avatarLink + '\'' +
                '}';
    }
}
