package roger.com.javafundament.controller;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: 骆佳俊
 * @date: 2022/5/26 7:53 AM
 */
public class ConcurrentHashMapLoopTest {
  private static int LOOP_COUNT = 10000000;
  private static int THREAD_COUNT = 10;
  private static int ITEM_COUNT = 10;

  @Test
  public void test() throws InterruptedException {
    Map<String, Long> normaluseResult = normaluse();
    System.out.println(normaluseResult);
    Map<String, Long> gooduse = gooduse();
    System.out.println(gooduse);
  }

  private Map<String, Long> normaluse() throws InterruptedException {
    StopWatch stopWatch = new StopWatch("任务耗时秒表工具");
    stopWatch.start("task1");
    ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      // 获得一个随机的Key
                      String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                      synchronized (freqs) {
                        if (freqs.containsKey(key)) {
                          // Key存在则+1
                          freqs.put(key, freqs.get(key) + 1);
                        } else {
                          // Key不存在则初始化为1
                          freqs.put(key, 1L);
                        }
                      }
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    stopWatch.stop();
    System.out.println(stopWatch.getTotalTimeMillis());
    System.out.println(stopWatch.prettyPrint());
    return freqs;
  }

  private Map<String, Long> gooduse() throws InterruptedException {
    StopWatch stopWatch = new StopWatch("任务耗时秒表工具2");
    stopWatch.start();
    ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                      // 利用computeIfAbsent()方法来实例化LongAdder，然后利用LongAdder来进行线程安全计数
                      freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    // 因为我们的Value是LongAdder而不是Long，所以需要做一次转换才能返回
    stopWatch.stop();
    System.out.println(stopWatch.getTotalTimeMillis());
    System.out.println(stopWatch.prettyPrint());
    return freqs.entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().longValue()));
  }
}
