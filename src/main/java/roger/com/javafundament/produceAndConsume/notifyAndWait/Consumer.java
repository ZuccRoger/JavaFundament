package roger.com.javafundament.produceAndConsume.notifyAndWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * @author: 骆佳俊
 * @date: 2022/4/19 8:13 AM
 */
public class Consumer extends Thread {
  private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
  private Queue<Integer> queue;

  public Consumer(Queue<Integer> queue, String threadName) {
    super(threadName);
    this.queue = queue;
  }

  @Override
  public void run() {
    super.run();
    while (true) {
      synchronized (queue) {
        while (queue.isEmpty()) {
          try {
            logger.info("消息队列为空: 消费者线程调用wait方法进入等待状态 ...");
            queue.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        logger.info("消费信息:{}", queue.remove());
        queue.notify();
      }
    }
  }
}
