package com.xander.payment9002.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 22:47
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

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
}
