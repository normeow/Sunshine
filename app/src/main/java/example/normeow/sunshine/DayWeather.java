package example.normeow.sunshine;

/**
 * Created by Admin on 15.06.2015.
 */
public class DayWeather {

    //todo enums: wind_direction, weather, weather_description for details for setting icons

    private double highTemperature;
    private double lowTemperature;
    private String weather;
    private String weatherDescription;
    private double humidity;
    private double pressure;
    private double wind_speed;
    private String wind_direction;

    public DayWeather(){}

    public DayWeather(String day, String weather, String weatherDescription, double highTemperature, double lowTemperature, double humidity, double pressure, double wind_speed, double wind_deg){
        this.day = day;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind_speed = wind_speed;
        this.wind_direction = defineDirection(wind_deg);
    }

    //todo
    private String defineDirection(double wind_deg) {
        return "N";
    }

    public DayWeather(String day, String weather, String weatherDescription, double highTemperature, double lowTemperature){
        this.day = day;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getHighTemperatureMetric() {
        return Math.round(highTemperature);
    }


    public double getHighTemperatureImperial() {
        return Math.round((highTemperature * 1.8) + 32);
    }

    public void setHighTemperature(double highTemperature) {
        this.highTemperature = highTemperature;
    }

    public double getLowTemperatureMetric() {
        return Math.round(lowTemperature);
    }
    public double getLowTemperatureImperial() {
        return Math.round((lowTemperature * 1.8) + 32);
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


}
