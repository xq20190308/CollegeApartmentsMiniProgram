package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface uploadFileService {
    Result Savefile(@RequestHeader("Authorization")String token,List<MultipartFile> files, HttpServletRequest request);
}
