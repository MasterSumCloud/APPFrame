package com.example.appframe.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * author: eagle
 * created on: 2019-06-13 13:13
 * description:
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView  {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     */
   /* @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }*/

}
