package com.xhuabu.xhuabu_androiddemo.base;

import com.google.gson.Gson;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;
import com.xhuabu.xhuabu_androiddemo.utils.SPUtils;

public class ConfigUtil {

    private boolean isLogin = false;//用户的登陆状态

    public static String SPTAG_TOKEN = "token"; //token key

    public static String BASE_URL = "http://customer.kiwiyun.com/"; //baseurl


    public ConfigUtil() {
        setUserInfo(getUserInfo());
    }

    public LoginBean getUserInfo() {
        return toObject(SPUtils.getInstance().getString("loginBean"), LoginBean.class);
    }

    public void setUserInfo(LoginBean loginBean) {
        isLogin = loginBean == null ? false : true;
        SPUtils.getInstance().put("loginBean", toJsonString(loginBean));
    }

    //获取用户当前登录状态
    public boolean getLoginStatus() {
        return isLogin;
    }


    public static String toJsonString(Object object) {

        String result = "";
        try {
            result = new Gson().toJson(object);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return result;
    }

    public static <T> T toObject(String json, Class<T> clazz) {

        T instance_class = null;

        try {
            instance_class = new Gson().fromJson(json, clazz);
        } catch (Exception e) {

        }

        return instance_class;
    }

}
