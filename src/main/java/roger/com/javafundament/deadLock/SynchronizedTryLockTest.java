package roger.com.javafundament.deadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 骆佳俊
 * @date: 2022/5/4 4:05 PM
 */
public class SynchronizedTryLockTest {
  public static void main(String[] args) {
    SynchronizedTryLockTest synchronizedTest = new SynchronizedTryLockTest();
    synchronizedTest.test();
  }

  public void test() {
    ReentrantLock lockA = new ReentrantLock();
    ReentrantLock lockB = new ReentrantLock();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  if (lockA.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("AAA");
                    if (lockB.tryLock(3, TimeUnit.SECONDS)) {
                      System.out.println("BBB");
                    }
                  }
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  if (lockA.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("CCC");
                    if (lockB.tryLock(3, TimeUnit.SECONDS)) {
                      System.out.println("DDD");
                    }
                  }
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
