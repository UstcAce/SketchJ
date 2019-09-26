package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(6);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();


        Runnable r = () -> {
            System.out.println(3);
            c.countDown();
            System.out.println(4);
            c.countDown();
        };

        r.run();

        Callable<String> call =() -> {
            System.out.println(5);
            c.countDown();
            System.out.println(6);
            c.countDown();
            return "return callable result by lambda!";
        };
        try {
            System.out.println(call.call());
        } catch (Exception e) {
            e.printStackTrace();
        }

        c.await();
        System.out.println("End");
    }
}