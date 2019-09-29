package concurrent.atomic;

import java.util.concurrent.CountDownLatch;

public class AtomicTest1 {
//    public static int count = 0;

    // 保证变量在线程间可见，对volatile变量的缩影写操作都能立即反应到其他线程中，
    // 简单一句话概括volatile就是：缓存失效，禁止重排序
    // 然而自增运算（i++）不是原子性的，它实际上包含（1）获取i值；（2）i值增加1
    public static volatile int count = 0;

    public static void increase() {
        count ++;
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i=0; i<threadCount; i++) {
            Thread thread = new Thread(new ThreadRunner(latch), "" + i);
            thread.start();
        }
        // CountDownLatch.await()用于保证Thread都执行完才输出count
        latch.await();
        // 结果始终小于50000
        System.out.println("count:" + count);
    }

    static class ThreadRunner implements Runnable {
        private CountDownLatch latch;

        public ThreadRunner(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            for (int i=0; i<10000; i++) {
                increase();
            }
        }
    }
}
