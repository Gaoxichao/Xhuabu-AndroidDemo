package com.xhuabu.xhuabu_androiddemo.ui.user.presenter;

import com.xhuabu.xhuabu_androiddemo.base.BasePresenter;
import com.xhuabu.xhuabu_androiddemo.http.HttpManager;
import com.xhuabu.xhuabu_androiddemo.http.HttpSubscriber;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.UserAsset;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.UserInfo;
import com.xhuabu.xhuabu_androiddemo.ui.user.contract.UserContract;

/**
 * Created by UPC on 2018/3/1.
 */

public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {

    public final String TYPE_USERINFO = "userinfo";
    public final String TYPE_USERASSET = "userasset";


    @Override
    public void getUserInfo() {
        toSubscribe(HttpManager.getApi().getUserInfo(), new HttpSubscriber<UserInfo>() {
            @Override
            protected void _onStart() {
                mView.showLoading("加载中...");

            }

            @Override
            protected void _onNext(UserInfo userInfo) {
                if (userInfo != null) {
                    mView.onGetUserSuccess(userInfo);
                } else {
                    mView.showErrorMsg("加载失败，请重试", TYPE_USERINFO);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, TYPE_USERINFO);
            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });

    }


    @Override
    public void getUserAsset() {
        toSubscribe(HttpManager.getApi().getUserAsset(), new HttpSubscriber<UserAsset>() {
            @Override
            protected void _onStart() {
                mView.showLoading("加载中...");
            }

            @Override
            protected void _onNext(UserAsset asset) {
                if (asset != null) {
                    mView.onGetUserAssetSuccess(asset);
                } else {
                    mView.showErrorMsg("加载失败，请重试", TYPE_USERASSET);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorMsg(message, TYPE_USERASSET);
            }

            @Override
            protected void _onCompleted() {
                mView.stopLoading();
            }
        });
    }
}
