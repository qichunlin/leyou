package com.legend.user.controller;

import com.legend.user.pojo.User;
import com.legend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/14
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public String all(ModelMap model) {
        // 查询用户
        List<User> users = this.userService.queryAll();
        // 放入模型
        model.addAttribute("users", users);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "users";
    }

    @GetMapping("/all-show")
    @ResponseBody
    public Object all() {
        // 查询用户
        List<User> users = this.userService.queryAll();
        return users;
    }


    @GetMapping("{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id") Long id) {
        return this.userService.queryById(id);
    }


    @GetMapping("/hello")
    @ResponseBody
    public String test() {
        return "hello ssm";
    }
}
