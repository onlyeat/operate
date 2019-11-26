package cn.stylefeng.guns.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中百微信渠道对账
 */
@RestController("weixin")
public class WeixinChannelCheck {

    /**
     * 手工操作对账 获取对账结果
     * @return
     */
    @PostMapping("manual/check")
    public String manualCheck(){
        return "success";
    }
}
