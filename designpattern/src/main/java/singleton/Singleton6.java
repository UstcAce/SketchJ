package singleton;

/**
 * 建枚举实例的过程是线程安全的，所以这种写法也没有同步的问题
 */
public enum Singleton6 {
    INSTANCE;
    public void fun1() {
        // do something
    }
}
//        Singleton6.INSTANCE.fun1();
