package com.example.appframe.base;

/**
 * author: eagle
 * created on: 2019-06-13 13:13
 * description:
 */
public class BasePresenter<V extends BaseView> {


    private V mView;

    /**
     * 绑定View
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解绑View
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * 判定View是否被绑定
     *
     * @return true or false
     */
    public boolean isViewAttached() {
        return mView != null;
    }


}
