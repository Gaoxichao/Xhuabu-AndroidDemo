package com.xhuabu.xhuabu_androiddemo.ui.user.presenter;

import android.widget.Toast;

import com.xhuabu.xhuabu_androiddemo.base.BasePresenter;
import com.xhuabu.xhuabu_androiddemo.http.HttpManager;
import com.xhuabu.xhuabu_androiddemo.http.HttpSubscriber;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.HeadIcon;
import com.xhuabu.xhuabu_androiddemo.ui.user.contract.UpdateUserInfoContract;
import com.xhuabu.xhuabu_androiddemo.utils.EncryptUtils;

/**
 * Created by UPC on 2018/3/1.
 */

public class UpdateUserInfoPresenter extends BasePresenter<UpdateUserInfoContract.View> implements UpdateUserInfoContract.Presenter {

    public static String TYPE_PWD = "pwd";
    public static String TYPE_NICKNAME = "nickname";
    public static String TYPE_HEAD = "head";


    @Override
    public void updatePwd(String username, String password, String captcha) {
        toSubscribe(HttpManager.getApi().updataUserPwd(username, EncryptUtils.encryptMD5ToString(password), captcha), new HttpSubscriber() {
            @Override
            protected void _onStart() {
                mView.showLoading("请稍后...");
            }

            @Override
            protected void _onNext(Object o) {
                mView.onUpdateSuccess();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, TYPE_PWD);

            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });
    }

    @Override
    public void updateNickName(String username, String nickname) {
        toSubscribe(HttpManager.getApi().updataUserNickName(username, nickname), new HttpSubscriber() {
            @Override
            protected void _onStart() {
                mView.showLoading("请稍后...");
            }

            @Override
            protected void _onNext(Object o) {
                mView.onUpdateSuccess();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, TYPE_NICKNAME);

            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });

    }

    @Override
    public void updateUserHead(String fileName) {
        toSubscribe(HttpManager.getApi().updataUserHead(putFile("imageName", fileName)), new HttpSubscriber<HeadIcon>() {
            @Override
            protected void _onStart() {
                mView.showLoading("上传中...");
            }

            @Override
            protected void _onNext(HeadIcon headIcon) {
                if (headIcon == null) {
                    mView.showErrorMsg("上传失败", TYPE_HEAD);
                } else {
                    mView.onUpdateHeadSuccess(headIcon);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, TYPE_HEAD);
            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });

    }
}
