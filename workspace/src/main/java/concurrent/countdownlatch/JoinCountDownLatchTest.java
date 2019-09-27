package concurrent.countdownlatch;

public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finish");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });
        parser1.start();
        parser2.start();

        // join用于当前执行线程等待join线程parser1执行结束
        // 其实现原理是不停检查join线程是否存活，如果join线程parser1存活则让当前线程永远等待下去
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}