package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
import com.william.collegeapartmentsbacke.service.uploadFileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LostpropertyController {
    @Autowired
    LostpropertyService lostpropertyService;

    @Autowired
    uploadFileService uploadfileService;


    @PostMapping("/uploadItem")
    public Result saveFile(@RequestHeader("Authorization")String token, @RequestParam("file") List<MultipartFile> file, HttpServletRequest request)
    {
        return uploadfileService.Savefile(token,file,request);

    }

    @PostMapping("/addFound")
    public Result saveSubmit(@RequestBody Itemdata itemdata)
    {
            lostpropertyService.saveSubmit(itemdata);
            return Result.success();
    }

    @GetMapping("/Getdata")
    public Result SelectData()
    {
        List<Itemdata> itemdata= lostpropertyService.getItemdata();
        return Result.success(itemdata);
    }
}
