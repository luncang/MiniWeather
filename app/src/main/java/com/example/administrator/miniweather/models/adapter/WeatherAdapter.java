package com.example.administrator.miniweather.models.adapter;


import com.example.administrator.miniweather.models.style.AQI;
import com.example.administrator.miniweather.models.style.Forecast;
import com.example.administrator.miniweather.models.style.LifeIndex;
import com.example.administrator.miniweather.models.style.RealTime;
import com.example.administrator.miniweather.models.style.Weather;

import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public abstract class WeatherAdapter {

    public abstract int getCityId();

    public abstract String getCityName();

    public abstract String getCityNameEn();

    public abstract RealTime getRealTime();

    public abstract List<Forecast> getForecasts();

    public abstract List<LifeIndex> getLifeIndexes();

    public abstract AQI getAQI();

    public abstract Weather getWeather();
}
