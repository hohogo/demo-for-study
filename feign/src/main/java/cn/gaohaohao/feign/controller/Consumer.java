package cn.gaohaohao.feign.controller;

import cn.gaohaohao.feign.feign.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class Consumer {

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignService feignService;

    @GetMapping(value = "/getServices")
    public Object getServices() {
        return discoveryClient.getInstances("my-first-consul-service");
    }

    // 轮询的选择同服务(来自不同的客户注册中心,IP不同)
    @GetMapping(value = "/chooseService")
    public Object chooseService() {
        return loadBalancerClient.choose("my-first-consul-service").getUri().toString();
    }

    // -------------------------- ribbon --------------------------
    @GetMapping(value = "/ribbon-call")
    public String ribbonCall() {
        String method = "hello";
        return restTemplate.getForEntity("http://my-first-consul-service/" + method, String.class).getBody();
    }

    // -------------------------- Feign --------------------------
    @GetMapping(value = "/feign-call")
    public String feignCall() {
        return feignService.hello();
    }

}