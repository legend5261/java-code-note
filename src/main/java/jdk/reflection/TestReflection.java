package jdk.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jdk.proxy.person.PersonBean;
import jdk.proxy.personImpl.PersonBeanImpl;

public class TestReflection {

    public static void main(String[] args) throws Exception {
        /** 1.**/
        Display disp = new Display();
        Class<?> cls = disp.getClass();

        Method method = cls.getMethod("show", String.class);
        Field[] f = cls.getDeclaredFields();
        System.out.println(cls.getInterfaces());
        System.out.println(f);
        System.out.println(method);
        method.invoke(disp, "lee");
        /** 2.**/
        Class<?> cls1 = PersonBean.class;
        String name = cls1.getName();
        String packageName = cls1.getPackage().getName();
        String cls1Name = name.substring(name.lastIndexOf(".") + 1, name.length());
        String implName = packageName + "Impl." + cls1Name + "Impl";
        System.out.println("implName " + implName);
        Class<?> clzz = Class.forName(implName);

        PersonBeanImpl person = (PersonBeanImpl) clzz.newInstance();
        Method[] methods = clzz.getMethods();
        System.out.println(methods);
        methods[0].invoke(person, "YuChuanQi");
        System.out.println(person);
        /** 3. **/
        PersonBeanImpl bean = ReflectionUtils.newInstance(PersonBeanImpl.class);
        bean.setName("haha");
        System.out.println(bean.getName());
    }
}

class Display {
    private String name;
    private String age;

    public void show(String name) {
        System.out.println(name);
    }
}
