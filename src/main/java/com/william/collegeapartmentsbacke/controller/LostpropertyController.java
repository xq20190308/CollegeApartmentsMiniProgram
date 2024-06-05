package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
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
    private FileService fileService;

    @PostMapping("/uploadItem")
    public Result saveFile(@RequestHeader("Authorization")String token, @RequestParam("file") List<MultipartFile> file, HttpServletRequest request)
    {
        return Result.success(fileService.Savefile(token,file,request));

    }

    @PostMapping("/addFound")
    public Result saveSubmit(@RequestBody Itemdata itemdata)
    {
            lostpropertyService.saveSubmit(itemdata);
            return Result.success();
    }

    @GetMapping("/Getdata/{category}")
    public Result SelectData(@PathVariable("category") String category)
    {
        List<Itemdata> itemdata= lostpropertyService.getItemdata(category);
        return Result.success(itemdata);
    }
}
