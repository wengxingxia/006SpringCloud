package com.xander.order8001.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:30
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    public static final String PAYMENT_URL = "http://PAYMENTSERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") String id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
