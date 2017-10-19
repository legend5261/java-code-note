package design.pattern.decorate.condimentimpl;

import design.pattern.decorate.beverage.Beverage;
import design.pattern.decorate.condiment.CondimentDecorator;

/**
 * 摩卡 (调料)
 *
 * @author pc
 * @since 2015年10月11日 上午9:24:10
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }

}
