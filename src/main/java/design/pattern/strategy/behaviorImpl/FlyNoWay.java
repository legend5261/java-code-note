package design.pattern.strategy.behaviorImpl;

import design.pattern.strategy.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("i cant't fly !!");
    }

}
