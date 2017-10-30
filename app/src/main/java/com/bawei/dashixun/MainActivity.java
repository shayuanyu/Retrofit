package com.bawei.dashixun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bawei.dashixun.API.Api;
import com.bawei.dashixun.Bean.Infoo;
import com.bawei.dashixun.Bean.News;
import com.bawei.dashixun.Bean.YouCan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // unArguments();
            Argument();
      // getWenHao();
    }
     //无参
     public void unArguments(){

         Retrofit retrofit=new Retrofit.Builder()
                   .baseUrl(Api.DATA)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
         //得到网络接口 通过动态代理的方法实现
         RetrofitSevercce retrofitSevercce = retrofit.create(RetrofitSevercce.class);
         Call<News> call = retrofitSevercce.getData();
         call.enqueue(new Callback<News>() {
             @Override
             public void onResponse(Call<News> call, Response<News> response) {
                 News news = response.body();
                 List<News.AdsBean> ads = news.getAds();
                 for (int i = 0; i <ads.size(); i++) {
                     News.AdsBean bean = ads.get(i);
                     int adsorder = bean.getAdsorder();
                     String imgsrc = bean.getImgsrc();
                     Toast.makeText(MainActivity.this, "无参成功"+adsorder+"图片"+imgsrc, Toast.LENGTH_SHORT).show();


                 }
             }

             @Override
             public void onFailure(Call<News> call, Throwable t) {

             }
         });


     }
     public void Argument(){
         //得到Retrofit的对象   使用的是建造者模式
          Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.DATA1).addConverterFactory(GsonConverterFactory.create()).build();
       //得到网络接口，使用动态代理的方法实现

         RetrofitSevercce retrofitSevercce = retrofit.create(RetrofitSevercce.class);
         Call<YouCan> argument = retrofitSevercce.getArgument("forever");
         argument.enqueue(new Callback<YouCan>() {

             @Override
             public void onResponse(Call<YouCan> call, Response<YouCan> response) {
                 YouCan youCan = response.body();
                 Toast.makeText(MainActivity.this, "有参成功"+youCan.avatar_url, Toast.LENGTH_SHORT).show();





             }

             @Override
             public void onFailure(Call<YouCan> call, Throwable t) {

             }
         });
  }
  public void getWenHao(){
         //创建Retrofit对象，建造者模式
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.DATA2).addConverterFactory(GsonConverterFactory.create()).build();
       //网络接口
      RetrofitSevercce pingjie = retrofit.create(RetrofitSevercce.class);
      Call<Infoo> call = pingjie.getPingjie(1, 0);
      call.enqueue(new Callback<Infoo>() {
          @Override
          public void onResponse(Call<Infoo> call, Response<Infoo> response) {
              Infoo infoo = response.body();
              List<Infoo.DataBean> data = infoo.getData();
              for (Infoo.DataBean b:data){
                  Toast.makeText(MainActivity.this, "拼接对象"+b.getTITLE(), Toast.LENGTH_SHORT).show();
              }








          }

          @Override
          public void onFailure(Call<Infoo> call, Throwable t) {

          }
      });


  }


}
