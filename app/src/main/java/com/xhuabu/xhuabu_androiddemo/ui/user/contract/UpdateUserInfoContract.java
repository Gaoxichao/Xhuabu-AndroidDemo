package com.xhuabu.xhuabu_androiddemo.ui.user.contract;

import com.xhuabu.xhuabu_androiddemo.base.BaseView;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.HeadIcon;

/**
 * Created by UPC on 2018/3/1.
 */

public interface UpdateUserInfoContract {


    interface View extends BaseView {

        void onUpdateSuccess();

        void onUpdateHeadSuccess(HeadIcon headIcon);
    }

    interface Presenter {

        void updatePwd(String username,
                       String password,
                       String captcha);

        void updateNickName(String username,
                            String nickname);

        void updateUserHead(String fileName);

    }
}
