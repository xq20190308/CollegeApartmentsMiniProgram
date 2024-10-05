package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.PageResults;
import com.william.collegeapartmentsbacke.pojo.entity.Suggestion;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private FileService fileService;

    //用户查询全部草稿
    @GetMapping("/selectDraft/{stu_id}")
    public AjaxResult SelectDraftfindall(@PathVariable("stu_id") String stu_id) {
        log.info("SelectDraftfindall");
        List<Suggestion> suggestions = suggestionService.SelectDraftfindall(stu_id);
        log.info(suggestions.toString());
        return AjaxResult.success(suggestions);
    }

    //用户提交投诉
    @PostMapping("/suggestions")
    public AjaxResult SubmitSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.SubmitSuggestion(suggestion);
        log.info("SubmitSuggestion");
        return AjaxResult.success();
    }

    //用户编辑保存投诉
    @PostMapping("/suggestionsDraft")
    public AjaxResult Savedaft(@RequestBody Suggestion suggestion) {
        String msg=suggestionService.Savedaft(suggestion);
        return AjaxResult.success(msg);
    }

    //删除投诉
    @DeleteMapping("/deleteSuggestions/{id}")
    public AjaxResult deleteSuggestion(@PathVariable("id") long id) {
        suggestionService.deleteSuggestion(id);
        if (suggestionService.deleteSuggestion(id)) {
            return AjaxResult.success();
        }
        else
            return AjaxResult.error("Draft is empty");
    }

    //上传文件
    @NoNeedLogin
    @PostMapping("/upload")
    public AjaxResult upload(@RequestHeader("Authorization")String token,@RequestParam("files")List<MultipartFile>files, HttpServletRequest request) {
       String userid = userService.getUseridFromToken(token);
       return AjaxResult.success(fileService.Savefile(userid,files,request));
    }

//    //获取文件
//    @GetMapping("/getFile/{id}")
//    public String Selectfile(@PathVariable ("id") String id) {
//
//        return suggestionService.Selectfile(id);
//    }
    //管理员获取投诉列表
    @NoNeedLogin
    @GetMapping("/manageSuggestions/{page}")
    public List<Suggestion> Selectfindall(@PathVariable("page")long page, HttpServletResponse response) {
        PageDTO pageDTO= new PageDTO();
        pageDTO.setNowPage(page);
        PageResults<Suggestion> pageResults=suggestionService.Selectfindall(pageDTO);
        List<Suggestion> list = pageResults.getList();
        response.setHeader("totalPage", pageResults.getPageCount() +"");
        System.out.println("查询数据：" + list);
        System.out.println("查询总数：" +list.size());
        return list;
    }

    //删除文件
    @DeleteMapping("/deleteFiles/{Filename}")
    public AjaxResult deleteSuggestion(@PathVariable String Filename) {
        return AjaxResult.success(fileService.DeletefileByUrl(Filename));
    }

    @PostMapping("/updateState")
    public AjaxResult updateStatus(@RequestBody Suggestion suggestion)
    {
        suggestionService.updataStatus(suggestion);
        return AjaxResult.success();
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
