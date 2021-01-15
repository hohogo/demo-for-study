package cn.gaohaohao.feign.controller.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudRule {

    @Autowired
    IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config){
        return  new RoundRobinRule();
    }
}