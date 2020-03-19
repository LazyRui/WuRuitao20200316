package com.bawei.wuruitao.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.wuruitao.base.base_mvp.BasePresenter;
import com.bawei.wuruitao.base.base_mvp.IBaseView;

/**
 * ProjectName: WuRuitao20200316
 * PackageName: com.bawei.wuruitao.base
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: LazyRui
 * CreateDate: 2020/3/16 8:40
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局（渲染布局）
        setContentView(layoutId());
        //初始化P层对象
        mPresenter = initPresenter();
        //绑定对应的布局对象
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决内存泄漏问题
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
