package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.dao.MerchantDao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("merchantService")
public class MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    public Page<Map<String,Object>> queryList(String name, String code) {
        Page page = LayuiPageFactory.defaultPage();
        return merchantDao.queryList(page, name, code);
    }
}
