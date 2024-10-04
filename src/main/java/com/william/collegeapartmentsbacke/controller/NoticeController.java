package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.dto.NoticeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.Notice;
import com.william.collegeapartmentsbacke.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.william.collegeapartmentsbacke.common.utils.HttpClientUtil.doPost;


@Slf4j
@RestController
@RequestMapping("/notifications")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //查询全部通知
    @NoNeedLogin
    @GetMapping
    public AjaxResult list(
        Integer id,
        String keyword,
        String typeName,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime publish_time_st,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime publish_time_ed,
        Boolean isActive) throws IOException {

        List<Notice> noticeList = noticeService.list(id,keyword,typeName,publish_time_st,publish_time_ed,isActive);

//        log.info("查询全部通知{}",typeName,isActive);
//        log.info(noticeList.toString());
        //返回结果
        return AjaxResult.success(noticeList);
    }





    @PostMapping
    public AjaxResult addNotice(@RequestBody NoticeDTO noticeDTO){
        log.info("新增Notice");
        Boolean addSuccess = noticeService.addNotice(noticeDTO);
        if(addSuccess) {
            log.info("添加成功");
            return AjaxResult.success();
        }
        else {
            return AjaxResult.error("已存在相同标题或相同内容的通知");
        }
    }

    @PostMapping("/modify")
    public AjaxResult modifyNotice(@RequestBody Notice notice){
//        log.info("更新数据 Notice:{}",notice.toString());
        noticeService.modifyNotice(notice);
        return AjaxResult.success();
    }

    @DeleteMapping("/{ids}")
    public AjaxResult deleteNotice(@PathVariable List<Integer> ids)
    {
//        log.info("彻底删除操作, ids:{}",ids);
        noticeService.deleteNotice(ids);
        return AjaxResult.success();
    }

}
