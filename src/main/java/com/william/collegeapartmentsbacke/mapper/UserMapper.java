package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据openid查询用户
    @Select("select * from coap.user where openid = #{openid}")
    User getByOpenid(String openid);

    //添加新用户
    void insert(User user);

    //查询最新的一条数据
    @Select("select LAST_VALUE() over () from coap.user")
    User getLastestUser();

    @Select("select password from coap.user where username = #{username}")
    String findPwdByUsername(String username);

    @Update("update coap.user set openid = #{openid} where username = #{username}")
    void updateOpenid(String username,String openid);
}
