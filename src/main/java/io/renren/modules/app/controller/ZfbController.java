/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.controller;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;

import io.renren.common.utils.R;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * APP登录授权
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app/zfb")
@Api("支付宝业务接口")
public class ZfbController {

    //读取支付宝的appId
    @Value("${application.alipay.app.app-id}")
    private String app_appId;

    //读取支付宝的公钥
    @Value("${application.alipay.app.public-key}")
    private String app_publicKey;

    //读取支付宝的私钥
    @Value("${application.alipay.app.private-key}")
    private String app_privateKey;

    //读取支付宝的网关（都在resouces中）
    @Value("${application.alipay.gateway}")
    private String gateway;

    @Autowired
    private UserService userService;



    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/appPayOrder")
    @ApiOperation("App程序付款")
    @ResponseBody
    public R appPayOrder(@RequestBody UserEntity userEntity) {
        UserEntity user = new UserEntity();
        //由user实体获取user_id
        int user_id = userEntity.getUser_id();
        //获取充值金额
        String amount = String.valueOf(user.getMoney());

        try {
            AlipayClient client = new DefaultAlipayClient(
                    gateway,
                    app_appId,
                    app_privateKey,
                    "json",
                    "UTF-8",
                    app_publicKey,
                    "RSA2"
            );
            //请求对象（商户向支付宝的请求）
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            ////请求对象中需要添加的模型
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody("我是测试数据");
            model.setSubject("App支付测试Java");
            model.setOutTradeNo("a_b_c123");//这是订单号
            model.setTimeoutExpress("30m");
            model.setTotalAmount(amount);
            model.setProductCode("QUICK_MSECURITY_PAY");
            request.setNotifyUrl("http://www.demo.com");
            request.setBizModel(model);

            AlipayTradeAppPayResponse response = client.sdkExecute(request);
            if (response.isSuccess()) {
                //返回客户端的为支付宝认可的字符串
                return R.ok().put("orderString", response.getBody());
            } else {
                return R.error("支付订单创建失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("支付宝支付模块故障");
        }

    }







}
