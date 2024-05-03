package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.pojo.Notice;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notifications")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public Result list(
            @RequestParam(defaultValue = "1") Boolean isActive){
        List<Notice> noticeList = noticeService.list(isActive);
        log.info("查询全部通知");
        return Result.success(noticeList);
    }
    @PostMapping
    public Result addNotice(Notice notice){
        log.info("新增Notice");
        noticeService.addNotice(notice);
        return Result.success();
    }


}
