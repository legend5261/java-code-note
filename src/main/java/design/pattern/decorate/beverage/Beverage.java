package design.pattern.decorate.beverage;

/**
 * 饮料超类
 *
 * @author YuChuanQi
 * @since 2015年10月10日 下午6:00:34
 */
public abstract class Beverage {
    protected String description = "Unknow Beverage";

    public abstract double cost();

    public String getDescription() {
        return description;
    }
}
