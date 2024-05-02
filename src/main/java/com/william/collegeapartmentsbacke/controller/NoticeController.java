package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.pojo.Notice;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notifications")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public Result list(){
        List<Notice> noticeList = noticeService.list();
        log.info("查询全部通知");

//        return Result.success(noticeList);
        return Result.success();

//        return "notifications";
    }
}
