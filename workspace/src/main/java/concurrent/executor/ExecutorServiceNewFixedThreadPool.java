package concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 创建了一个可以容纳10个线程的线程池，然后向executor()方法中传递一个异步的Runnable接口的实现，
 * 这样ExecutorService中的某个线程执行了这个Runnable的任务
 */
public class ExecutorServiceNewFixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

//        submitRunnable(executorService);

        submitCallable(executorService);

        executorService.shutdown();
    }

    public static void submitRunnable(ExecutorService executorService) throws ExecutionException, InterruptedException {
        List<Future> futures = new ArrayList<>();
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
            /**
             * ExecutorService中submit和execute的区别:
             * 1.execute 是java5之前，submit是之后
             * 2.submit有返回值，而execute没有。submit Runnable会返回Future对象，但是get返回null.
             * https://www.cnblogs.com/wanqieddy/p/3853863.html
             */
            Future future =  executorService.submit(r);
            futures.add(future);
//            executorService.execute(r);
        }

        for (int i=0; i<20; i++) {
            Future future = futures.get(i);
            // 这里加get才会阻塞至线程任务完成
            System.out.println("Task " + i + " result:" + future.get());
            System.out.println("Task " + i + ":" + futures.get(i).isDone());
        }
    }

    public static void submitCallable(ExecutorService executorService) throws ExecutionException, InterruptedException {
        List<Future> futures = new ArrayList<>();
        for (int i=0; i<20; i++) {
            final int ii = i;
            Callable<String> c = () -> {
                String result = "Asynchronous task:" + ii;
                System.out.println(result);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return result;
            };
            /**
             * ExecutorService中submit和execute的区别:
             * 1.execute 是java5之前，submit是之后
             * 2.submit有返回值，而execute没有。submit Runnable会返回Future对象，但是get返回null.
             * https://www.cnblogs.com/wanqieddy/p/3853863.html
             */
            Future future =  executorService.submit(c);
            futures.add(future);
        }

        for (int i=0; i<20; i++) {
            Future future = futures.get(i);
            // 这里加get才会阻塞至线程任务完成
            System.out.println("Task " + i + " result:" + future.get());
            System.out.println("Task " + i + ":" + futures.get(i).isDone());
        }
    }
}
