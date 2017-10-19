package design.pattern.decorate.condiment;

import design.pattern.decorate.beverage.Beverage;


/**
 * 调料超类
 *
 * @author YuChuanQi
 * @since 2015年10月10日 下午6:02:32
 */
public abstract class CondimentDecorator extends Beverage {
    @Override
    public abstract String getDescription();
}
