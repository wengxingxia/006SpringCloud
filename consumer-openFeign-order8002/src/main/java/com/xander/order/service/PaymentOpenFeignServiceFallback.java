package com.xander.order.service;

import com.xander.entities.CommonResult;
import com.xander.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * Description: paymentService微服务的 OpenFeign 接口
 *
 * @author Xander
 * datetime: 2021-05-21 10:00
 */
@Component
public class PaymentOpenFeignServiceFallback implements PaymentOpenFeignService {

    @Override
    public CommonResult<Payment> getPaymentById(String id) {
        return CommonResult.newInstance().setCode(200).setMessage("openFeign fallback 降级处理：getPaymentById");
    }

    @Override
    public CommonResult<Payment> timeout() {
        return CommonResult.newInstance().setCode(200).setMessage("openFeign fallback 降级处理：timeout");
    }

    @Override
    public CommonResult<Payment> excep() {
        return CommonResult.newInstance().setCode(200).setMessage("openFeign fallback 降级处理：excep");
    }
}

