package com.xander.user.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.WechatInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-14 16:39
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String WECHAT_SERVICE = "wechatService";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/wechatInfo/{userId}")
    public CommonResult<WechatInfo> wechatInfo(@PathVariable("userId") Long userId) {
        List<ServiceInstance> instances = this.discoveryClient.getInstances(WECHAT_SERVICE);
        // 打印服务实例信息
        for (ServiceInstance instance : instances) {
            this.logger.info("serviceId: " + instance.getServiceId() + "\t" + "host: " + instance.getHost() + "\t"
                    + "port: " + instance.getPort() + "\t" + "uri: " + instance.getUri());
        }
        return this.restTemplate.getForObject("http://" + WECHAT_SERVICE + "/wechat/getByUserId/" + userId, CommonResult.class);
    }
}
