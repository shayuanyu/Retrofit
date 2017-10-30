package com.bawei.dashixun;

import com.bawei.dashixun.Bean.Infoo;
import com.bawei.dashixun.Bean.News;
import com.bawei.dashixun.Bean.YouCan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface RetrofitSevercce {
    /**
     * 无参get请求
     * http://service.meiyinkeqiu.com/service/ads/cptj
     *
     * @return
     */

    @GET("service/ads/cptj")
      Call<News> getData();

    //有参构造https://api.github.com/users/baiiu
    //拼接参数/形式
    @GET("users/{user}")
    Call<YouCan> getArgument(@Path("user")String user);
  //拼接 ？&http://www.93.gov.cn/93app/data.do?channelId=1&startNum=2
    @GET("data.do")
    Call<Infoo>  getPingjie(@Query("channelId") int channelId,@Query("startNum") int startNum);


}
