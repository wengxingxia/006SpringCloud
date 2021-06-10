package com.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-06-08 17:45
 */
@RestController
public class ConsumerController {
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/consumer/echo/{msg}")
    public String callEcho(@PathVariable String msg) {
        // 访问应用 service-provider 的 REST "/echo/{msg}"
        return this.restTemplate.getForObject("http://service-provider/echo/" + msg, String.class);
    }
}
