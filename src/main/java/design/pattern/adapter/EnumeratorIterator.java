package design.pattern.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 枚举Iterator适配器
 * <p>
 * <p>
 * 适配器模式:将一个类的接口,转换成客户期望的另一个接口.适配器让原本接口不兼容的类可以合作无间
 * </p>
 *
 * @author YuChuanQi
 * @since 2015年11月25日 下午3:11:43
 */
public class EnumeratorIterator implements Iterator<Object> {
    private Enumeration<?> enumer;

    public EnumeratorIterator(Enumeration<?> enumer) {
        this.enumer = enumer;
    }

    @Override
    public boolean hasNext() {
        return enumer.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumer.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
