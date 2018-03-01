package com.xhuabu.xhuabu_androiddemo.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import com.xhuabu.xhuabu_androiddemo.MyApp;
import com.xhuabu.xhuabu_androiddemo.R;
import com.xhuabu.xhuabu_androiddemo.base.BaseActivity;
import com.xhuabu.xhuabu_androiddemo.base.PermissionsListener;
import com.xhuabu.xhuabu_androiddemo.ui.login.activity.LoginActivity;
import com.xhuabu.xhuabu_androiddemo.ui.user.activity.UserActivity;
import com.xhuabu.xhuabu_androiddemo.widget.AlertFragmentDialog;

import java.util.List;


public class SplashActivity extends BaseActivity {

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private boolean isRequesting;


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isRequesting) {
            requestPermissions(permissions, mListener);
            isRequesting = true;
        }
    }

    private PermissionsListener mListener = new PermissionsListener() {
        @Override
        public void onGranted() {
            isRequesting = false;
//            new Handler(new Handler.Callback() {
//                @Override
//                public boolean handleMessage(Message msg) {
//                    if (!MyApp.getConfig().getLoginStatus()) {
//                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                        finish();
//                    } else {
//                        startActivity(new Intent(SplashActivity.this, UserActivity.class));
//                        finish();
//                    }
//                    return false;
//                }
//            }).sendEmptyMessageDelayed(0, 2000);
            if (!MyApp.getConfig().getLoginStatus()) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, UserActivity.class));
                finish();
            }
        }

        @Override
        public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
            if (!isNeverAsk) {//请求权限没有全被勾选不再提示然后拒绝
                new AlertFragmentDialog.Builder(SplashActivity.this)
                        .setLeftBtnText("退出").setLeftCallBack(new AlertFragmentDialog.LeftClickCallBack() {
                    @Override
                    public void dialogLeftBtnClick() {
                        finish();
                    }
                }).setContent("为了能正常使用应用，请授予所需权限")
                        .setRightBtnText("授权").setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                    @Override
                    public void dialogRightBtnClick() {
                        requestPermissions(permissions, mListener);
                    }
                }).build();
            } else {//全被勾选不再提示
                new AlertFragmentDialog.Builder(SplashActivity.this)
                        .setLeftBtnText("退出").setLeftCallBack(new AlertFragmentDialog.LeftClickCallBack() {
                    @Override
                    public void dialogLeftBtnClick() {
                        finish();
                    }
                }).setContent("缺少必要权限\n请手动授予应用访问您的权限")
                        .setRightBtnText("授权").setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                    @Override
                    public void dialogRightBtnClick() {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                        isRequesting = false;
                    }
                }).build();
            }
        }
    };


    @Override
    public void loadData() {

    }

}
