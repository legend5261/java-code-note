package design.pattern.singleton;

/**
 * 3.用'双重检查锁',在getInstance中减少使用同步
 * <p>
 * 利用双重检查锁,首先检查是否实例已经创建了.如果未创建,'才'进行同步.这样一来,只有第一次会同步.
 * <p>
 * <p>
 * 如果性能是关心的重点,这种做法可以大大减少getInstance()时间耗费.
 * <p>
 *
 * @author YuChuanQi
 * @since 2015年10月12日 下午2:37:47
 */
public class SingletonDoubleLock {
    /**
     * volatile关键字取保:当<code>uniqueInstance<code>变量被初始化成SingletonDobleLock实例时,多个线程正确地处理uniqueInstance变量
     */
    private volatile static SingletonDoubleLock uniqueInstance;
    private int num = 0;

    private SingletonDoubleLock() {
    }

    public static SingletonDoubleLock getInstance() {
        if (uniqueInstance == null) {
            synchronized (SingletonDoubleLock.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SingletonDoubleLock();
                }
            }
        }
        return uniqueInstance;
    }

    public int count() {
        return ++num;
    }
}
