package com.william.collegeapartmentsbacke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.william.collegeapartmentsbacke.pojo.entity.LeaveMentor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMentorMapper extends BaseMapper<LeaveMentor> {

    Integer addLeaveMentors(List<LeaveMentor> list);
}
