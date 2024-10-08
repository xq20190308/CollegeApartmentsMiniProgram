package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.AnswerStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/8/21 21:25
 * @Version: 1.0
 */
@Mapper
public interface AnswerStatisticsMapper {
    /**
     * 批量插入统计结果。
     *
     * @param naireId 统计结果列表
     */

    List<AnswerStatistics> selectByNaireId(Integer naireId);


    void batchInsert(@Param("answerStatisticsList") List<AnswerStatistics> answerStatisticsList);

}
