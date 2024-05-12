package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {
    @SuppressWarnings("CallToPrintStackTrace")
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty())
        {
            String fileName = file.getOriginalFilename();
            String filetype = file.getContentType();
            try {
                String filePath = "D:/Desktop/Smart-MergeCode/src/main//resources/static/uploadpicture/" + fileName;
                file.transferTo(new File(filePath));
                return Result.success();
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("file upload fail");
            }
        }
        else
        {
            return Result.error("file is empty");
        }
    }
}
