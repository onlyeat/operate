package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.modular.entity.CheckDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/20/0:50
 * @Description:
 */
@Repository
public interface CheckDetailDao {
	int insertBatch(@Param("list") List<CheckDetail> checkDetailList);

	List<CheckDetail> listCheckDetail();
}
