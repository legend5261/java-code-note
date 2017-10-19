package design.pattern.strategy.superDuck;

import design.pattern.strategy.behavior.FlyBehavior;
import design.pattern.strategy.behavior.QuackBehavior;


/**
 * 鸭子超类
 *
 * @author pc
 * @since 2015年10月9日 上午11:44:48
 */
public abstract class Duck {
    /**
     * 飞行行为
     */
    public FlyBehavior flyBehavior;
    public QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float,even decoys!");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
