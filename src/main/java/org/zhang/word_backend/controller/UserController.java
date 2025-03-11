package org.zhang.word_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhang.word_backend.pojo.User;
import org.zhang.word_backend.util.result.UserResponse;
import org.zhang.word_backend.service.UserService;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
        logger.info("用户注册请求: {}", user);
        UserResponse response = new UserResponse();
        try {
            User registeredUser = userService.registerUser(user);
            response.setStatus("success");
            response.setMessage("用户注册成功");
            User user1 = new User();
            user1.setUser_name(registeredUser.getUser_name());
            user1.setUser_id(registeredUser.getUser_id());
            response.setUser(user1);
            logger.info("用户注册成功: {}", user1);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("用户注册失败: " + e.getMessage());
            logger.error("用户注册失败", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody User user) {
        logger.info("用户登录请求: {}", user);
        UserResponse response = new UserResponse();
        try {
            User loggedInUser = userService.loginUser(user);
            response.setStatus("success");
            response.setMessage("用户登录成功");
            response.setUser(loggedInUser);
            logger.info("用户登录成功: {}", loggedInUser);
            response.setUser(loggedInUser);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("用户登录失败: " + e.getMessage());
            logger.error("用户登录失败", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}