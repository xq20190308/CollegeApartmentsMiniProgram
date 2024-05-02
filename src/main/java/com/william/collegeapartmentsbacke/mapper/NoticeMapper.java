package com.william.collegeapartmentsbacke.mapper;


import com.william.collegeapartmentsbacke.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> list(@Param("isActive") Boolean is_active);
}
