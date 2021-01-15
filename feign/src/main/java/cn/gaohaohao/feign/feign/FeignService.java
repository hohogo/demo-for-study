package cn.gaohaohao.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("my-first-consul-service")
@Component
public interface FeignService {
    @RequestMapping(value = "/hello")
    public String hello(); // 如果有参数, 必须用RequestParam注解

}