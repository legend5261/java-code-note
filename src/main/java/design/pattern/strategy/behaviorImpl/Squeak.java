package design.pattern.strategy.behaviorImpl;

import design.pattern.strategy.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak !!");
    }
}
