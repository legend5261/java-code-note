package design.pattern.decorate.beverageimpl;

import design.pattern.decorate.beverage.Beverage;


public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }

}
