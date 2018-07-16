package io.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.servicecomb.bizkeeper.FallbackPolicy;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.Response;
import org.springframework.stereotype.Component;

//扩展一个FallbackPolicy策略，用于Consumer，等价于Feign的fallback class
@Component
public class MyFallbackPolicy implements FallbackPolicy {

  private Map<String, Map<String, Function<Invocation, Response>>> processor = new HashMap<>();

  public MyFallbackPolicy() {
    processor.put("demo-producer-service", new HashMap<>());
    //对demo-producer-service的sayHello调用如果失败，返回value from FallbackPolicy
    processor.get("demo-producer-service").put("sayHello", invocation -> Response.succResp("value from FallbackPolicy"));
  }

  @Override
  public String name() {
    return "myFallbackPolicy";
  }

  @Override
  public Response getFallbackResponse(Invocation invocation) {
    return processor.get(invocation.getMicroserviceName()).get(invocation.getOperationName()).apply(invocation);
  }
}
