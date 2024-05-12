package com.william.collegeapartmentsbacke.mapper;


import com.william.collegeapartmentsbacke.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> list(@Param("title") String title,
                      @Param("content") String content,
                      @Param("type_name") String type_name,
                      @Param("publish_time_st") LocalDateTime publish_time_st,
                      @Param("publish_time_ed") LocalDateTime publish_time_ed,
                      @Param("is_active") Boolean is_active);

    void insertNotice(Notice notice);

    List<Notice> search(@Param("title") String title,
                      @Param("content") String content);

    void deleteNotice(@Param("ids") List<Integer> ids);

    void updateNotice(Notice notice);

//    //根据typename查询这类通知的id
//    int findTyoeId(String type_name);
//    //查询所有分类
//    List<String> getAllTyoeName();
//    //添加分类
//    void insertType(String type_name);
    


}
