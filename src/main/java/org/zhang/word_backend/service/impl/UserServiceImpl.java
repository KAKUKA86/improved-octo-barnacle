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
    public Integer registerUser(User user) {
         //如果数据库返回值不为1，则抛出异常
        if(userMapper.insertUser(user)!=1){
            throw new RuntimeException("用户注册失败");
        }return 1;
    }

    @Override
    public User loginUser(User user) {
        User loginuser =  userMapper.selectUserByPhoneOrEmailAndPassword(user.getUser_email(),user.getUser_password());
        if ( loginuser == null)
            throw new RuntimeException("用户登录失败");
        return loginuser;
    }
}
