package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.BankDetail;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
public interface BankDetailDao {

	public BankDetail getById(Long id);
	
//	public List<BankDetail> query(@Param(value = "vo") BankDetailQuery vo, @Param(value = "customSort") String customSort);
	
	public int insert(BankDetail entity);
	
	public int update(BankDetail entity);

	public int delete(BankDetail entity);
}
