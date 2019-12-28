package cn.stylefeng.guns.service;

import cn.stylefeng.guns.BaseTest;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public class MerchantServiceTest extends BaseTest {

    @Autowired
    private MerchantService merchantService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listMerchants() {
        Page<Map<String, Object>> mapPage = merchantService.listMerchants("", "");
        logger.info(JSON.toJSONString(mapPage));
    }
}