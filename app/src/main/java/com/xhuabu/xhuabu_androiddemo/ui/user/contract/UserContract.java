package com.xhuabu.xhuabu_androiddemo.ui.user.contract;

import com.xhuabu.xhuabu_androiddemo.base.BaseView;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.UserAsset;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.UserInfo;

/**
 * Created by UPC on 2018/3/1.
 */

public interface UserContract {

    interface View extends BaseView {
        void onGetUserSuccess(UserInfo userInfo);

        void onGetUserAssetSuccess(UserAsset userAsset);
    }

    interface Presenter {

        void getUserInfo();

        void getUserAsset();
    }
}
