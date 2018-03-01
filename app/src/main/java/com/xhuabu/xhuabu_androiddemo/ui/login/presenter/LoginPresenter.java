package com.xhuabu.xhuabu_androiddemo.ui.login.presenter;

import com.xhuabu.xhuabu_androiddemo.base.BasePresenter;
import com.xhuabu.xhuabu_androiddemo.http.HttpManager;
import com.xhuabu.xhuabu_androiddemo.http.HttpSubscriber;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;
import com.xhuabu.xhuabu_androiddemo.ui.login.contract.LoginContract;
import com.xhuabu.xhuabu_androiddemo.utils.EncryptUtils;

/**
 * Created by UPC on 2018/3/1.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void toLogin(String username, String password) {
        toSubscribe(HttpManager.getApi().login(username, EncryptUtils.encryptMD5ToString(password)), new HttpSubscriber<LoginBean>() {
            @Override
            protected void _onStart() {
                mView.showLoading("登录中...");
            }

            @Override
            protected void _onNext(LoginBean loginBean) {
                if (loginBean != null) {
                    mView.LoginSuccess(loginBean);
                } else {
                    mView.showErrorMsg("登录失败，请重试", null);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, null);
            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });
    }
}
