package org.zhang.word_backend.service.impl;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserMapper;
import org.zhang.word_backend.pojo.User;
import org.zhang.word_backend.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User registerUser(User user) {
        if (userMapper.selectUserByPhoneOrEmail(user) != 0)
            throw new RuntimeException("用户已存在,请直接登录或修改邮箱和电话号码");
         //如果数据库返回值不为1，则抛出异常
        int registerUser= userMapper.insertUser(user);
        if(registerUser != 1){
            throw new RuntimeException("用户注册失败");
        }
        return userMapper.selectUserByPhoneOrEmailAndPassword(user);
    }

    @Override
    public User loginUser(User user) {
        User loginuser =  userMapper.selectUserByPhoneOrEmailAndPassword(user);
        System.out.println("登录用户："+loginuser.getUser_name());
        if ( loginuser == null)
            throw new RuntimeException("用户登录失败");
        return loginuser;
    }
}
