package design.pattern.singleton;

/**
 * 1.同步getInstance()方法
 * <p>
 * 如果程序可以接受getInstance()造成的额外负担,getInstance()的性能对应用程序不是很关键,就什么也不做(即同步). 同步一个方法会造成程序执行效率下降
 * <p>
 *
 * @author YuChuanQi
 * @since 2015年10月12日 下午2:26:00
 */
public class Singleton {
    private static Singleton uniqueInstance;
    private int num = 0;

    private Singleton() {
    }

    ;

    /**
     * 延迟实例化,当用到的时候才实例化单例对象.
     */
    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // 其它方法...

    public int count() {
        return num++;
    }
}
