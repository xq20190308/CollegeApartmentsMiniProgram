package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Objects;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/api")
public class SuggestionContriller {
    @Autowired
    private SuggestionService suggestionService;
    //用户查询全部草稿
    @GetMapping("/selectDraft")
    public Result SelectDraftfindall() {
        log.info("SelectDraftfindall");
        List<Suggestion> suggestions = suggestionService.SelectDraftfindall();
        log.info(suggestions.toString());
        return Result.success(suggestions);
    }

    //用户提交投诉
    @PostMapping("/suggestions")
    public Result SubmitSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.SubmitSuggestion(suggestion);
        return Result.success();
    }

    //用户编辑保存投诉
    @PostMapping("/suggestionsDraft")
    public Result Savedaft(@RequestBody Suggestion suggestion) {
        Integer savedaft = suggestionService.Savedaft(suggestion);
        return Result.success(savedaft);
    }

    //删除投诉
    @DeleteMapping("/deleteSuggestions{id}")
    public Result deleteSuggestion(@PathVariable ("id") long id) {
        suggestionService.deleteSuggestion(id);
        if(suggestionService.deleteSuggestion(id)) {
            return Result.success();
        }
        else
            return Result.error("Draft is empty");
    }

    //上传文件
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String ID = String.valueOf(UUID.randomUUID());
        String filename = ID + "." + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String filetype = file.getContentType();
        String Path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/static/" + filename;
        byte[] b;
        try {
            b = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Uploadfile loadFile = new Uploadfile(ID,filename, filetype,Path,b);
        try {
            file.transferTo(new File("E:/static/" + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        suggestionService.Savefile(loadFile);
        return Result.success(Path);
    }

    //获取文件
    @GetMapping("/getFile/{id}")
    public String Selectfile(@PathVariable ("id") String id) {

        return suggestionService.Selectfile(id);
    }
    //管理员获取投诉列表
    @GetMapping("/manageSuggestions")
    public List<Suggestion> Selectfindall() {
        return suggestionService.Selectfindall();
    }

//    @PostMapping ("/faceback{id}")
//    public ResponseEntity<?> createFaceback(
//            @PathVariable("id") Long id,
//            @RequestBody Faceback faceback) {
//
//        try {
//            Faceback result = complaintService.createFeedback(id, feedback);
//            return ResponseEntity.ok(result);
//        } catch (NotFoundException ex) {
//
//        }
//    }
}
