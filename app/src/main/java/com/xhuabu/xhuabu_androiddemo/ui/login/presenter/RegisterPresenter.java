package com.xhuabu.xhuabu_androiddemo.ui.login.presenter;

import android.text.TextUtils;

import com.xhuabu.xhuabu_androiddemo.base.BasePresenter;
import com.xhuabu.xhuabu_androiddemo.base.BaseResponse;
import com.xhuabu.xhuabu_androiddemo.http.HttpManager;
import com.xhuabu.xhuabu_androiddemo.http.HttpSubscriber;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;
import com.xhuabu.xhuabu_androiddemo.ui.login.contract.RegisterContract;
import com.xhuabu.xhuabu_androiddemo.utils.EncryptUtils;

import rx.Observable;

/**
 * Created by UPC on 2018/3/1.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void toRegister(String username, String password, String captcha, String promotionCode) {

        Observable<BaseResponse<LoginBean>> register;

        if (TextUtils.isEmpty(promotionCode)) {
            //邀请码为空
            register = HttpManager.getApi().register(username, EncryptUtils.encryptMD5ToString(password), captcha);
        }else {
            register = HttpManager.getApi().register(username, EncryptUtils.encryptMD5ToString(password), captcha, promotionCode);
        }


        toSubscribe(register, new HttpSubscriber<LoginBean>() {
            @Override
            protected void _onStart() {
                mView.showLoading("注册中...");
            }

            @Override
            protected void _onNext(LoginBean loginBean) {
                if (loginBean != null) {
                    mView.registerSuccess(loginBean);
                } else {
                    mView.showErrorMsg("注册失败，请重试", null);
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
