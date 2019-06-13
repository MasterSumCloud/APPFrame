package com.example.appframe.base;

/**
 * author: eagle
 * created on: 2019-06-13 13:16
 * description:
 */
public interface BaseView {


    /**
     * 显示加载Loading
     */
    void showLoading();

    /**
     * 隐藏加载Loading
     */
    void hideLoading();

    /**
     * 页面数据加载出错
     * @param throwable t
     */
    void onError(Throwable throwable);

    /**
     * 绑定的安卓的生命周期，防止Rxjava内存泄露
     * @param <T> T
     * @return .
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
