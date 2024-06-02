package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    //返回文件路径
    String Savefile(String userid, List<MultipartFile> files, HttpServletRequest request, String fileType);

    String Selectfile(String id, String usedType);
}
