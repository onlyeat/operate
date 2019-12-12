package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.service.MerchantService;
import cn.stylefeng.guns.sys.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    public static final String PREFIX = "/modular/merchant/";

    @RequestMapping("")
    public String index(){
        return PREFIX + "merchant.html";
    }

    @RequestMapping("/queryList")
    public LayuiPageInfo queryList(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "code", required = false) String code){

            Page<Map<String, Object>> users = merchantService.queryList(name, code);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
    }
}
