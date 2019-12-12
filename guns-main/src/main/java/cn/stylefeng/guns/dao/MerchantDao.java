package cn.stylefeng.guns.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MerchantDao {


    Page<Map<String,Object>> queryList(@Param("page") Page page,
                                       @Param("name") String name,
                                       @Param("code") String code);
}
