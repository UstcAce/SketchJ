package singleton;

/**
 * https://www.jianshu.com/p/eb30a388c5fc
 * 懒汉式，线程安全
 * 通过volatile关键字禁止了指令重排序，它的写操作就会有一个内存屏障（什么是内存屏障？），这样，在它的赋值完成之前，就不用会调用读操作
 */
public class Singleton4 {
    private static volatile Singleton4 instance;

    private Singleton4() {
    }

    public Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
