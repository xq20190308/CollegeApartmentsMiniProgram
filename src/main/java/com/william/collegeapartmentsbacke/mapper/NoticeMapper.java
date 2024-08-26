package com.william.collegeapartmentsbacke.mapper;


import com.william.collegeapartmentsbacke.pojo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoticeMapper {

//    @Select("select * from Notification.announcements")
    List<Notice> list(@Param("id") Integer id,
                      @Param("keyword") String keyword,
                      @Param("type_name") String typeName,
                      @Param("publish_time_st") LocalDateTime publish_time_st,
                      @Param("publish_time_ed") LocalDateTime publish_time_ed,
                      @Param("is_active") Boolean isActive);

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
