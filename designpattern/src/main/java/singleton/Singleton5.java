package singleton;

/**
 * 线程安全，懒汉式
 * 1.如果多线程都调用了getInstance()方法，在类还没加载的情况下，会先触发类的加载。
 * 2.在类加载过程的“初始化”步骤，会执行类构造器＜clinit＞()方法。＜clinit＞()方法是由编译器自动收集类中的所有类变量的赋值动作和
 * 静态语句块（static{}块）中的语句合并产生的。
 * 3.JVM会保证一个类的＜clinit＞()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，
 * 那么只会有一个线程去执行这个类的＜clinit＞()方法。
 * 4.这样便保证了线程安全。
 */
public class Singleton5 {
    private static class SingletonHolder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    private Singleton5() {
    }

    public static final Singleton5 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
