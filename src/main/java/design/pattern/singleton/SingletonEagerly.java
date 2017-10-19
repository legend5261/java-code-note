package design.pattern.singleton;

/**
 * 2.使用'急切'创建实例,而不用延迟实例化的做法.
 * <p>
 * 如果程序总是创建并使用单例实例,获得在创建和运行时方面的负担不太繁重的情况.
 * <p>
 *
 * @author YuChuanQi
 * @since 2015年10月12日 下午2:34:24
 */
public class SingletonEagerly {
    private static SingletonEagerly uniqueInstance = new SingletonEagerly();

    private SingletonEagerly() {
    }

    ;

    public static synchronized SingletonEagerly getInstance() {
        return uniqueInstance;
    }
}
