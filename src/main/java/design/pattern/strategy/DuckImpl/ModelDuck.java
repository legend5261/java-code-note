package design.pattern.strategy.DuckImpl;

import design.pattern.strategy.behaviorImpl.FlyNoWay;
import design.pattern.strategy.behaviorImpl.Quack;
import design.pattern.strategy.superDuck.Duck;

public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck ");
    }
}
