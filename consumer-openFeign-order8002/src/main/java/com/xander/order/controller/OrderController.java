package com.xander.order.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import com.xander.order.service.PaymentOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") String id) {
        return this.paymentOpenFeignService.getPaymentById(id);
    }

    /**
     * 模拟请求超时接口
     *
     * @return
     */
    @GetMapping(value = "/timeout")
    public CommonResult<Payment> timeout() {
        return this.paymentOpenFeignService.timeout();
    }

    /**
     * 模拟请求异常接口，用于演示 openFeign 的fallback属性配置服务降级处理
     *
     * @return
     */
    @GetMapping(value = "/excep")
    public CommonResult<Payment> excep() {
        return this.paymentOpenFeignService.excep();
    }
}
