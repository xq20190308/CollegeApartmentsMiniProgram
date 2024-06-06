package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String Savefile(String userid, List<MultipartFile> files, HttpServletRequest request);

    //上传文件，同时返回文件对象，方便从文件对象中取到属性
    Uploadfile SaveSingleFile(String userid, MultipartFile file, HttpServletRequest request);

    //返回文件路径
    String SelectfileById(String id);


}
