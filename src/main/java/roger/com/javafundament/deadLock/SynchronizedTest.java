package roger.com.javafundament.deadLock;

/**
 * @author: 骆佳俊
 * @date: 2022/5/4 3:34 PM
 *     <p>2个线程 2个变量 相互持有且不释放对方需要的 要加锁
 */
public class SynchronizedTest {
  public static void main(String[] args) {
    SynchronizedTest synchronizedTest = new SynchronizedTest();
    synchronizedTest.test();
  }

  public void test() {
    Integer numA = 1;
    Integer numB = 2;

    Thread a =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                synchronized (numA) {
                  System.out.println("AAA");
                  try {
                    Thread.sleep(3 * 1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  synchronized (numB) {
                    System.out.println("CCC");
                  }
                }
              }
            },
            "A");
    a.start();

    Thread b =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                synchronized (numB) {
                  System.out.println("BBB");
                  try {
                    Thread.sleep(3 * 1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  synchronized (numA) {
                    System.out.println("DDD");
                  }
                }
              }
            },
            "B");
    b.start();
  }
}
