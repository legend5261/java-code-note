package design.pattern.observer.subobjectImpl;

import java.util.Observable;
import java.util.Observer;

/**
 * 主题
 *
 * @author YuChuanQi
 * @since 2015年10月9日 下午4:06:21
 */
public class WeatherData extends Observable {
    /**
     * 气温
     */
    private float temprature;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 压强
     */
    private float pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temprature, float humidity, float pressure) {
        this.temprature = temprature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void registerObserver(Observer ob) {
        this.addObserver(ob);
    }

    public float getTemprature() {
        return temprature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
