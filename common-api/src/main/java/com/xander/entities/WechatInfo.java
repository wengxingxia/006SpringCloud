package com.xander.entities;

/**
 * Description: 模拟微信用户信息
 *
 * @author Xander
 * datetime: 2021-05-14 16:24
 */
public class WechatInfo {

    private String userName;
    
    private String openId;
    
    public static WechatInfo newInstance() {
        WechatInfo instance = new WechatInfo();
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public WechatInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public WechatInfo setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
