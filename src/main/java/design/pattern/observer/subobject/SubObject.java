package design.pattern.observer.subobject;

/**
 * 主题
 *
 * @author pc
 * @since 2015年10月9日 下午3:20:28
 */
public interface SubObject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
