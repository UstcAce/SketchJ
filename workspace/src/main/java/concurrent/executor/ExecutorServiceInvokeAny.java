package concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 方法 invokeAny() 会调用存在于参数集合中的所有Callable对象. 调用该方法不会返回Future对象，而是集合中某个Callable对象的结果
 * 而且无法保证调用之后返回的是哪一个Callable对象的结果，只知道是某个执行结束的Callable的结果。
 */
public class ExecutorServiceInvokeAny {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<Callable<String>> callables = new ArrayList<>();

        Callable<String> c1 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 300) {

            }
            return "Task1";
        };

        Callable<String> c2 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 200) {

            }
            return "Task2";
        };

        Callable<String> c3 = () -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) < 100) {

            }
            return "Task3";
        };

        callables.add(c1);
        callables.add(c2);
        callables.add(c3);

        String result = executorService.invokeAny(callables);
        System.out.println("result = " + result);
        executorService.shutdown();
    }
}
