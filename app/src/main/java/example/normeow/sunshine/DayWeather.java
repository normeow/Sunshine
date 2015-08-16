package example.normeow.sunshine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 15.06.2015.
 */
public class DayWeather implements Parcelable{

    //todo enums: wind direction, weather, weather_description for details for setting icons

    private String day;
    private String date;
    private String weather;
    private String weatherDescription;
    private String windDirection;
    private String units;
    //in the temperature fields saved metrics data
    private double highTemperature;
    private double lowTemperature;
    private double humidity;
    private double pressure;
    //m/sec
    private double wind_speed;



    //ids of weather pics
    private int artIconId;
    private int blackIconId;

    public DayWeather(){}

    public DayWeather(String day, String date, String weather, String weatherDescription, double highTemperature, double lowTemperature, double humidity, double pressure, double wind_speed, double wind_deg){
        this.units = "metric";
        this.day = day;
        this.date = date;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind_speed = wind_speed;
        this.windDirection = defineDirection(wind_deg);
        this.setIcons();
    }



    private String defineDirection(double wind_deg) {
        final MyApplication mInstance = MyApplication.getInstance();
        if ((wind_deg <= 11.25) || (wind_deg >= 348.75))
            return mInstance.getResources().getString(R.string.north);
        if ((wind_deg > 11.25) && (wind_deg < 78.75))
            return  mInstance.getResources().getString(R.string.north_east);
        if ((wind_deg > 78.75) && (wind_deg < 101.25))
            return  mInstance.getResources().getString(R.string.east);;
        if ((wind_deg > 101.25) && (wind_deg < 168.75))
            return  mInstance.getResources().getString(R.string.south_east);
        if ((wind_deg >168.75) && (wind_deg < 191.25))
            return  mInstance.getResources().getString(R.string.south);
        if ((wind_deg >191.25) && (wind_deg < 258.75))
            return  mInstance.getResources().getString(R.string.south_west);
        if ((wind_deg >258.75) && (wind_deg < 281.25))
            return  mInstance.getResources().getString(R.string.west);
        else
            return  mInstance.getResources().getString(R.string.north_west);
    }

    public DayWeather(String day, String weather, String weatherDescription, double highTemperature, double lowTemperature){
        this.day = day;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.setIcons();
    }

    private DayWeather(Parcel in){
        String[] strs = new String[6];
        in.readStringArray(strs);
        this.day = strs[0];
        this.date = strs[1];
        this.weather = strs[2];
        this.weatherDescription = strs[3];
        this.windDirection = strs[4];
        this.units = strs[5];

        double[] doubles = new double[5];
        in.readDoubleArray(doubles);
        this.highTemperature = doubles[0];
        this.lowTemperature = doubles[1];
        this.humidity = doubles[2];
        this.pressure = doubles[3];
        this.wind_speed = doubles[4];

        int[] images = new int[2];
        in.readIntArray(images);
        this.artIconId = images[0];
        this.blackIconId = images[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.day, this.date, this.weather, this.weatherDescription, this.windDirection, this.units});
        dest.writeDoubleArray(new double[]{this.highTemperature, this.lowTemperature, this.humidity, this.pressure, this.wind_speed});
        dest.writeIntArray(new int[]{this.artIconId, this.blackIconId});
    }

    public static final Parcelable.Creator<DayWeather> CREATOR = new Parcelable.Creator<DayWeather>(){
        @Override
        public DayWeather createFromParcel(Parcel source) {
            return new DayWeather(source);
        }

        @Override
        public DayWeather[] newArray(int size) {
            return new DayWeather[size];
        }
    };
    private void setIcons(){
        //todo set image, use not description, but "clouds", "rain" and so on!
        switch (weather) {
            case "Clear":
                artIconId = R.drawable.art_clear;
                blackIconId = R.drawable.ic_clear;
                break;
            case "Clouds":
                artIconId = R.drawable.art_clouds;
                blackIconId = R.drawable.ic_cloudy;
                break;
            case "Rain":
                artIconId = R.drawable.art_rain;
                blackIconId = R.drawable.ic_rain;
                break;
            case "Fog":
                artIconId = R.drawable.art_fog;
                blackIconId = R.drawable.ic_fog;
                break;
        }
    }

    public int getArtIconId(){
        return artIconId;
    }

    public int getBlackIconId(){
        return blackIconId;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getHighTemperature() {
        if (ForecastFragment.unitsType.equals("metric"))
            return Math.round(highTemperature);
        else if (ForecastFragment.unitsType.equals("imperial"))
            return Math.round((highTemperature * 1.8) + 32);
        return 0;
    }

    public double getLowTemperature() {
        if (ForecastFragment.unitsType.equals("metric"))
            return Math.round(lowTemperature);
        else if (ForecastFragment.unitsType.equals("imperial"))
            return Math.round((lowTemperature * 1.8) + 32);
        return 0;
    }

    public void setHighTemperature(double highTemperature) {
        this.highTemperature = highTemperature;
    }

    public void setLowTemperature(double lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
