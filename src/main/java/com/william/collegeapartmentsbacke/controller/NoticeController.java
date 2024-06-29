package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.dto.NoticeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Notice;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notifications")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //查询全部通知
    @NoNeedLogin
    @GetMapping
    public Result list(
            Integer id,
            String keyword,
            String typeName,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime publish_time_st,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime publish_time_ed,
            Boolean isActive){
//        log.info("isActive{}", isActive);
        List<Notice> noticeList = noticeService.list(id,keyword,typeName,publish_time_st,publish_time_ed,isActive);

//        log.info("查询全部通知{}",typeName,isActive);
//        log.info(noticeList.toString());
        return Result.success(noticeList);
    }

    //发布通知
//    @PostMapping
//    public Result addNotice(Notice notice){
//        log.info("新增Notice");
//        Boolean addSuccess = noticeService.addNotice(notice);
//
//        if(addSuccess)
//            return Result.success();
//        else
//            return Result.error("已存在相同标题或相同内容的通知");
//    }






    @PostMapping
    public Result addNotice(@RequestBody NoticeDTO noticeDTO){
        log.info("新增Notice");
        Boolean addSuccess = noticeService.addNotice(noticeDTO);
        if(addSuccess) {
            log.info("添加成功");
            return Result.success();
        }
        else {
            return Result.error("已存在相同标题或相同内容的通知");
        }
    }

    @PostMapping("/modify")
    public Result modifyNotice(@RequestBody Notice notice){
//        log.info("更新数据 Notice:{}",notice.toString());
        noticeService.modifyNotice(notice);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteNotice(@PathVariable List<Integer> ids)
    {
//        log.info("彻底删除操作, ids:{}",ids);
        noticeService.deleteNotice(ids);
        return Result.success();
    }

}
