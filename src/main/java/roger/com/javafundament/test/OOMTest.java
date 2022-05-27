package roger.com.javafundament.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * @author: 骆佳俊
 * @date: 2022/5/26 9:16 AM
 */
public class OOMTest {
  private static final Logger log = LoggerFactory.getLogger(OOMTest.class);

  private void printStats(ThreadPoolExecutor threadPool) {
    Executors.newSingleThreadScheduledExecutor()
        .scheduleAtFixedRate(
            () -> {
              log.info("=========================");

              log.info("Pool Size: {}", threadPool.getPoolSize());

              log.info("Active Threads: {}", threadPool.getActiveCount());

              log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());

              log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

              log.info("=========================");
            },
            0,
            1,
            TimeUnit.SECONDS);
  }

  @Test
  public void test() throws InterruptedException {
    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    // 打印线程池的信息，稍后我会解释这段代码

    for (int i = 0; i < 100000000; i++) {

      threadPool.execute(
          () -> {
            String payload =
                IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining(""))
                    + UUID.randomUUID().toString();

            try {

              TimeUnit.HOURS.sleep(1);

            } catch (InterruptedException e) {

            }
          });
    }

    threadPool.shutdown();

    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }
}
