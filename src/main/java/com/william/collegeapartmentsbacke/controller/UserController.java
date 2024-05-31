package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.constant.JwtClaimsConstant;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.common.utils.JwtUtil;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.pojo.vo.UserLoginVO;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
//        1.验证用户名密码
//        如果不正确，则说明登录失败，需要联系管理员
//        如果根据username查询不到密码，说明用户不存在 默认username学号，pwd是123456
        log.info("USER LOGIN DTO: {}", userLoginDTO);
        String currentUsername = userLoginDTO.getUsername();
        String msg = userService.verifyByPwd(currentUsername,userLoginDTO.getPassword());
//        2.如果用户名密码正确，则获取DTO中的该微信号的code，换取openid
        if(msg != "true"){
            //情况1：登录失败，查无此用户,请注册或联系管理员
            //情况2：登陆失败,账号或密码错误
            return Result.error(msg);
        }
        User user = userService.wxLogin(userLoginDTO);
        //情况3：如果到此查询不到数据,则出现未知问题
        //情况4：也有可能是获取不到openid;
        if(user==null){
            return Result.error("登录失败，请联系管理人员");
        }
        //情况4：如果当前用户名和这个微信登录过（绑定）的用户名不同
        if(!currentUsername.equals(user.getUsername())){
            return Result.error("当前微信已绑定其他账号，请联系登录已绑定的账号，或联系管理员");
        }
        //生成token
        Map<String, Object> claims = new HashMap<String, Object>() {{
            put(JwtClaimsConstant.ID,user.getId());
            put(JwtClaimsConstant.OPENID, user.getOpenid());
            put(JwtClaimsConstant.USERID,user.getUserid());
            put(JwtClaimsConstant.USERLEVEL,user.getUserLevel());
        }};
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        //返回给前端的数据
        Permission permission = userService.getPermission(user.getOpenid());
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .username(user.getUsername())
                .trueName(user.getName())
                .userid(user.getUserid())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .userPermission(permission)
                .token(token)
                .build();
        //id,token
        log.info("返回给前端："+userLoginVO.toString());
        return Result.success(userLoginVO);
    }







    public Result regist(@RequestBody UserLoginDTO userLoginDTO) {

        User user = userService.wxLogin(userLoginDTO);

        return Result.success("注册成功");
    }

    public Result getPermission(@RequestHeader("Authorization") String token) {
        String openid = JwtUtil.parseJWT(jwtProperties.getSecretKey(),token).getClaim("openid").toString();
        Permission permission = userService.getPermission(openid);
        return Result.success(permission);
    }
//    @GetMapping("/findNeighborhood")
//    public NeighborhoodInfo findNeighborhoodByUserId(@RequestParam("id") Integer id) {
//        return userService.findNeighborhoodByUserId(id);
//    }

    @GetMapping("/findByOpenid")
    public Result findByOpenid(@RequestHeader("Authorization") String token) {
        try {
            String openid = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token).getClaim("openid").toString();
            if (openid != null) {
                User resultUser = userService.findByOpenid(openid);
                if (resultUser != null) {
                    return Result.success(userService.findByOpenid(openid));
                }
            }

        } catch (Exception e) {
            log.info("获取openid时遇到问题");
        }
        return Result.error("请登录");
    }
}

