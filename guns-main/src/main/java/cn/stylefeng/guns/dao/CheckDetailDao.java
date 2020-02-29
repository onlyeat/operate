package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.modular.entity.CheckDetail;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

	Page<Map<String,Object>> listCheckDetailByCondition(@Param("page") Page page,
														@Param("merchantNo") String merchantNo,
														@Param("clearDate") String clearDate,
														@Param("indexNo") String indexNo,
														@Param("tradeChannel") String tradeChannel,
														@Param("checkStatus") Integer checkStatus);
}
