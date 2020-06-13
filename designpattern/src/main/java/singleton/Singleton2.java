package singleton;

/**
 * 饿汉式，线程安全
 * 缺点：未使用就加载，浪费内存
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return instance;
    }
}
