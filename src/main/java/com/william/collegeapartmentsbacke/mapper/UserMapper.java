package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据openid查询用户
    @Select("select * from coap.user where openid = #{openid}")
    User getByOpenid(String openid);
    //查询最新的一条数据
    @Select("select LAST_VALUE() over () from coap.user")
    User getLastestUser();

    //通过权限获取用户，主要是获取老师信息
    @Select("select * from coap.user where user_level = #{userLevel} order by name")
    List<User> getByUserLevel(String userLevel);

    @Select("select * from coap.user where username = #{username}")
    User getUserByUsername(String username);

    @Select("select password from coap.user where username = #{username}")
    String findPwdByUsername(String username);

    //查询用户权限
    @Select("select p.* from coap.user_permission p join coap.user u on p.user_level = u.user_level where u.openid = #{openid}")
    Permission getPermissionByOpenid(String openid);

//    @Select("select * from coap.user where username = #{username}")
//    User getUserByUserName(String username);


    @Select("select * from coap.user where userid = #{userid}")
    User getUserByUserid(String userid);

    //添加新用户
    void insert(User user);

    //更新openid
    @Update("update coap.user set openid = #{openid} where username = #{username}")
    void updateOpenid(String username,String openid);


    //更新头像
    @Update("update coap.user set avatar = #{avatarFileId} where userid = #{userid}")
    void updateAvatarFileid(String userid, String avatarFileId);



    //通过userid修改password
    @Update("update coap.user set password = #{password} where userid = #{userid}")
    void updatePasswordByUserid(String userid, String password);

    //通过userid修改userlevel
    @Update("update coap.user set user_level = #{userLevel} where userid = #{userid}")
    void updateLevelByUserid(String userid,String userLevel);

    //通过userid初始化用户的openid
    @Update("update coap.user set openid = NULL where userid = #{userid}")
    void initOpenidByUserid(String userid);
}
