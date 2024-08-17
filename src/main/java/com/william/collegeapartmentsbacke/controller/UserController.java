package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.common.constant.JwtClaimsConstant;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.common.utils.JwtUtil;
import com.william.collegeapartmentsbacke.pojo.entity.*;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.vo.ContactInfoVO;
import com.william.collegeapartmentsbacke.pojo.vo.UserLoginVO;
import com.william.collegeapartmentsbacke.pojo.vo.UserVO;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.SchoolnfoService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private FileService fileService;
    @Autowired
    private SchoolnfoService schoolnfoService;

    @NoNeedLogin
    @RequestMapping(value = "loginInnerTest", method = RequestMethod.POST)
    public Result loginInnerTest(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("USER LOGIN DTO: {}", userLoginDTO);
        String currentUsername = userLoginDTO.getUsername();
        String msg = userService.verifyByPwd(currentUsername,userLoginDTO.getPassword());
//        2.如果用户名密码正确，则获取DTO中的该微信号的code，换取openid
        if(msg != "true"){
            //情况1：登录失败，查无此用户,请注册或联系管理员
            //情况2：登陆失败,账号或密码错误
            return Result.error(msg);
        }
        User user = userService.findByUsername(currentUsername);
        if(user == null){
            return Result.error("测试登录时查无此用户");
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

        String avatarUrl = user.getAvatarUrl();
        if(avatarUrl == null || "".equals(avatarUrl)){
//            返回默认头像
            avatarUrl =  "https://c-ssl.duitang.com/uploads/item/201602/04/20160204001032_CBWJF.jpeg";
        }
        //后面会改成服务器上的图片
        StuClassInfoDTO stuClassInfo = schoolnfoService.getStuClassInfoByUserIdBetter(user.getUserid());
        Permission permission = userService.getPermissionByUserid(user.getUserid());
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .username(user.getUsername())
                .trueName(user.getTrueName())
                .userid(user.getUserid())
                .phone(user.getPhone())
                .userPermission(permission)
                .token(token)
                .avatarUrl(avatarUrl)
                .dormitory(user.getDormitory())
                .classInfo(stuClassInfo)
                .email("暂时还没写")
                .build();
        //id,token
        log.info("测试登录返回给前端："+userLoginVO.toString());
        return Result.success(userLoginVO);

    }


    /**
     * 正式登录
     *
     * @param userLoginDTO
     * @return
     */
    //传入账号密码，返回登录状态，用户基本信息
    @NoNeedLogin
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
        //测试时用,可不校验opid
//        User  user = userService.findByUsername(userLoginDTO.getUsername());
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
        Permission permission = userService.getPermissionByUserid(user.getUserid());
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .username(user.getUsername())
                .trueName(user.getTrueName())
                .userid(user.getUserid())
                .phone(user.getPhone())
                .userPermission(permission)
                .token(token)
                .userLevel(user.getUserLevel())
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
        String userId = userService.getUseridFromToken(token);
        Permission permission = userService.getPermissionByUserid(userId);
        return Result.success(permission);
    }

    @GetMapping("/findByOpenid")
    public Result findByOpenid(@RequestHeader("Authorization") String token) {
        try {
            String openid = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token).get("openid").toString();
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

    @RequestMapping(value = "/findByUserLevel",method = RequestMethod.GET)
    public Result findByUserLevel(String userLevel) {
        List<ContactInfoVO> contactInfoVOs = null;
        try {
            contactInfoVOs = userService.findByUserLevel(userLevel);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new RuntimeException(e);
        }
        log.info(contactInfoVOs.toString());
        return Result.success(contactInfoVOs);
    }

    @RequestMapping(value = "findByUserid",method = RequestMethod.GET)
    public Result findByUserid(@RequestHeader("Authorization") String token,String userid){
        String userLevel = userService.getUserLevleFromToken(token);
        User user = userService.findByUserid(userid);
        StuClassInfoDTO stuClassInfo = schoolnfoService.getStuClassInfoByUserIdBetter(user.getUserid());
        Permission permission = userService.getPermissionByUserid(user.getUserid());
        String avatarUrl = user.getAvatarUrl();
        if(avatarUrl == null || "".equals(avatarUrl)){
//            返回默认头像
            avatarUrl =  "https://c-ssl.duitang.com/uploads/item/201602/04/20160204001032_CBWJF.jpeg";
        }
        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .trueName(user.getTrueName())
                .userid(user.getUserid())
                .phone(user.getPhone())
                .avatarUrl(user.getAvatarUrl())
                .dormitory(user.getDormitory())
                .classInfo(stuClassInfo)
                .email("无")
                .build();
        if(Objects.equals(userLevel, "0") || Objects.equals(userLevel, "1"))
        {
            userVO.setOpenid(user.getOpenid());
            userVO.setUserLevel(user.getUserLevel());
            userVO.setUserPermission(permission);
        }
        return Result.success(userVO);
    }


   @RequestMapping(value = "/uploadavatar",method = RequestMethod.POST)
   public Result upLoadAvatar(@RequestHeader("Authorization") String token, MultipartFile avatar, HttpServletRequest request) throws IOException {
        String userid = userService.getUseridFromToken(token);
        //删除上一次的头像
       User user = userService.findByUserid(userid);
       String avatarUrl = user.getAvatarUrl();
       if(avatarUrl != null ){
           fileService.DeletefileByUrl(avatarUrl);
       }
       log.info("***avatar : {}",avatar.getOriginalFilename());
//       log.info("***avatar : {}",avatar.getContentType());
//       log.info("***avatar : {}",avatar.getBytes());
        Uploadfile savaedFile = fileService.SaveSingleFile(userid,avatar,request);
        userService.updateAvatar(userid,savaedFile.getPath());

        String fileUrl = savaedFile.getPath();
        log.info("fileUrl : {}",fileUrl);

        return Result.success(fileUrl);
   }

   @RequestMapping(value = "/getavatar",method = RequestMethod.GET)
   public Result getAvatar(@RequestHeader("Authorization") String token, String otherUserid) {
        log.info("token : {}",token);
        String userid = userService.getUseridFromToken(token);

       if (otherUserid != null) {
            userid = otherUserid;
       }
        User user = userService.findByUserid(userid);
        String avatarUrl = user.getAvatarUrl();
        //暂时返回网络头像,其实应该在User表的avatar存一个默认File的id
       if(avatarUrl == null || "".equals(avatarUrl)){
//            返回默认头像
           return Result.success("https://c-ssl.duitang.com/uploads/item/201602/04/20160204001032_CBWJF.jpeg");
       }else
       {
           log.info("avatar : {}",avatarUrl);
           return Result.success(avatarUrl);
       }


   }

   @RequestMapping(value = "/updatePasswordByUserid/{userid}",method = RequestMethod.POST)
   public Result updatePasswordByUserid(@PathVariable String userid, @RequestBody User user) {
       if (userid == null) {
           return Result.error("用户不存在");
       }
       String newPassword = user.getPassword();
       if (newPassword == null || newPassword.isEmpty()) {
           return Result.error("密码不能为空");
       }
       log.info("要更新的用户ID: " + userid + " 新密码: " + newPassword);
       userService.updatePasswordByUserid(userid, newPassword);
       return Result.success();
   }

   
   @RequestMapping(value = "/updateLevelByUserid/{userid}",method = RequestMethod.POST)
    public Result updateLevelByUserid(@PathVariable String userid, @RequestBody User user) {
        if (userid == null) {
            return Result.error("用户不存在");
        }
        String newLevel = user.getUserLevel();
        if (newLevel == null || newLevel.isEmpty()) {
            return Result.error("等级不能为空");
        }
       log.info("要更新的用户ID: " + userid + " 新等级: " + newLevel);
       userService.updateLevelByUserid(userid, newLevel);
       return Result.success();
   }


   @RequestMapping(value = "/initOpenidByUserid/{userid}",method = RequestMethod.POST)
    public Result intiOpenidByUserid(@PathVariable String userid) {
        if (userid == null) {
            return Result.error("用户不存在");
        }
        userService.initOpenidByUserid(userid);
        return Result.success();
   }

}

