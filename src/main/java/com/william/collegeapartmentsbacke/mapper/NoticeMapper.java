package com.william.collegeapartmentsbacke.mapper;


import com.william.collegeapartmentsbacke.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> list(Boolean is_active);

    void insertNotice(Notice notice);
    
    
//    //根据typename查询这类通知的id
//    int findTyoeId(String type_name);
//    //查询所有分类
//    List<String> getAllTyoeName();
//    //添加分类
//    void insertType(String type_name);
    


}
