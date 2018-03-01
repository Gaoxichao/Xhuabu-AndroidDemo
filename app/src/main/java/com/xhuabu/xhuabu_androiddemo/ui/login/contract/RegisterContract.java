package com.xhuabu.xhuabu_androiddemo.ui.login.contract;

import com.xhuabu.xhuabu_androiddemo.base.BaseView;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;

/**
 * Created by UPC on 2018/3/1.
 */

public interface RegisterContract {

    interface View extends BaseView {
        void registerSuccess(LoginBean loginBean);
    }

    interface Presenter {
        void toRegister(String username,
                        String password,
                        String captcha,
                        String promotionCode);
    }

}
