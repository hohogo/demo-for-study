package cn.gaohaohao.hystrix.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    public List getOrderList() {
        log.info("查询订单列表");
        return new ArrayList<String>(0);
    }

}
