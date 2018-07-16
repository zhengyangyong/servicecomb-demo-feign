package io.demo;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "hello")
@RequestMapping(path = "/")
public class HelloImpl implements Hello {

  @Override
  @GetMapping(path = "/hello")
  public String sayHello() {
    //模拟超时
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //正常返回
    return "hello";

    //模拟微服务在注册中心中有，实际已经掉线或死掉，由于我们的注册中心是长连接实时推送微服务状态变化，这种情况会很少见
    //throw new InvocationException(CONSUMER_INNER_STATUS, "sim error");

    //模拟服务器处理抛出异常处理失败
    //throw new ServiceCombException("sim error");
  }

  @Override
  @GetMapping(path = "/hi")
  public String sayHi() {
    return "hi";
  }
}
