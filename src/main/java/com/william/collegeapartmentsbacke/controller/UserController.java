package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.constant.JwtClaimsConstant;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.common.utils.JwtUtil;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
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

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
//    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info(userLoginDTO.toString());
        log.info("微信用户登录：{}", userLoginDTO.getCode());
        User user = userService.wxLogin(userLoginDTO);
        if(user==null && userLoginDTO.isVerify()){
            return Result.error("no user");
        }
        Map<String, Object> claims = new HashMap<String, Object>() {{
            put(JwtClaimsConstant.ID, user.getId());
            put(JwtClaimsConstant.OPENID, user.getOpenid());
        }};
        log.info("111");
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);

        //返回给前端的数据
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        log.info("返回给前端："+userLoginVO.toString());
        return Result.success(userLoginVO);
    }

//    @GetMapping("/findNeighborhood")
//    public NeighborhoodInfo findNeighborhoodByUserId(@RequestParam("id") Integer id) {
//        return userService.findNeighborhoodByUserId(id);
//    }

    @GetMapping("/findByOpenid")
//    public Result<User> findByOpenid(@RequestHeader("Authorization") String token) {
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

        }

        return Result.error("请登录");
    }
}
