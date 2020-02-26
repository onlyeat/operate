package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.BusinessDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
@Repository
public interface BusinessDetailDao {

	BusinessDetail getById(Long id);

	List<BusinessDetail> query(@Param(value = "vo") BusinessDetail vo, @Param(value = "customSort") String customSort);
	
	int insert(BusinessDetail entity);
	
	int update(BusinessDetail entity);

	int delete(BusinessDetail entity);

	int insertBatch(List<BusinessDetail> businessDetail);

	List<BusinessDetail> listBusinessDetail(@Param("fileBatchId") String fileBatchId, @Param("clearDate") String clearDate);
}
