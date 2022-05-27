package roger.com.javafundament.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author: 骆佳俊
 * @date: 2022/5/25 8:31 PM
 */
@RestController
public class ThreadLocalTestController {
  private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTestController.class);
  private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);
  private static int THREAD_COUNT = 10;
  private static int ITEM_COUNT = 1000;

  @GetMapping("/wrong")
  public Map wrong(Integer userId) {
    String before = Thread.currentThread().getName() + ":" + currentUser.get();
    currentUser.set(userId);
    String after = Thread.currentThread().getName() + ":" + currentUser.get();
    HashMap<Object, Object> map = new HashMap(16);
    map.put("before", before);
    map.put("after", after);
    return map;
  }

  /** 用来获得一个指定元素数量模拟数据的 ConcurrentHashMap */
  private ConcurrentHashMap<String, Long> getData(int count) {
    return LongStream.rangeClosed(1, count)
        .boxed()
        .collect(
            Collectors.toConcurrentMap(
                i -> UUID.randomUUID().toString(),
                Function.identity(),
                (o1, o2) -> o1,
                ConcurrentHashMap::new));
  }

  @GetMapping("/concurrentHashMapWrong")
  public String concurrentHashMapWrong() throws InterruptedException {
    ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
    logger.info("init size : {}", concurrentHashMap.size());
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(
                    i -> {
                      synchronized (concurrentHashMap) {
                        int gap = ITEM_COUNT - concurrentHashMap.size();
                        logger.info("gap size:{}", gap);
                        concurrentHashMap.putAll(getData(gap));
                      }
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    logger.info("finish size {}", concurrentHashMap.size());
    return "ok";
  }
}
