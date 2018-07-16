package io.demo;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerApplication {

  @RpcReference(microserviceName = "demo-producer-service", schemaId = "hello")
  private Hello hello;

  public String sayHello() {
    return hello.sayHello();
  }

  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();

    ConsumerApplication consumer = BeanUtils.getBean("consumerApplication");
    System.out.println("ConsumerApplication Call = " + consumer.sayHello());
  }
}
