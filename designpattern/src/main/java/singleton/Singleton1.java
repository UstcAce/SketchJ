package singleton;

/**
 * 懒汉式，线程不安全。
 * 缺点：当多线程工作的时候，如果有多个线程同时运行到if (instance == null)，都判断为null，
 * 那么两个线程就各自会创建一个实例——这样一来，就不是单例了。
 */
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
