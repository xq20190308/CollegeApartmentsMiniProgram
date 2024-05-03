package com.william.collegeapartmentsbacke.service;


import com.william.collegeapartmentsbacke.pojo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> list(Boolean isActive);

    void addNotice(Notice notice);
}
