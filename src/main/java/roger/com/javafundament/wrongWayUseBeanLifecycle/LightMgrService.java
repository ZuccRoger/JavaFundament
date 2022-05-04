package roger.com.javafundament.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: 骆佳俊
 * @date: 2022/4/30 5:15 PM
 */
@Component
public class LightMgrService {
  @Autowired private LightService lightService;

  @Test
  public void LightMgrService() {
    lightService.check();
  }
}
