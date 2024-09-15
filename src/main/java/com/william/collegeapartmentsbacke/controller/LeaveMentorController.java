package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.LeaveMentor;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.LeaveMentorService;
import com.william.collegeapartmentsbacke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveMentors")
public class LeaveMentorController {
    @Autowired
    private LeaveMentorService leaveMentorService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getAllLeaveMentors")
    public Result getAllLeaveMentors() {
        return Result.success(leaveMentorService.getAllLeaveMentors());
    }

    @RequestMapping("/getByPostId")
    public Result getByUserId(@RequestParam("postId") String postId) {
        return Result.success(leaveMentorService.getByPostId(postId));
    }

    //插入多条数据
    @RequestMapping("/addLeaveMentors")
    public Result addLeaveMentors(@RequestHeader("Authorization") String token, @RequestBody List<LeaveMentor> list,@RequestParam String postId) {
        String userId = userService.getUseridFromToken(token);
        for (LeaveMentor leaveMentor : list) {
            leaveMentor.setUserId(userId);
            leaveMentor.setPostId(postId);
        }
        return Result.success(leaveMentorService.addLeaveMentors(list));
    }
}
