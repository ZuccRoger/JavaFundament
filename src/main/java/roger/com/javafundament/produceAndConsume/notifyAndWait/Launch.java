package roger.com.javafundament.produceAndConsume.notifyAndWait;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: 骆佳俊
 * @date: 2022/4/19 8:46 AM
 */
public class Launch {
  public static void main(String[] args) {
    Queue<Integer> queue = new LinkedList<>();
    int maxSize = 5;
    new Producer(queue, maxSize, "producer-thread").start();
    new Consumer(queue, "consumer-thread").start();
  }
}
