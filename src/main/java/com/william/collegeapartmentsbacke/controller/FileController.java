package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
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

//文件上传
@Slf4j
@RestController
@RequestMapping(value = "/upload")
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

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
        if(avatarUrl != null ){
            fileService.DeletefileByUrl(avatarUrl);
        }
        Uploadfile savaedFile = fileService.SaveSingleFile(userid,file,request);
        userService.updateAvatar(userid,savaedFile.getPath());

        String fileUrl = savaedFile.getPath();
        log.info("fileUrl : {}",fileUrl);

        HashMap<String, String> result = new HashMap<String , String>();

        AjaxResult ajax = AjaxResult.success();
        ajax.put("url", fileUrl);
        ajax.put("fileName", fileUrl);//与前端一致
        return ajax;
    }
}

