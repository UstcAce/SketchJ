package strategy;

/**
 * 策略模式代表了解决一类算法的通用解决方案，你可以在运行时选择使用哪种方案
 * 例如：使用不同的条件（比如苹果的重量，或者颜色）来筛选库存中的苹果，
 * 使用不同的标准来验证输入的有效性，
 * 使用不同的方式来分析或者格式化输入。
 */
@FunctionalInterface
public interface ValidationStrategy {
    boolean excute(String s);
}
