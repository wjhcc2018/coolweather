package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
    *解析和处理服务器返回的省级的数据
    * */
    public static boolean handleProvinceResponse (String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setId(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     * */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
  /**
   * 解析和处理服务器返回的县级数据
   * */
  public static boolean handleContryResponse(String response,int cityId){
      if(!TextUtils.isEmpty(response)){
          try {
              JSONArray allContries = new JSONArray(response);
              for(int i=0;i<allContries.length();i++){
                  JSONObject countryObject = allContries.getJSONObject(i);
                  County county = new County();
                  county.setCountyName(countryObject.getString("name"));
                  county.setId(countryObject.getInt("id"));
                  county.setCityId(cityId);
                  county.setWeatherId(countryObject.getString("weather_id"));
                  county.save();
              }
              return true;
          }catch (JSONException e){
              e.printStackTrace();
          }
      }
      return false;
  }
}
