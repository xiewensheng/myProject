package com.wjw.graduationproject.repository;

import com.wjw.graduationproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author XWS
 * @ClassName UserRepository
 * @Description user的repository类
 * @create 2018-11-05 14:23
 * @Version 1.0
 */

public interface UserRepository extends JpaRepository<User,Long> {

  /**
   * @author xiewensheng
   * @Description 查询用户名称包含username字符串的用户对象
   * @Date 14:26 2018/11/5
   * @Param [username]
   * @return java.util.List<com.wjw.graduationproject.entity.User>
   **/
  List<User> findByUsernameContaining(String username);

  /**
   * @author xiewensheng
   * @Description 获得单个用户对象，根据username和pwd的字段匹配
   * @Date 14:27 2018/11/5
   * @Param [username, pwd]
   * @return com.wjw.graduationproject.entity.User
   **/
  User getByUsernameIsAndUserpwdIs(String username,String pwd);

  /**
   * @author xiewensheng
   * @Description //精确匹配username的用户对象
   * @Date 14:28 2018/11/5
   * @Param [username]
   * @return com.wjw.graduationproject.entity.User
   **/
  User getByUsernameIs(String username);
}
