package concurrent.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 方法 invokeAll() 会调用存在于参数集合中的所有Callable对象，并返回Future对象的List
 * 可以通过这个集合来操作每个Callable的执行结果
 */
public class ExecutorServiceInvokeAll {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<>();

        Callable<String> c1 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 1000) {

            }
            return "Task1";
        };

        Callable<String> c2 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 2000) {

            }
            return "Task2";
        };

        Callable<String> c3 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 3000) {

            }
            return "Task3";
        };

        callables.add(c1);
        callables.add(c2);
        callables.add(c3);

//        callables.add(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "Task 1";
//            }
//        });
//
//        callables.add(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "Task 2";
//            }
//        });
//
//        callables.add(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "Task 3";
//            }
//        });
        // 这里包含多线程任务的开启以及所有线程任务的结束及返回
        // TODO: 这里集合中Future的结果和callables的顺序不一定是对应的
        List<Future<String>> result = executorService.invokeAll(callables);

        for (Future<String> future : result) {
            System.out.println("result = " + future.get());
        }

        executorService.shutdown();
    }
}
