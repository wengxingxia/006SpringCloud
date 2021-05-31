package com.xander.payment9002.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 22:47
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        if (id != null && id % 2 == 0) {
            Payment payment = Payment.newInstance().setSerial(UUID.randomUUID().toString()).setId(id);
            return CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(payment);
        } else {
            return CommonResult.newInstance().setCode(444).setMessage("没有对应记录,查询ID: " + id).setData(null);
        }
    }

    /**
     * 模拟请求超时
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping(value = "/timeout")
    public CommonResult<Payment> timeout() throws InterruptedException {
        this.logger.info("timeout-----" + LocalDateTime.now());
        //模拟业务处理时间，线程睡眠3s
        TimeUnit.SECONDS.sleep(3);
        return CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(null);
    }


    /**
     * 模拟请求异常
     *
     * @return
     */
    @GetMapping(value = "/excep")
    public CommonResult<Payment> excep() {
        this.logger.info("excep-----" + LocalDateTime.now());
        //模拟业务处理异常
        int div = 1 / 0;
        return CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(null);
    }
}
