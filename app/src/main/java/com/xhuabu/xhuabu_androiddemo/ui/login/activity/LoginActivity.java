package com.xhuabu.xhuabu_androiddemo.ui.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xhuabu.xhuabu_androiddemo.MyApp;
import com.xhuabu.xhuabu_androiddemo.R;
import com.xhuabu.xhuabu_androiddemo.base.BaseActivity;
import com.xhuabu.xhuabu_androiddemo.base.ConfigUtil;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;
import com.xhuabu.xhuabu_androiddemo.ui.login.contract.LoginContract;
import com.xhuabu.xhuabu_androiddemo.ui.login.presenter.LoginPresenter;
import com.xhuabu.xhuabu_androiddemo.ui.user.activity.UserActivity;
import com.xhuabu.xhuabu_androiddemo.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.tv_login)
    TextView tvLogin;

    private ProgressDialog progressDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.tv_submit, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:

                String userName = etPhone.getText().toString().trim();
                String passWord = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passWord)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.toLogin(userName, passWord);
                }

                break;
            case R.id.tv_login:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void showLoading(String content) {
        buildProgressDialog(content);
    }

    @Override
    public void stopLoading() {
        cancelProgressDialog();
    }

    @Override
    public void showErrorMsg(String msg, String type) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void LoginSuccess(LoginBean loginBean) {
        MyApp.getConfig().setUserInfo(loginBean);
        //更新token
        SPUtils.getInstance().put(ConfigUtil.SPTAG_TOKEN, loginBean.getToken());
        //跳转
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, UserActivity.class));
        finish();
    }

    public void buildProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void cancelProgressDialog() {
        if (progressDialog != null)
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
    }
}
