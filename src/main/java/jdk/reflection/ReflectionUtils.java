package jdk.reflection;

import java.lang.reflect.Constructor;

/**
 * 反射工具类,用于获得反射对象(study from hadoop src {@code org.apache.hadoop.util.ReflectionUtils})
 *
 * @author YuChuanQi
 * @since 2016年1月8日 下午5:30:42
 */
public class ReflectionUtils {
    private static final Class<?>[] EMPTY_CLASS = new Class[]{};

    public static <T> T newInstance(Class<T> clazz) {
        T result;
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(EMPTY_CLASS);
            constructor.setAccessible(true);
            result = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error!");
        }
        return result;
    }
}
