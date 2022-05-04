package roger.com.javafundament.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  /** 构造函数 */
  public MyBeanFactoryPostProcessor() {
    super();
    System.out.println("Spring容器准备阶段: 这是BeanFactoryPostProcessor实现类构造器！");
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
    System.out.println("Spring容器准备阶段: BeanFactoryPostProcessor调用postProcessBeanFactory方法");
    BeanDefinition bd = arg0.getBeanDefinition("person");
    bd.getPropertyValues().addPropertyValue("phone", "110");
  }
}
