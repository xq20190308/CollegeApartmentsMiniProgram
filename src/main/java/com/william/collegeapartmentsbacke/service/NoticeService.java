package com.william.collegeapartmentsbacke.service;


import com.william.collegeapartmentsbacke.pojo.dto.NoticeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Notice;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {
    List<Notice> list(Integer id, String title, String content, String type_name, LocalDateTime publish_time_st, LocalDateTime publish_time_ed, Boolean is_active);

    Boolean addNotice(NoticeDTO noticeDTO);

    void deleteNotice(List<Integer> ids);

    void modifyNotice(Notice notice);


}
