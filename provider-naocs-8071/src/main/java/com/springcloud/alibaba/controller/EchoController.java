package com.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-06-08 17:37
 */
@RestController
public class EchoController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return "Hello Nacos Discovery " + serverPort + ", " + msg;
    }

}
