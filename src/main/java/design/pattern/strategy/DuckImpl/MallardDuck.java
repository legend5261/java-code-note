package design.pattern.strategy.DuckImpl;

import design.pattern.strategy.behaviorImpl.FlyWithWings;
import design.pattern.strategy.behaviorImpl.Quack;
import design.pattern.strategy.superDuck.Duck;


public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real marllard duck ");
    }

}
