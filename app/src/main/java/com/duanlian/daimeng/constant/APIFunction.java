package com.duanlian.daimeng.constant;

import com.duanlian.daimeng.model.YiYuanNews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 段炼 on 2017/9/13.
 * Description :
 */

public interface APIFunction {
    @GET("/109-35?showapi_appid=46075&showapi_sign=7aa109b067f741e0be287348d8961da1")
    Observable<YiYuanNews> getYiYuanNews(@Query("page") int page);
}
