package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.LeavePostService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/leavePosts")
public class LeavePostController {

    private static final Logger log = LoggerFactory.getLogger(LeavePostController.class);
    @Autowired
    private LeavePostService leavePostService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getAllLeavePosts")
    public Result getAllLeavePosts(){
        return Result.success(leavePostService.getAllLeavePosts());
    }

    @RequestMapping("/getByReviewerId")
    public Result getByReviewerId(@RequestParam("reviewerId") String reviewerId){
        return Result.success(leavePostService.getByReviewerId(reviewerId));
    }

    @RequestMapping("/getByUserId")
    public Result getByUserId(@RequestParam("userId") String userId){
        return Result.success(leavePostService.getByUserId(userId));
    }

    @PostMapping("/addLeavePost")
    public Result addLeavePost(@RequestBody LeavePost leavePost){
        log.info("新增请假申请{}",leavePost);
        return Result.success(leavePostService.insertLeavePost(leavePost));
    }
    @RequestMapping("/reviewLeavePost")
    public Result reviewLeavePost(){
        return Result.success();
    }

    @PostMapping("/uploadFiles")
    public Result upload(@RequestHeader("Authorization")String token, @RequestParam("files") List<MultipartFile> files, HttpServletRequest request) {
        String userid = userService.getUseridFromToken(token);
        String savefile = fileService.Savefile(userid, files, request);
        log.info("fileUrl : {}", savefile);
        return Result.success(savefile);
    }
}
