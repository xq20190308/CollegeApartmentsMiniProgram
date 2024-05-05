package com.william.collegeapartmentsbacke.service;


import com.william.collegeapartmentsbacke.pojo.Notice;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {
    List<Notice> list(String title, String content, String type_name, LocalDateTime publish_time_st, LocalDateTime publish_time_ed, Boolean is_active);

    Boolean addNotice(Notice notice);

    void deleteNotice(List<Integer> ids);

    void disActicNotice(List<Integer> ids);
}
