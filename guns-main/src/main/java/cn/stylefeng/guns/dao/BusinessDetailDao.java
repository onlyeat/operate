package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.BusinessDetail;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
public interface BusinessDetailDao {

	public BusinessDetail getById(Long id);
	
//	public List<BusinessDetail> query(@Param(value = "vo") BusinessDetailQuery vo, @Param(value = "customSort") String customSort);
	
	public int insert(BusinessDetail entity);
	
	public int update(BusinessDetail entity);

	public int delete(BusinessDetail entity);
}
