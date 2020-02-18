package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.modular.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiuQianYi on 2018-12-19 23:12
 */
@Repository
public interface UserDao {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    int saveBatch(List<User> users);

    void update(User user);

    void delete(Long id);
}
