package com.example.administrator.miniweather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.miniweather.R;
import com.example.administrator.miniweather.database.dao.CityDao;
import com.example.administrator.miniweather.models.City;
import com.example.administrator.miniweather.preferences.Preferences;
import com.example.administrator.miniweather.preferences.WeatherSettings;
import com.example.administrator.miniweather.utils.CommonUtil;
import com.example.library.activity.BaseActivity;

import java.io.InvalidClassException;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

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
        Preferences.loadDefaults();
        //TODO 测试，待删除
        try {
            Preferences.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, "101020100");
        } catch (InvalidClassException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "importCityData start");
        CommonUtil.importCityData();
        Log.d(TAG, "importCityData end");
        return null;
    }
}
