package concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest2 {
    public static AtomicInteger count = new AtomicInteger(0);

    /**
     * getAndIncrement: 返回旧值（即+1前的原始值）
     * incrementAndGet: 返回新值(即+1后的新值)
     */
    public static void increase() {
        count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i=0; i<threadCount; i++) {
            Thread thread = new Thread(new ThreadRunner(latch), "" + i);
            thread.start();
        }

        latch.await();

        // 输出结果按预期为50000
        System.out.println(count);
    }

    static class ThreadRunner implements Runnable {
        private CountDownLatch latch;

        public ThreadRunner(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            // 如果latch.countDown放在这个地方，则输出结果始终小于50000
            // latch.countDown();
            for (int i=0; i<10000; i++) {
                increase();
            }
            latch.countDown();
        }
    }
}
