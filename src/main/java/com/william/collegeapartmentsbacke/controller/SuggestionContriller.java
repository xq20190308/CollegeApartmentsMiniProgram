package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Faceback;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuggestionContriller {
    @Autowired
    private SuggestionService suggestionService;
    //用户查询全部草稿
    @GetMapping("/suggestions")
    public List<Suggestion> SelectDraftfindall() {
        return suggestionService.SelectDraftfindall();
    }

    //用户提交投诉
    @PostMapping("/suggestions")
    public Result SubmitSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.SubmitSuggestion(suggestion);
        return Result.success();
    }

    //用户编辑保存投诉
    @PostMapping("/suggestionsdraft")
    public Result Savedaft(@RequestBody Suggestion suggestion) {
        suggestionService.Savedaft(suggestion);
        return Result.success();
    }

    //删除投诉
    @DeleteMapping("/suggestions{id}")
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
    public Result upload(@RequestParam("file") MultipartFile file) {
        String result = suggestionService.uploadFile(file);
        if(result.equals("success"))
            return Result.success();
        else if(result.equals("file upload fail")||result.equals("file is empty"))
            return Result.error(result);
        else return null;
    }

    //管理员获取投诉列表
    @GetMapping("/manage-suggestions")
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
