package cn.stylefeng.guns.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 中百微信渠道对账
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinChannelCheckController extends BaseController {

    public static final String PREFIX = "/weixin/";

    /**
     * 默认的 对账结果查看页
     * @return
     */
    @RequestMapping("")
    public String weixinCheckPage(){
        return PREFIX + "user.html";
    }




    /**
     * 手工操作对账 获取对账结果
     * @return
     */
    @RequestMapping("/manual/check")
    @ResponseBody
    public String manualCheck(){
        return "success";
    }
}
