package com.william.collegeapartmentsbacke.service.impl;


import com.william.collegeapartmentsbacke.mapper.NoticeMapper;
import com.william.collegeapartmentsbacke.pojo.Notice;
import com.william.collegeapartmentsbacke.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    //查询通知
    @Override
    public List<Notice> list(String title, String content, String type_name, LocalDateTime publish_time_st,LocalDateTime publish_time_ed,Boolean is_active) {
        if (publish_time_ed == null){
            publish_time_ed = LocalDateTime.now();
        }

        return noticeMapper.list(title,content,type_name,publish_time_st,publish_time_ed,is_active);
    }
    //添加新通知
    @Override
    public Boolean addNotice(Notice notice) {
        notice.setPublish_time(LocalDateTime.now());
        notice.setIs_active(true);
        //检查是否有重复title

        List<Notice> existNotices = noticeMapper.search(notice.getTitle(),notice.getContent());
        Boolean alreadyExist = (existNotices != null && !existNotices.isEmpty());
        if(alreadyExist)
        {
            return false;
        }
        else
        {
            noticeMapper.insertNotice(notice);
            return true;
        }


    }
    @Override
    public void disActicNotice(List<Integer> ids) {
        for(Integer id : ids){
            noticeMapper.disActiveNotice(id);
        }
    }
    @Override
    public void deleteNotice(List<Integer> ids) {
        noticeMapper.deleteNotice(ids);
    }



}
