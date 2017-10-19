package design.pattern.observer.subobject;

/**
 * 观察者
 *
 * @author pc
 * @since 2015年10月9日 下午3:20:45
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
