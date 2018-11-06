package com.duanlian.daimeng.ui;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.constant.APIFunction;
import com.duanlian.daimeng.constant.CommendUrl;
import com.duanlian.daimeng.model.YiYuanNews;
import com.duanlian.daimeng.utils.LogU;
import com.duanlian.daimeng.utils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 新闻列表fragment,全部公用这个fragment
 */
public class NewsListFragment extends BaseFragment {
    String title;

    @Override
    public int getLayoutRes() {
        if (getArguments() != null) {//拿到传递过来的channelName
            title = getArguments().getString("channelName");
        }
        return R.layout.fragment_news_list;
    }

    @Override
    public void initView() {
        RetrofitUtils
                .createRetrofit(CommendUrl.YIYUAN_BASE_URL)
                .create(APIFunction.class)
                .getYiYuanNews(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YiYuanNews>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YiYuanNews value) {
                        LogU.e(value.getShowapi_res_body().getPagebean().getContentlist()
                        .get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {

                        LogU.e(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
