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
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/order")
@DefaultProperties(defaultFallback = "defaultFallbackMethod")//配置类级别默认的降级处理方法
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    public static final String PAYMENT_URL = "http://PAYMENTSERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") String id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * 服务提供者请求异常，级联故障
     *
     * @return
     */
    @GetMapping(value = "/excep")
    public CommonResult<Payment> excep() {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/excep", CommonResult.class);
    }

    /**
     * 服务提供者请求超时，级联故障
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping(value = "/timeout")
    public CommonResult<Payment> timeout() {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/timeout", CommonResult.class);
    }

    /**
     * 模拟请求异常，Hystrix进行降级
     *
     * @return
     */
    @GetMapping(value = "/hystrix/excep")
    @HystrixCommand(fallbackMethod = "fallback4Excep")//配置降级处理方法
    public CommonResult<Payment> hystrixExcep() {
        this.logger.info("hystrixExcep()--开始请求----" + LocalDateTime.now() + "---" + Thread.currentThread().getName());
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/excep", CommonResult.class);
    }

    /**
     * 模拟请求超时，Hystrix进行降级
     *
     * @return
     */
    @GetMapping(value = "/hystrix/timeout")
    //配置降级处理方法
    @HystrixCommand(fallbackMethod = "fallback4Timeout", threadPoolKey = "OrderController", commandProperties = {
            // 设置超时时间为2s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")}
    )
    public CommonResult<Payment> hystrixTimeout() {
        this.logger.info("hystrixTimeout()--开始请求----" + LocalDateTime.now() + "---" + Thread.currentThread().getName() + "---" + Thread.currentThread().getId());
        CommonResult result = this.restTemplate.getForObject(PAYMENT_URL + "/payment/timeout", CommonResult.class);
        this.logger.info("hystrixTimeout()--响应结果----" + LocalDateTime.now());
        return result;
    }

    /**
     * 服务提供者请求异常，级联故障
     *
     * @return
     */
    @GetMapping(value = "/randomExcep")
    @HystrixCommand(fallbackMethod = "fallback4Excep", commandProperties = {
            //是否开启断路器，默认是true
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 此属性设置统计滚动窗口的持续时间（以毫秒为单位）。默认是10000
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000"),
            // 将使电路跳闸的最小请求数。例如，默认是20
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
            // 设置电路跳闸后拒绝请求的时间，默认是5000
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            //失败率达到多少后跳闸，默认是50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
    })
    public CommonResult<Payment> randomExcep() {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/randomExcep", CommonResult.class);
    }

    /**
     * 模拟线程池隔离，当线程池满负荷后，直接进行降级处理，达到限流的效果
     *
     * @return
     */
    @GetMapping(value = "/hystrix/threadpool")
    //配置降级处理方法
    @HystrixCommand(fallbackMethod = "defaultFallbackMethod", threadPoolKey = "OrderController", commandProperties = {
            // 设置超时时间为5s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
    },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1")
            }
    )
    public CommonResult<Payment> hystrixThreadpool() {
        this.logger.info("hystrixThreadpool()--开始请求----" + LocalDateTime.now() + "---" + Thread.currentThread().getName() + "---" + Thread.currentThread().getId());
        CommonResult result = this.restTemplate.getForObject(PAYMENT_URL + "/payment/timeout", CommonResult.class);
        this.logger.info("hystrixThreadpool()--响应结果----" + LocalDateTime.now());
        return result;
    }

    /**
     * 异常降级处理方法
     *
     * @return
     */
    public CommonResult<Payment> fallback4Excep() {
        return CommonResult.newInstance().setCode(500).setMessage("客户端服务降级处理...");
    }

    /**
     * 超时降级处理方法
     *
     * @return
     */
    public CommonResult<Payment> fallback4Timeout() {
        return CommonResult.newInstance().setCode(500).setMessage("对响应超时进行降级处理...");
    }

    /**
     * 演示类级别默认的降级处理生效
     *
     * @return
     */
    @GetMapping(value = "/defaultFallback")
    @HystrixCommand //不配置降级方法，则使用 类级别默认的降级处理方法
    public CommonResult<Payment> defaultFallback() {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/timeout", CommonResult.class);
    }

    /**
     * 类级别默认的降级处理方法
     *
     * @return
     */
    public CommonResult<Payment> defaultFallbackMethod() {
        return CommonResult.newInstance().setCode(500).setMessage("类级别默认的降级处理方法...");
    }

}
