package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.dao.MerchantDao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    public Page<Map<String,Object>> listMerchants(String name, String code) {
        Page page = LayuiPageFactory.defaultPage();
        return merchantDao.listMerchants(page, name, code);
    }

}
