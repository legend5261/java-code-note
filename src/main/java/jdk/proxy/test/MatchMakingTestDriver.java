package jdk.proxy.test;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import jdk.proxy.invocation.OwnerInvocationHandler;
import jdk.proxy.person.PersonBean;
import jdk.proxy.personImpl.FileAnnotation;
import jdk.proxy.personImpl.PersonBeanImpl;


/**
 * 动态代理
 * <p>
 * Java已经为我们创建了Proxy类,所以需要有办法来告诉Proxy类你要做什么.你不能像以前一样把代码放入Proxy类中,
 * 因为Proxy不是你直接实现的.放在哪里?放在InvocationHandler中,InvocationHandler的工作是响应代理的任何调用.
 * 可以把InvocationHandler想成是代理收到方法调用后,请求做实际工作的对象.
 * </p>
 *
 * @author YuChuanQi
 * @since 2015年11月28日 下午4:27:17
 */
public class MatchMakingTestDriver {

    public static void main(String[] args) throws Exception {
        MatchMakingTestDriver driver = new MatchMakingTestDriver();
        driver.drive();
    }

    public void drive() {
        PersonBean joe = new PersonBeanImpl();
        /*******              for test             ********/
        String name = joe.getClass().getName();
        Class<?>[] interfaces = joe.getClass().getInterfaces();
        Field[] fields = joe.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.print(" " + field.getAnnotation(FileAnnotation.class).value());
        }
        System.out.println(name);
        System.out.println(interfaces[0].getName());
        /**************************************************/

        PersonBean ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is : " + joe.getName());
        ownerProxy.setInterests("blowing, Go");
        System.out.println("Interest set from owner jdk.proxy");
        try {
            ownerProxy.setHotOrNorRating(10);
        } catch (Exception ex) {
            System.out.println("Can't set rating from owner jdk.proxy");
            ex.printStackTrace();
        }
        System.out.println("Rating is : " + ownerProxy.getHotOrNotRating());
    }

    public PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass()
                .getInterfaces(), new OwnerInvocationHandler(personBean));
    }
}
