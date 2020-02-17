package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.BankDetail;
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
public interface BankDetailDao {

	BankDetail getById(Long id);
	
	List<BankDetail> query(@Param(value = "vo") BankDetail vo, @Param(value = "customSort") String customSort);
	
	int insert(BankDetail entity);
	
	int update(BankDetail entity);

	int delete(BankDetail entity);

	int insertBatch(@Param("list") List<BankDetail> bankDetailsList);

	List<BankDetail> listBankDetail(String fileBatchId);
}
