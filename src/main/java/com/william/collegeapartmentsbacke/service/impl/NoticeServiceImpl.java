package com.william.collegeapartmentsbacke.service.impl;


import com.william.collegeapartmentsbacke.mapper.NoticeMapper;
import com.william.collegeapartmentsbacke.pojo.Notice;
import com.william.collegeapartmentsbacke.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> list(Boolean isActive) {
        return noticeMapper.list(isActive);
    }
}
