package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;
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

import static com.william.collegeapartmentsbacke.pojo.entity.AjaxResult.success;

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
    public AjaxResult getAllLeavePosts(){
        return AjaxResult.success(leavePostService.getAllLeavePosts());
    }

    @RequestMapping("/getByReviewerId")
    public AjaxResult getByReviewerId(@RequestParam("reviewerId") String reviewerId){
        return AjaxResult.success(leavePostService.getByReviewerId(reviewerId));
    }

    @RequestMapping("/getByUserId")
    public AjaxResult getByUserId(@RequestParam("userId") String userId){
        return AjaxResult.success(leavePostService.getByUserId(userId));
    }

    @PostMapping("/addLeavePost")
    public AjaxResult addLeavePost(@RequestBody LeavePost leavePost){
        log.info("新增请假申请{}",leavePost);
        return AjaxResult.success(leavePostService.insertLeavePost(leavePost));
    }
    @PostMapping("/reviewLeavePost")
    @NoNeedLogin
    public AjaxResult reviewLeavePost(@RequestBody LeavePost leavePost){
        log.info("审核申请{}",leavePost);
        return success(leavePostService.updateLeavePost(leavePost));
    }

    @PostMapping("/uploadFiles")
    public AjaxResult upload(@RequestHeader("Authorization")String token, @RequestParam("files") List<MultipartFile> files, HttpServletRequest request) {
        String userid = userService.getUseridFromToken(token);
        String savefile = fileService.Savefile(userid, files, request);
        log.info("fileUrl : {}", savefile);
        return AjaxResult.success(savefile);
    }
}
