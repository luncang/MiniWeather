package com.example.administrator.miniweather.database.dao;

import android.content.Context;

import com.example.administrator.miniweather.database.CityDatabaseHelper;
import com.example.administrator.miniweather.models.City;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


/**
 * City表操作类
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/3/13
 */
public class CityDao {

    private Dao<City, Integer> cityDaoOperation;

    public CityDao(Context context) {

        this.cityDaoOperation = CityDatabaseHelper.getInstance(context).getCityDao(City.class);
    }

    /**
     * 查询表中的所有城市
     *
     * @return 城市列表数据
     */
    public List<City> queryCityList() {

        try {
            return cityDaoOperation.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据城市查询城市信息
     *
     * @param cityId 城市ID
     * @return city
     * @throws SQLException
     */
    public City queryCityById(String cityId) throws SQLException {

        QueryBuilder<City, Integer> queryBuilder = cityDaoOperation.queryBuilder();
        queryBuilder.where().eq(City.CITY_ID_FIELD_NAME, cityId);

        return queryBuilder.queryForFirst();
    }
}
