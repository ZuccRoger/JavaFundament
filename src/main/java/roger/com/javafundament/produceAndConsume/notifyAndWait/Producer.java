package roger.com.javafundament.produceAndConsume.notifyAndWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;

/**
 * @author: 骆佳俊
 * @date: 2022/4/19 8:06 AM
 */
public class Producer extends Thread {
  private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  private Queue<Integer> queue;
  private int maxSize;
  private int pc = 1;

  public Producer(Queue<Integer> queue, int maxSize, String threadName) {
    super(threadName);
    this.queue = queue;
    this.maxSize = maxSize;
  }

  @Override
  public void run() {
    super.run();
    while (true) {
      try {
        Thread.sleep(new Random().nextInt(2));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      /** 在条件判断之前给共享资源加锁 */
      synchronized (queue) {
        while (queue.size() == maxSize) {
          try {
            logger.info("消息队列已满: 生产者线程调用wait方法进入等待状态 ...");
            queue.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        int messageId = pc++;
        logger.info("生产消息:{} ", messageId);
        queue.add(messageId);
        queue.notify();
      }
    }
  }
}
