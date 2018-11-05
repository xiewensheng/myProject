package com.wjw.graduationproject.controller;

import com.wjw.graduationproject.entity.User;
import com.wjw.graduationproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XWS
 * @ClassName TestController
 * @Description 测试控制器
 * @create 2018-11-05 14:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/searchUser/{username}")
  @ResponseBody
  public List<User> searchUser(@PathVariable("username") String username) {
    List<User> result = this.userRepository.findByUsernameContaining(username);
    return result;
  }
}
