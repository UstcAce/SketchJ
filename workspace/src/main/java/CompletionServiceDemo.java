import java.util.ArrayList;
import java.util.concurrent.*;

public class CompletionServiceDemo {
    // 声明CompletionService中的线程池executorService
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 某一个线程报错，后面的线程都无法从get处返回结果
     */
    public static void main(String[] args) {
        CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService);

        final int groupNum = 10;
        for (int i=1; i<=groupNum; i++) {
            final long ii = i;
            completionService.submit(new Callable<Long>() {
                public Long call() throws Exception {
                    if (ii != 10) {
                        return ii;
                    } else {
                        new ArrayList<Integer>().get(0);
                        return 0l;
                    }
                }
            });
        }

        long result = 0l;
        try {
            for (int i=1; i<=groupNum; i++) {
                result += completionService.take().get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
        executorService.shutdown();
    }
}
