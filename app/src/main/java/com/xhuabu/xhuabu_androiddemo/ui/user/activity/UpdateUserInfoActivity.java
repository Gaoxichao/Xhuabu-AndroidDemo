package com.xhuabu.xhuabu_androiddemo.ui.user.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xhuabu.xhuabu_androiddemo.MyApp;
import com.xhuabu.xhuabu_androiddemo.R;
import com.xhuabu.xhuabu_androiddemo.base.BaseActivity;
import com.xhuabu.xhuabu_androiddemo.ui.login.activity.LoginActivity;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.HeadIcon;
import com.xhuabu.xhuabu_androiddemo.ui.user.contract.UpdateUserInfoContract;
import com.xhuabu.xhuabu_androiddemo.ui.user.presenter.UpdateUserInfoPresenter;
import com.xhuabu.xhuabu_androiddemo.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter> implements UpdateUserInfoContract.View {


    @BindView(R.id.tv_pwd)
    TextView tvPwd;

    @BindView(R.id.tv_nickname)
    TextView tvNickname;


    private ProgressDialog progressDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

    }


    @OnClick({R.id.tv_pwd, R.id.tv_nickname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pwd:
                showDialog("请输入新密码", 1);
                break;
            case R.id.tv_nickname:
                showDialog("请输入新昵称", 2);
                break;
        }
    }

    private void showDialog(String title, final int type) {
        final EditText inputServer = new EditText(UpdateUserInfoActivity.this);
        new AlertDialog.Builder(UpdateUserInfoActivity.this)
                .setTitle(title)
                .setView(inputServer)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String str = inputServer.getText().toString().trim();
                        if (TextUtils.isEmpty(str)) {
                            Toast.makeText(UpdateUserInfoActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            if (type == 1) {
                                //更改密码
                                mPresenter.updatePwd(MyApp.getConfig().getUserInfo().getPhone(), str, "000000");
                                //重新登录
                                MyApp.getConfig().setUserInfo(null);
                                SPUtils.getInstance().clear();
                                startActivity(new Intent(UpdateUserInfoActivity.this, LoginActivity.class));
                                finish();
                            } else if (type == 2) {
                                //更改昵称
                                mPresenter.updateNickName(MyApp.getConfig().getUserInfo().getPhone(), str);
                            }
                        }
                    }
                }).show();
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSuccess() {
        Toast.makeText(this, "更改成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateHeadSuccess(HeadIcon headIcon) {

    }

    public void buildProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(UpdateUserInfoActivity.this);
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
