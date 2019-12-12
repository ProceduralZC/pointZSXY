package com.android.commonapp.interfaces;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    private static final String TAG = "BasePresenterImpl";
    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
        //Log.e(TAG, "detach: 执行");
        unDisposable();
    }

//    // 将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
//    private CompositeDisposable compositeDisposable;
//
//    /**
//     * 将Disposable添加
//     *
//     * @param subscription
//     */
//    @Override
//    public void addDisposable(Disposable subscription) {
//        // csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
//        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
//            Log.e(TAG, "addDisposable: new了添加CompositeDisposable");
//            compositeDisposable = new CompositeDisposable();
//        }
//        compositeDisposable.add(subscription);
//        Log.e(TAG, "addDisposable: 添加进去了");
//    }

    /**
     * 在activity退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
//        if (compositeDisposable != null) {
//            compositeDisposable.dispose();
//            compositeDisposable = null;
//            Log.e(TAG, "unDisposable: 解绑了");
//        }
    }

//    public <T> ObservableTransformer<T, T> setThread() {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io()) // 请求在子线程中进行
//                        .observeOn(AndroidSchedulers.mainThread()); // 回调结果在主线程
//            }
//        };
//    }

}
