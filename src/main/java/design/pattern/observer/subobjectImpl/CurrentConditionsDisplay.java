package design.pattern.observer.subobjectImpl;

import java.util.Observable;
import java.util.Observer;
import design.pattern.observer.subobject.DisplayElement;

/**
 * 观察者1
 *
 * @author pc
 * @since 2015年10月9日 下午3:44:48
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temprature;
    private float humidity;

    @Override
    public void display() {
        System.out.println("Current conditions : " + temprature + "F degrees and " + humidity + "%");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.temprature = weatherData.getTemprature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

}
