package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.FileResult;
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
public interface FileResultDao {

	FileResult getById(Long id);
	
	List<FileResult> query(@Param(value = "vo") FileResult vo, @Param(value = "customSort") String customSort);

	int insert(FileResult entity);
	
	int update(FileResult entity);

	int delete(FileResult entity);
}
