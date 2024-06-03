package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String Savefile(String userid, List<MultipartFile> files, HttpServletRequest request);

    Uploadfile SaveSingleFile(String userid, MultipartFile file, HttpServletRequest request);

    //返回文件路径
    String SelectfileById(String id);


}
