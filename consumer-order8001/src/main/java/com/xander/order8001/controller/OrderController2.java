package com.xander.order8001.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:30
 */
@RestController
@RequestMapping("/order2")
@DefaultProperties(defaultFallback = "defaultFallbackMethod")//配置类级别默认的降级处理方法
public class OrderController2 {
    private Logger logger = LoggerFactory.getLogger(OrderController2.class);
    public static final String PAYMENT_URL = "http://PAYMENTSERVICE";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 模拟请求超时，Hystrix进行降级
     *
     * @return
     */
    @GetMapping(value = "/hystrix/timeout")
    //配置降级处理方法
    @HystrixCommand(fallbackMethod = "fallback4Timeout", commandProperties = {
            // 设置超时时间为2s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")}
    )
    public CommonResult<Payment> hystrixTimeout() {
        this.logger.info("hystrixTimeout()--开始请求----" + LocalDateTime.now() + "---" + Thread.currentThread().getName() + "---" + Thread.currentThread().getId());
        CommonResult result = this.restTemplate.getForObject(PAYMENT_URL + "/payment/timeout", CommonResult.class);
        this.logger.info("hystrixTimeout()--响应结果----" + LocalDateTime.now());
        return result;
    }


    /**
     * 超时降级处理方法
     *
     * @return
     */
    public CommonResult<Payment> fallback4Timeout() {
        return CommonResult.newInstance().setCode(500).setMessage("对响应超时进行降级处理...");
    }

}
