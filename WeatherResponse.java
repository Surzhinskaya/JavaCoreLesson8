package Homework8;

import java.util.Objects;


public class WeatherResponse  {
    private String cityName;
    private String cityId;
    private String minTemp;
    private String maxTemp;
    private String weather;
    private String ptime;




    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityid() {
        return cityId;
    }

    public void setCityid(String cityId) {

        this.cityId = cityId;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String MinTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }


    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public WeatherResponse (String cityName, String minTemp, String maxTemp, String ptime) {
        this.cityName=cityName;
        this.ptime=ptime;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ptime);
    }
 @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse that = (WeatherResponse) o;
        return Objects.equals(ptime, that.ptime) ;


    }




    public String toString(){

        return  "City: " + cityName+ ". Data: " + ptime + ". MinMaxTemp{" +
                "minTemp=" + minTemp  +
                ", maxTemp=" + maxTemp  +
                '}';
    }
}
