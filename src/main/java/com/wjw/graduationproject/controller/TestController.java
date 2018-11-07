package com.wjw.graduationproject.controller;

import com.wjw.graduationproject.entity.Audience;
import com.wjw.graduationproject.entity.User;
import com.wjw.graduationproject.repository.BaseRepository;
import com.wjw.graduationproject.repository.UserRepository;
import com.wjw.graduationproject.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private BaseRepository<User> baseRepository;

  @RequestMapping("/searchUser/{username}")
  public List<User> searchUser(@PathVariable("username") String username) {
    List<User> result = this.userRepository.findByUsernameContaining(username);
    return result;
  }

  @RequestMapping(value = "/login",method = RequestMethod.POST)
  public String loginSuccess(@RequestParam("username") String username, @RequestParam("userId") int userId) {
    String jwtToken = JwtHelper.createJwt(username, userId, Audience.getClientId(),
            Audience.getName(), Audience.getExpiresSecond()*1000, Audience.getBase64Secret());
    jwtToken = "bearer;" + jwtToken;
    return "Login Successful!:"+jwtToken;
  }

  @RequestMapping(value = "/test",method = RequestMethod.GET)
  public String test() {
    return "TEST Successful!:";
  }

  @RequestMapping("/testSearch")
  public User testSearch(){
    return baseRepository.findFirstBySql("select * from user where username=?",User.class,new Object[]{"xws"});
  }

  @RequestMapping("/testSearch2")
  public List<User> testSearch2(){
    return baseRepository.findBySql("select * from user where username=?",new Object[]{"xws"});
  }

  @RequestMapping("/testinsert")
  public int testInsert1(){
    return baseRepository.updateBySql("insert into user (username,userpwd) values (?,?)",new Object[]{"test","test"});
  }
}
