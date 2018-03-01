package com.xhuabu.xhuabu_androiddemo.ui.user.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xhuabu.xhuabu_androiddemo.MyApp;
import com.xhuabu.xhuabu_androiddemo.R;
import com.xhuabu.xhuabu_androiddemo.base.BaseActivity;
import com.xhuabu.xhuabu_androiddemo.base.PermissionsListener;
import com.xhuabu.xhuabu_androiddemo.ui.login.activity.RegisterActivity;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.UserAsset;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.HeadIcon;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.UserInfo;
import com.xhuabu.xhuabu_androiddemo.ui.user.contract.UpdateUserInfoContract;
import com.xhuabu.xhuabu_androiddemo.ui.user.contract.UserContract;
import com.xhuabu.xhuabu_androiddemo.ui.user.presenter.UpdateUserInfoPresenter;
import com.xhuabu.xhuabu_androiddemo.ui.user.presenter.UserPresenter;
import com.xhuabu.xhuabu_androiddemo.utils.ConvertUtil;
import com.xhuabu.xhuabu_androiddemo.widget.AlertFragmentDialog;
import com.xhuabu.xhuabu_androiddemo.widget.CircleImageView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View, UpdateUserInfoContract.View {


    @BindView(R.id.tv_tel)
    TextView tvTel;

    @BindView(R.id.iv_userHead)
    CircleImageView ivUserHead;

    @BindView(R.id.tv_userNickName)
    TextView tvUserNickName;

    @BindView(R.id.tv_userasset)
    TextView tvUserAsset;

    @BindView(R.id.tv_userstock)
    TextView tvUserstock;

    @BindView(R.id.tv_detail)
    TextView tvDetail;

    private ProgressDialog progressDialog;

    //照相文件
    private File newFile;
    private UpdateUserInfoPresenter updateUserInfoPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        updateUserInfoPresenter = new UpdateUserInfoPresenter();
        updateUserInfoPresenter.init(this);
    }

    @Override
    public void loadData() {

        if (MyApp.getConfig().getLoginStatus()) {
            mPresenter.getUserInfo();
        }
    }

    private boolean checkSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            Toast.makeText(UserActivity.this, "SD卡不存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @OnClick({R.id.tv_tel, R.id.iv_userHead, R.id.tv_userNickName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tel:
                callTel("0755-29202196");
                break;
            case R.id.iv_userHead:
                new AlertDialog.Builder(UserActivity.this)
                        .setItems(new String[]{"更改头像", "更改个人信息"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    takePhoto();
                                } else if (which == 1) {
                                    startActivity(new Intent(UserActivity.this, UpdateUserInfoActivity.class));
                                }
                            }
                        }).show();
                break;
            case R.id.tv_userNickName:
                break;
        }
    }

    /**
     * 拍照上传头像
     */
    private void takePhoto() {
        if (checkSD()) {
            // 照相 和 读取 内存权限
            requestPermissions(new String[]{Manifest.permission.CAMERA}, new PermissionsListener() {
                //授权
                @Override
                public void onGranted() {
                    //7.0 处理 重新构造Uri：content://
                    File imagePath = new File(Environment.getExternalStorageDirectory() + "/" + getApplicationInfo()
                            .packageName + "/image/");
                    if (!imagePath.exists()) {
                        imagePath.mkdirs();
                    }
                    newFile = new File(imagePath, System.currentTimeMillis() + ".jpg");
                    Uri contentUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        contentUri = FileProvider.getUriForFile(mContext, MyApp.getContext().getPackageName() + "" +
                                ".provider.fileProvider", newFile);
                    } else {
                        contentUri = Uri.fromFile(newFile);
                    }

                    try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                        // 授予目录临时共享权限
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        startActivityForResult(intent, 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(UserActivity.this, "拍照应用不可用", Toast.LENGTH_SHORT).show();
                    }
                }

                //拒绝
                @Override
                public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
                    if (isNeverAsk) {
                        toAppSettings("相机权限已被禁止", false);
                    }
                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 100) {
            if (data != null) {


            } else {
                if (newFile != null && !TextUtils.isEmpty(newFile.getPath())) {
                    String cameimage = newFile.getPath();
                    saveFile(cameimage);
                } else {
                    Toast.makeText(this, "照片获取失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * 拨打客服电话
     *
     * @param phone 客服电话
     */

    private void callTel(final String phone) {
        new AlertFragmentDialog.Builder(this)
                .setCancel(false)
                .setTitle("温馨提示")
                .setContent("是否拨打客服电话 " + phone)
                .setLeftBtnText("取消").setLeftCallBack(new AlertFragmentDialog.LeftClickCallBack() {
            @Override
            public void dialogLeftBtnClick() {

            }
        }).setRightBtnText("确定").setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
            @Override
            public void dialogRightBtnClick() {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).build();
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
    public void onGetUserSuccess(UserInfo userInfo) {
        Glide.with(this)
                .load(userInfo.getHeadUrl())
//                .placeholder(R.mipmap.defaultavatar)
//                .error(R.mipmap.defaultavatar)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.2f)
                .into(ivUserHead);

        tvUserNickName.setText(userInfo.getPhone());
        tvDetail.setText(userInfo.toString());

        mPresenter.getUserAsset();
    }

    @Override
    public void onGetUserAssetSuccess(UserAsset userAsset) {
        tvUserAsset.setText(userAsset.getTotalCash() + "");
        tvUserstock.setText(userAsset.getTotalStock() + "");
    }

    public void buildProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(UserActivity.this);
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

    private void saveFile(final String imagePath) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (new File(imagePath).exists()) {//图片是否存在
                    //获取图片旋转度数
                    int degree = ConvertUtil.getBitmapDegree(imagePath);
                    //压缩图片
                    Bitmap bitmap = ConvertUtil.getCompressedBmp(imagePath);
                    //如果旋转度数大于0则进行校正
                    if (degree > 0) {
                        bitmap = ConvertUtil.rotateBitmapByDegree(bitmap, degree);
                    }
                    //保存压缩、校正旋转之后的图片(覆盖掉所拍的图片)
                    ConvertUtil.saveBitmap(imagePath, bitmap);
                    if (bitmap != null) {
                        bitmap.recycle();//释放内存
                    }
                    subscriber.onNext(imagePath);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new IOException("图片保存失败"));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        updateUserInfoPresenter.updateUserHead(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(UserActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onUpdateSuccess() {

    }

    @Override
    public void onUpdateHeadSuccess(HeadIcon headIcon) {
        Glide.with(this)
                .load(headIcon.getAvatarLink())
//                .placeholder(R.mipmap.defaultavatar)
//                .error(R.mipmap.defaultavatar)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.2f)
                .into(ivUserHead);

        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
    }
}
