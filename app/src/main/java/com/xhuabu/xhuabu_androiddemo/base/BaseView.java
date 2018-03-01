package com.xhuabu.xhuabu_androiddemo.base;

/**
 * baseview
 */
public interface BaseView {


    void showLoading(String content);

    void stopLoading();

    /**
     * 请求失败
     * @param msg  请求异常信息
     * @param type  若有多个请求，用于区分不同请求（不同请求失败或有不同的处理）
     *              PS：无需区分则可传null
     */
    void showErrorMsg(String msg, String type);
}
