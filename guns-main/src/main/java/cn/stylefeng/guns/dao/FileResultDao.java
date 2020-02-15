package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.FileResult;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
@Repository
public interface FileResultDao {

	public FileResult getById(Long id);
	
//	public List<FileResult> query(@Param(value = "vo") FileResultQuery vo, @Param(value = "customSort") String customSort);
	
	public int insert(FileResult entity);
	
	public int update(FileResult entity);

	public int delete(FileResult entity);
}
