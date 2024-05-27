package com.william.collegeapartmentsbacke.service.impl;


import com.william.collegeapartmentsbacke.mapper.NoticeMapper;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Notice;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private UserMapper userMapper;

    //查询通知
    @Override
    @Transactional
    public List<Notice> list(String title, String content, String typeName, LocalDateTime publish_time_st,LocalDateTime publish_time_ed,Boolean isActive) {
        if (publish_time_ed == null){
            publish_time_ed = LocalDateTime.now();
        }
        List<Notice> test = noticeMapper.list(title,content,typeName,publish_time_st,publish_time_ed,isActive);
        return noticeMapper.list(title,content,typeName,publish_time_st,publish_time_ed,isActive);
    }
    //添加新通知
    @Transactional
    @Override
    public Boolean addNotice(Notice notice) {
        notice.setPublishTime(LocalDateTime.now());
        notice.setIsActive(true);
        //检查是否有重复title

        List<Notice> existNotices = noticeMapper.search(notice.getTitle(),notice.getContent());
        boolean alreadyExist = (existNotices != null && !existNotices.isEmpty());
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
    @Transactional
    @Override
    public void modifyNotice(Notice notice) {
        notice.setPublishTime(LocalDateTime.now());
        noticeMapper.updateNotice(notice);
    }

    @Transactional
    @Override
    public void deleteNotice(List<Integer> ids) {
        noticeMapper.deleteNotice(ids);
    }


}
