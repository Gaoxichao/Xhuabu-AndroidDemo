package com.xhuabu.xhuabu_androiddemo.http;


import com.xhuabu.xhuabu_androiddemo.base.BaseResponse;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.LoginBean;
import com.xhuabu.xhuabu_androiddemo.ui.login.bean.UserAsset;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.HeadIcon;
import com.xhuabu.xhuabu_androiddemo.ui.user.bean.UserInfo;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 *
 */
public interface HttpApi {

    //所有需要的泛型 添加：BaseResponse<UserInfo>

    //注册（有邀请码）
    @FormUrlEncoded
    @POST("api/customer/user")
    Observable<BaseResponse<LoginBean>> register(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("captcha") String captcha,
                                                 @Field("promotionCode") String promotionCode);

    //注册（无邀请码）
    @FormUrlEncoded
    @POST("api/customer/user")
    Observable<BaseResponse<LoginBean>> register(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("captcha") String captcha);

    //登录
    @FormUrlEncoded
    @POST("api/customer/user/token")
    Observable<BaseResponse<LoginBean>> login(@Field("username") String username,
                                              @Field("password") String password);

    //获取个人信息
    @GET("api/customer/user")
    Observable<BaseResponse<UserInfo>> getUserInfo();

    //获取资产统计
    @GET("api/customer/user/asset-total")
    Observable<BaseResponse<UserAsset>> getUserAsset();

    //上传头像
    @Multipart
    @POST("api/customer/user/avatar")
    Observable<BaseResponse<HeadIcon>> updataUserHead(@Part MultipartBody.Part File);

    //更新用户昵称
    @FormUrlEncoded
    @PATCH("api/customer/user")
    Observable<BaseResponse> updataUserNickName(@Field("username") String username,
                                                @Field("nickname") String nickname);

    //更新用户密码
    @FormUrlEncoded
    @PATCH("api/customer/user")
    Observable<BaseResponse> updataUserPwd(@Field("username") String username,
                                           @Field("password") String password,
                                           @Field("captcha") String captcha);


}
