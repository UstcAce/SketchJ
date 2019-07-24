package pattern;

import java.util.function.Consumer;

/**
 * 模板方法：需要采用某个算法的框架，同时又希望有一定的灵活度，能对它的某些部分进行改进。
 * 简单说就是希望使用这个算法，但是需要对其中的某些行进行改进，才能达到希望的效果。
 *
 *
 * 如果一个类中没有包含足够的信息来描绘一个具体的对象，这样的类就是抽象类。
 * 抽象类除了不能实例化对象之外，类的其它功能依然存在，成员变量、成员方法和构造方法的访问方式和普通类一样。
 * 由于抽象类不能实例化对象，所以抽象类必须被继承，才能被使用。也是因为这个原因，通常在设计阶段决定要不要设计抽象类。
 * 父类包含了子类集合的常见的方法，但是由于父类本身是抽象的，所以不能使用这些方法。
 * 在Java中抽象类表示的是一种继承关系，一个类只能继承一个抽象类，而一个类却可以实现多个接口。
 */
public abstract class BankingPattern {
    public void processCustomer(int id) {
        String customer = "customer:" + id;
        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(String customer);

    public void processCustomer(int id, Consumer<String> makeCustomerHappy) {
        String customer = "customer:" + id;
        makeCustomerHappy.accept(customer);
    }
}
