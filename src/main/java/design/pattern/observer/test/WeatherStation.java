package design.pattern.observer.test;

import design.pattern.observer.subobjectImpl.CurrentConditionsDisplay;
import design.pattern.observer.subobjectImpl.WeatherData;


/**
 * 气象站案例
 * <p>
 * 观察者(observer)模式：定义了对象之间的一对多依赖,这样一来,当一个对象状态发生改变时,它的所有依赖者都会收到通知并自动更新.
 * <p>
 *
 * @author YuChuanQi
 * @since 2015年10月10日 下午5:35:58
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        CurrentConditionsDisplay c1 = new CurrentConditionsDisplay();
        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(c1);
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println(15 >> 1);
        System.out.println(1 ^ 3);
    }
}
