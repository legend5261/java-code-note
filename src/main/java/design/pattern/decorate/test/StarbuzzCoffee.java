package design.pattern.decorate.test;

import design.pattern.decorate.beverage.Beverage;
import design.pattern.decorate.beverageimpl.Espresso;
import design.pattern.decorate.beverageimpl.HouseBlend;
import design.pattern.decorate.condimentimpl.Mocha;

/**
 * 星吧兹咖啡案例
 * <p>
 * 装饰者模式：动态的将责任附加到对象上,若要扩展功能,装饰者提供了比继承更有弹性的替代方案.
 * <p>
 * <p>
 * 本例中调料为装饰者,用来装饰不同种类的咖啡.
 * eg:一杯DarkRoast(咖啡-被装饰者),里边放入Mocha(调料-装饰者)
 * <p>
 *
 * @author YuChuanQi
 * @since 2015年10月11日 上午9:34:56
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Mocha(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());
    }
}
