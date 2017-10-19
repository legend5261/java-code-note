package design.pattern.strategy.test;

import design.pattern.strategy.DuckImpl.MallardDuck;
import design.pattern.strategy.DuckImpl.ModelDuck;
import design.pattern.strategy.behaviorImpl.FlyRocketPowered;
import design.pattern.strategy.superDuck.Duck;

/**
 * 鸭子案例
 * 策略(pattern)模式：定义了算法族,分别封装起来,让它们之间可以相互替换,此模式让算法的变化独立于使用算法的客户
 *
 * @author YuChuanQi
 * @since 2015年10月9日 上午11:42:42
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();
        mallard.display();
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
