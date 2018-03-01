package com.xhuabu.xhuabu_androiddemo.ui.login.contract;

import com.xhuabu.xhuabu_androiddemo.base.BaseView;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;

/**
 * Created by UPC on 2018/3/1.
 */

public interface LoginContract {

    interface View extends BaseView {
        void LoginSuccess(LoginBean loginBean);
    }

    interface Presenter {
        void toLogin(String username,
                     String password);
    }
}
