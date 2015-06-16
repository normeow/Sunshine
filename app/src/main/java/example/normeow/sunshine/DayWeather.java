package example.normeow.sunshine;

/**
 * Created by Admin on 15.06.2015.
 */
public class DayWeather {

    private double highTemperature;
    private double lowTemperature;
    private String weather;
    private double humidity;
    private double pressure;
    private double wind;

    public DayWeather(){}

    public DayWeather(String day, String weather, double highTemperature, double lowTemperature, double humidity, double pressure, double wind){
        this.day = day;
        this.weather = weather;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
    }

    public DayWeather(String day, String weather, double highTemperature, double lowTemperature){
        this.day = day;
        this.weather = weather;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
    }

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getHighTemperatureMetric() {
        return highTemperature;
    }


    public double getHighTemperatureImperial() {
        return Math.round((highTemperature * 1.8) + 32);
    }

    public void setHighTemperature(double highTemperature) {
        this.highTemperature = highTemperature;
    }

    public double getLowTemperatureMetric() {
        return lowTemperature;
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

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }


}
