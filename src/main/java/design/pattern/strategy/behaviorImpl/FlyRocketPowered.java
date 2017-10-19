package design.pattern.strategy.behaviorImpl;

import design.pattern.strategy.behavior.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket !");
    }

}
