package com.example.administrator.miniweather.models.adapter;


import com.example.administrator.miniweather.models.style.AQI;
import com.example.administrator.miniweather.models.style.Forecast;
import com.example.administrator.miniweather.models.style.LifeIndex;
import com.example.administrator.miniweather.models.style.RealTime;
import com.example.administrator.miniweather.models.style.Weather;
import com.example.api.entities.MiForecast;
import com.example.api.entities.MiIndex;
import com.example.api.entities.MiWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/26
 */
public class MiWeatherAdapter extends WeatherAdapter {

    private MiWeather miWeather;

    public MiWeatherAdapter(MiWeather miWeather) {
        this.miWeather = miWeather;
    }

    @Override
    public int getCityId() {
        return miWeather.getForecast().getCityId();
    }

    @Override
    public String getCityName() {
        return miWeather.getForecast().getCityName();
    }

    @Override
    public String getCityNameEn() {
        return miWeather.getForecast().getCityEn();
    }

    @Override
    public RealTime getRealTime() {
        return new RealTime(miWeather.getRealTime().getCityId(),
                miWeather.getRealTime().getWeather(), miWeather.getRealTime().getTemp(),
                miWeather.getRealTime().getHumidity(), miWeather.getRealTime().getWind(),
                miWeather.getRealTime().getWindSpeed(), miWeather.getRealTime().getTime());
    }

    @Override
    public List<Forecast> getForecasts() {

        List<Forecast> forecasts = new ArrayList<>();

        MiForecast miForecast = miWeather.getForecast();

        //TODO Forecast中的日期和星期还需要修改
        String[] weathers1 = splitWeather(miForecast.getWeather1());
        int[] temps1 = splitTemperature(miForecast.getTemp1());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather1(), weathers1[0],
                weathers1[1], temps1[0], temps1[1], miForecast.getWind1(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        String[] weathers2 = splitWeather(miForecast.getWeather2());
        int[] temps2 = splitTemperature(miForecast.getTemp2());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather2(), weathers2[0],
                weathers2[1], temps2[0], temps2[1], miForecast.getWind2(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        String[] weathers3 = splitWeather(miForecast.getWeather3());
        int[] temps3 = splitTemperature(miForecast.getTemp3());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather3(), weathers3[0],
                weathers3[1], temps3[0], temps3[1], miForecast.getWind3(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        String[] weathers4 = splitWeather(miForecast.getWeather4());
        int[] temps4 = splitTemperature(miForecast.getTemp4());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather4(), weathers4[0],
                weathers4[1], temps4[0], temps4[1], miForecast.getWind4(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        String[] weathers5 = splitWeather(miForecast.getWeather5());
        int[] temps5 = splitTemperature(miForecast.getTemp5());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather5(), weathers5[0],
                weathers5[1], temps5[0], temps5[1], miForecast.getWind5(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        String[] weathers6 = splitWeather(miForecast.getWeather6());
        int[] temps6 = splitTemperature(miForecast.getTemp6());
        forecasts.add(new Forecast(miForecast.getCityId(), miForecast.getWeather6(), weathers6[0],
                weathers6[1], temps6[0], temps6[1], miForecast.getWind6(), miForecast.getDate(), convertDataToWeek(miForecast.getDate())));

        return forecasts;
    }

    @Override
    public List<LifeIndex> getLifeIndexes() {

        List<LifeIndex> lifeIndexes = new ArrayList<>();
        int cityId = miWeather.getForecast().getCityId();

        for (MiIndex miIndex : miWeather.getIndexList()) {
            lifeIndexes.add(new LifeIndex(cityId, miIndex.getName(), miIndex.getIndex(), miIndex.getDetails()));
        }

        return lifeIndexes;
    }

    @Override
    public AQI getAQI() {
        return new AQI(miWeather.getForecast().getCityId(), miWeather.getAqi().getAqi(),
                miWeather.getAqi().getPm25(), miWeather.getAqi().getPm10(),
                miWeather.getAqi().getPublishTime(), miWeather.getAqi().getSo2(),
                miWeather.getAqi().getNo3(), miWeather.getAqi().getSrc());
    }

    @Override
    public Weather getWeather() {
        Weather weather = new Weather();
        weather.setCityId(getCityId());
        weather.setCityName(getCityName());
        weather.setCityNameEn(getCityNameEn());
        weather.setAqi(getAQI());
        weather.setForecasts(getForecasts());
        weather.setLifeIndexes(getLifeIndexes());
        weather.setRealTime(getRealTime());
        return weather;
    }

    /**
     * 拆分天气
     *
     * @param weather 如：晴转多云
     * @return {"晴", "多云"}
     */
    private String[] splitWeather(String weather) {

        if (weather == null) {
            return new String[]{"", ""};
        }
        if (weather.contains("转")) {
            return weather.split("转");
        } else {
            return new String[]{weather, weather};
        }
    }

    /**
     * 拆分气温
     *
     * @param temperature 如：5℃~-3℃
     * @return {5, 3}
     */
    private int[] splitTemperature(String temperature) {
        if (temperature != null && temperature.contains("~")) {
            if (temperature.contains("℃")) {
                temperature = temperature.replaceAll("℃", "");
            }
            String[] temps = temperature.split("~");
            return new int[]{Integer.parseInt(temps[0]), Integer.parseInt(temps[1])};
        } else {
            return null;
        }
    }

    /**
     * 日期转星期
     *
     * @param data 如：
     * @return 周一，周二，....
     */
    private String convertDataToWeek(String data) {
        //TODO 日期转星期

        return "周一";
    }
}
