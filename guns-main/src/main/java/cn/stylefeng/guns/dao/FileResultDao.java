package cn.stylefeng.guns.dao;


import cn.stylefeng.guns.modular.entity.FileResult;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
public interface FileResultDao {

	public FileResult getById(Long id);
	
//	public List<FileResult> query(@Param(value = "vo") FileResultQuery vo, @Param(value = "customSort") String customSort);
	
	public int insert(FileResult entity);
	
	public int update(FileResult entity);

	public int delete(FileResult entity);
}
