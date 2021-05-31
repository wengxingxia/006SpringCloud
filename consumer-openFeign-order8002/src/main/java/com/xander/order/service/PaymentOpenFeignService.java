package com.xander.order.service;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description: paymentService微服务的 OpenFeign 接口
 *
 * @author Xander
 * datetime: 2021-05-21 10:00
 */
@FeignClient(value = "paymentService",fallback = PaymentOpenFeignServiceFallback.class)
public interface PaymentOpenFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") String id);

    /**
     * 模拟请求超时
     *
     * @return
     */
    @GetMapping(value = "/payment/timeout")
    CommonResult<Payment> timeout();

    /**
     * 模拟请求异常
     *
     * @return
     */
    @GetMapping(value = "/payment/excep")
    CommonResult<Payment> excep();
}

