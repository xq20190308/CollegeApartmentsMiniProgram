package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalSchoolInfoVO;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:08
 * @Version: 1.0
 */
public interface SchoolnfoService {
    //直接数据库强查，耗时五六秒
    TotalSchoolInfoVO getAllSchoolInfo();

    //仅仅操作一次数据库，优化了算法，1s不到
    TotalSchoolInfoVO getAllSchoolInfoBetter();


}
