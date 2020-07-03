package cn.gzhennaxia.test.jvm;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单4-9
 *
 * @author bo li
 * @date 2020-06-28 15:18
 */
public class ThreadDeadLockTest {

    static class SynAddRunnable implements Runnable {

        private int a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}
