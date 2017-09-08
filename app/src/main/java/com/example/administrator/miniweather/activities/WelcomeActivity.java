package com.example.administrator.miniweather.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.miniweather.R;
import com.example.administrator.miniweather.database.dao.CityDao;
import com.example.administrator.miniweather.models.City;
import com.example.administrator.miniweather.utils.CommonUtil;
import com.example.library.activity.BaseActivity;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.just(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> {
                    gotoMainPage();
                });
    }

    private void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化应用数据
     */
    private List<City> initAppData() {

        CommonUtil.importCityData();
        return new CityDao(this).queryCityList();
    }
}
