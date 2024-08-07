package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Suggestion;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;;

    //用户查询全部草稿
    @GetMapping("/selectDraft/{stu_id}")
    public Result SelectDraftfindall(@PathVariable("stu_id") String stu_id) {
        log.info("SelectDraftfindall");
        List<Suggestion> suggestions = suggestionService.SelectDraftfindall(stu_id);
        log.info(suggestions.toString());
        return Result.success(suggestions);
    }

    //用户提交投诉
    @PostMapping("/suggestions")
    public Result SubmitSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.SubmitSuggestion(suggestion);
        log.info("SubmitSuggestion");
        return Result.success();
    }

    //用户编辑保存投诉
    @PostMapping("/suggestionsDraft")
    public Result Savedaft(@RequestBody Suggestion suggestion) {
        String msg=suggestionService.Savedaft(suggestion);
        return Result.success(msg);
    }

    //删除投诉
    @DeleteMapping("/deleteSuggestions/{id}")
    public Result deleteSuggestion(@PathVariable("id") long id) {
        suggestionService.deleteSuggestion(id);
        if (suggestionService.deleteSuggestion(id)) {
            return Result.success();
        }
        else
            return Result.error("Draft is empty");
    }

    //上传文件
    @PostMapping("/upload")
    public Result upload(@RequestHeader("Authorization")String token,@RequestParam("files")List<MultipartFile>files, HttpServletRequest request) {
       String userid = userService.getUseridFromToken(token);
       return Result.success(fileService.Savefile(userid,files,request));
    }

//    //获取文件
//    @GetMapping("/getFile/{id}")
//    public String Selectfile(@PathVariable ("id") String id) {
//
//        return suggestionService.Selectfile(id);
//    }
    //管理员获取投诉列表
    @GetMapping("/manageSuggestions")
    public List<Suggestion> Selectfindall() {
        return suggestionService.Selectfindall();
    }

    //删除文件
    @DeleteMapping("/deleteFiles/{Filename}")
    public Result deleteSuggestion(@PathVariable String Filename) {
        return Result.success(fileService.DeletefileByUrl(Filename));
    }

    @PostMapping("/updateState")
    public Result updateStatus(@RequestBody Suggestion suggestion)
    {
        suggestionService.updataStatus(suggestion);
        return Result.success();
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
