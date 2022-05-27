package roger.com.javafundament.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: 骆佳俊
 * @date: 2022/5/5 9:42 AM
 */
// @Component
public class DemoFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      // 模拟异常
      System.out.println("Filter 处理中时发生异常");
      throw new RuntimeException();
    } catch (Exception e) {
      chain.doFilter(request, response);
    }
    chain.doFilter(request, response);
  }
}
