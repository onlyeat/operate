package cn.stylefeng.guns.service;

import cn.stylefeng.guns.dao.CheckDetailDao;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/20/0:46
 * @Description:
 */
@Service
@Slf4j
public class CheckDetailService {

	@Autowired
	private CheckDetailDao checkDetailDao;

	public int insertBatch(List<CheckDetail> checkDetailList){
		if (CollectionUtils.isEmpty(checkDetailList)) {
			return 0;
		}
		return checkDetailDao.insertBatch(checkDetailList);
	}

	public Page<Map<String, Object>> queryList(String clearDate, String indexNo, String tradeChannel, Integer checkStatus) {
		return null;
	}
}
