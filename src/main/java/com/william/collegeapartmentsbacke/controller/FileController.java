package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.config.DefaultConfig;
import com.william.collegeapartmentsbacke.mapper.FileMapper;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

//文件上传
@Slf4j
@RestController
@RequestMapping(value = "/upload")
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private DefaultConfig defaultConfig;

    @PostMapping(value = "/updateAvatar")
    @NoNeedLogin
    public AjaxResult updateAvatar(MultipartFile file, String userid, HttpServletRequest request) {
        //打印所有的接收到的参数
        log.info("file : {}",file);
        log.info("userid : {}",userid);
        log.info("request : {}",request);
        //删除上一次的头像
        User user = userService.findByUserid(userid);
        String avatarUrl = user.getAvatarUrl();
        log.info("avatarUrl : {}",avatarUrl);
        if(avatarUrl != null && !avatarUrl.equals(defaultConfig.getAvatarUrl())){
            fileService.DeletefileByUrl(avatarUrl);//删服务器
            fileMapper.deletefile(avatarUrl);//删fileDate库
        }
        Uploadfile savaedFile = fileService.SaveSingleFile(userid,file,request);
        userService.updateAvatar(userid,savaedFile.getPath());

        String fileUrl = savaedFile.getPath();
        log.info("fileUrl : {}",fileUrl);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("url", fileUrl);
        ajax.put("fileName", fileUrl);//与前端一致
        return ajax;
    }

    //删除服务器的头像
    @PostMapping(value = "/deleteAvatar")
    @NoNeedLogin
    public AjaxResult deleteAvatar(@RequestParam("ids") Long[] ids, HttpServletRequest request) {
        for (Long userid : ids){
            //打印所有的接收到的参数
            log.info("userid : {}", userid);
            //删除头像
            User user = userService.findById(userid);
            String avatarUrl = user.getAvatarUrl();
            if (avatarUrl != null && !avatarUrl.equals(defaultConfig.getAvatarUrl())) {
                log.info("avatarUrl : {}", avatarUrl);
                fileService.DeletefileByUrl(avatarUrl);
                int i = fileMapper.deletefile(avatarUrl);//删fileDate库
                log.info("删除数i : {}", i);
            }else{
                log.info("不用删");
            }
        }
        return AjaxResult.success();
    }

    //上传文件
    @PostMapping("/files")
    public AjaxResult upload(@RequestHeader("Authorization")String token, @RequestParam("files") List<MultipartFile> files, HttpServletRequest request) {
        String userid = userService.getUseridFromToken(token);
        String  savefile = fileService.Savefile(userid,files,request);
        log.info("fileUrl : {}",savefile);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("url", savefile);
        ajax.put("fileName", savefile);//与前端一致
        return ajax;
    }
}

