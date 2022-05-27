package roger.com.javafundament.test;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: 骆佳俊
 * @date: 2022/5/26 8:41 AM
 */
public class ThreadLocalRandomTest {
  @Test
  public void test() {
    int i = ThreadLocalRandom.current().nextInt();
    System.out.println(i);
  }
}
