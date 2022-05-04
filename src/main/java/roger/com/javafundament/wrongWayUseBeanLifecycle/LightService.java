package roger.com.javafundament.wrongWayUseBeanLifecycle;

import org.springframework.stereotype.Service;

/**
 * @author: 骆佳俊
 * @date: 2022/4/30 5:15 PM
 */
//@Service
public class LightService {
  public void start() {
    System.out.println("turn on all lights");
  }

  public void shutdown() {
    System.out.println("turn off all lights");
  }

  public void check() {
    System.out.println("check all lights");
  }
}
