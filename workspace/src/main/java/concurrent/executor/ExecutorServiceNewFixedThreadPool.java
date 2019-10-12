package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建了一个可以容纳10个线程的线程池，然后向executor()方法中传递一个异步的Runnable接口的实现，
 * 这样ExecutorService中的某个线程执行了这个Runnable的任务
 */
public class ExecutorServiceNewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i=0; i<20; i++) {
            final int ii = i;
            Runnable r = () -> {
                System.out.println("Asynchronous task:" + ii);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.submit(r);
        }

        executorService.shutdown();
    }
}
