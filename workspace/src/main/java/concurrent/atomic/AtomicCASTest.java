package concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCASTest {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    /**
     * 非线程安全的计数器
     */
    private void count() {
        i++;
    }

    /**
     * 使用CAS实现线程安全的计数器
     */
    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            // 比较atomicI当前值是否和i一致，一致则赋值i+1，并返回True
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicCASTest cas = new AtomicCASTest();
        List<Thread> ts = new ArrayList<>();
        for (int j=0; j <100; j++) {
            Runnable r = () -> {
              for (int i=0; i<10000; i++) {
                  cas.count();
                  cas.safeCount();
              }
            };

            ts.add(new Thread(r));
        }

        for (Thread t : ts) {
            t.start();
            // 如果在这个地方加join，则两种输出结果相同
            // t.join();
        }

        for (Thread t : ts) {
            // 主线程join所有线程，等待所有线程执行完成
            t.join();
        }

        // 输出结果
        // original i:991419
        // CAS atomic i:1000000
        System.out.println("original i:" + cas.i);
        System.out.println("CAS atomic i:" + cas.atomicI.get());
    }
}
