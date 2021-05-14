package com.xander.wechat.controller;

import com.xander.entities.CommonResult;
import com.xander.entities.WechatInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 22:47
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/getByUserId/{userId}")
    public CommonResult<WechatInfo> getWechatInfo(@PathVariable("userId") Long userId) {
        WechatInfo wechatInfo = WechatInfo.newInstance().setOpenId(userId + "-openId").setUserName(userId + "-name");
        return CommonResult.newInstance().setCode(200).setMessage("查询成功,serverPort：" + serverPort).setData(wechatInfo);
    }
}
