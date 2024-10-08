package com.william.collegeapartmentsbacke.service;


import com.william.collegeapartmentsbacke.pojo.dto.NoticeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Notice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {
    List<Notice> list(Integer id, String keyword, String type_name, LocalDateTime publish_time_st, LocalDateTime publish_time_ed, Boolean is_active) throws IOException;

    Boolean addNotice(NoticeDTO noticeDTO);

    void deleteNotice(List<Integer> ids);

    void modifyNotice(Notice notice);


}
