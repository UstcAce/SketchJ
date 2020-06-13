package singleton;

/**
 * 懒汉式，线程安全
 * 通过类锁保证多线程并发调用
 * 缺点：必须加锁 synchronized 才能保证单例，效率很低，99% 情况下不需要同步。
 * 100个线程调用getInstance()，类锁要求它们逐个执行。但是如果instance非空的情况下，不需要加锁。
 * getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
