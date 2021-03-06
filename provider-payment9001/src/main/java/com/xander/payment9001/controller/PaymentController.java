package com.xander.payment9001.controller;

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
        logger.info("paymentService getPaymentById开始，id=" + id);
        if (id != null && id % 2 == 0) {
            Payment payment = Payment.newInstance().setSerial(UUID.randomUUID().toString()).setId(id);
            CommonResult result = CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(payment);
            logger.info("paymentService getPaymentById结束，id=" + id);
            return result;
        } else {
            CommonResult result = CommonResult.newInstance().setCode(444).setMessage("没有对应记录,查询ID: " + id).setData(null);
            logger.info("paymentService getPaymentById结束，id=" + id);
            return result;
        }
    }

    /**
     * 模拟请求异常
     *
     * @return
     */
    @GetMapping(value = "/excep")
    public CommonResult<Payment> excep() {
        logger.info("paymentService excep 开始-----" + LocalDateTime.now());
        //模拟业务处理异常
        int div = 1 / 0;
        CommonResult result = CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(null);
        logger.info("paymentService excep 结束-----" + LocalDateTime.now());
        return result;
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
     * 模拟请求随机异常
     *
     * @return
     */
    @GetMapping(value = "/randomExcep")
    public CommonResult<Payment> randomExcep() {
        this.logger.info("randomExcep-----" + LocalDateTime.now());
        if (Math.random() > 0.5d) {
            //模拟业务处理异常
            int div = 1 / 0;
        }
        return CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(null);
    }


}
