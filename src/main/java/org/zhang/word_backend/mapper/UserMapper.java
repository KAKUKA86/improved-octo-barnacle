package org.zhang.word_backend.mapper;

import org.apache.ibatis.annotations.*;
import org.zhang.word_backend.pojo.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user(user_id,user_name,user_password,user_email,user_phone) values(#{user_id},#{user_name},#{user_password},#{user_email},#{user_phone})")
    int insertUser(User user);


    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "user_name", column = "user_name"),
            @Result(property = "user_password", column = "user_password"),
            @Result(property = "user_email", column = "user_email"),
            @Result(property = "user_phone", column = "user_phone")
    })
    @Select("SELECT user_id,user_name,user_password,user_email,user_phone " +
            "FROM user " +
            "WHERE (user_phone = #{user_email} OR user_email = #{user_email})" +
            "AND user_password = #{user_password}")
    User selectUserByPhoneOrEmailAndPassword(@Param("user_email") String user_email,@Param("user_password") String user_password);
}
