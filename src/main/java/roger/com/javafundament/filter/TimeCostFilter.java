package roger.com.javafundament.filter;

/**
 * @author: 骆佳俊
 * @date: 2022/5/5 9:18 AM
 */
// @WebFilter
// @Slf4j
public class TimeCostFilter
//        implements Filter
{
  public TimeCostFilter() {
    System.out.println("construct");
  }

  //  @Override
  //  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
  //      throws IOException, ServletException {
  //    log.info("开始计算接口耗时");
  //    long start = System.currentTimeMillis();
  //    chain.doFilter(request, response);
  //    long end = System.currentTimeMillis();
  //    long time = end - start;
  //    System.out.println("执行时间(ms)：" + time);
  //  }
}
