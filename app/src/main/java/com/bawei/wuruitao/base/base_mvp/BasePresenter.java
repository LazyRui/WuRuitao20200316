package com.bawei.wuruitao.base.base_mvp;

import java.lang.ref.WeakReference;

/**
 * ProjectName: WuRuitao20200316
 * PackageName: com.bawei.wuruitao.base.base_mvp
 * ClassName:   BasePresenter
 * Description: Java类的作用
 * Author: LazyRui
 * CreateDate: 2020/3/16 8:38
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {
    protected M mM;
    //使用弱引用
    private WeakReference<V> mVWeakReference;

    public BasePresenter() {
        mM = initModel();
    }

    /**
     * 初始化V层对象
     * @param v
     */

    public void attach(V v) {

        mVWeakReference = new WeakReference<>(v);

    }


    public void detach() {
        if (mVWeakReference != null) {
            //处理内存泄漏问题
            mVWeakReference.clear();
            mVWeakReference = null;
        }
    }

    protected abstract M initModel();

    public V getView() {
        if (mVWeakReference != null) {
            //获取V层对象
            return mVWeakReference.get();
        }
        return null;
    }
}
