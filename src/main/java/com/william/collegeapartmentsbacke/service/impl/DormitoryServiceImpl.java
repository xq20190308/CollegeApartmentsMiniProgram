package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.basicInfo.BuildiingInfoMapper;
import com.william.collegeapartmentsbacke.mapper.basicInfo.CampusInfoMapper;
import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.BuildingInfo;
import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.CampusInfo;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.*;
import com.william.collegeapartmentsbacke.service.DormitoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Autowired
    private CampusInfoMapper campusInfoMapper;
    @Autowired
    private BuildiingInfoMapper buildiingInfoMapper;

    @Override
    public TotalDormitoryInfoVO getAllDormitoryInfo() {
        TotalDormitoryInfoVO totalDormitoryInfoVO = new TotalDormitoryInfoVO();
        totalDormitoryInfoVO.setCampusInfoVOList(new ArrayList<>());
        List<CampusInfo> campusInfos = campusInfoMapper.selectAll();
        List<BuildingInfo> buildingInfos = buildiingInfoMapper.selectAll();

        for (CampusInfo campusInfo : campusInfos) {
            Integer campusId = campusInfo.getCampusId();
            CampusInfoIncludeDormitoryVO campusInfoVO = new CampusInfoIncludeDormitoryVO();
            campusInfoVO.setCampusId(campusInfo.getCampusId());
            campusInfoVO.setCampusName(campusInfo.getCampusName());
            campusInfoVO.setBuildings(new ArrayList<>());

            for (BuildingInfo buildingInfo : buildingInfos) {

                if(!Objects.equals(buildingInfo.getCampusId(), campusId)){
                    continue;
                }
                BuildingInfoVO buildingInfoVO = new BuildingInfoVO();
                buildingInfoVO.setBuildingId(buildingInfo.getBuildingId());
                buildingInfoVO.setBuildingName(buildingInfo.getBuildingName());
                buildingInfoVO.setFloors(new ArrayList<>());
                for (int i = 0; i < buildingInfo.getFloorNum(); i++) {
                    FloorInfoVO floorInfoVO = new FloorInfoVO();
                    floorInfoVO.setFloorId(i+1);
                    floorInfoVO.setFloorName(String.format("%d", i+1)+"楼");
                    floorInfoVO.setDormitorys(new ArrayList<>());
                    for (int j = 0; j < buildingInfo.getDormitoryNum(); j++) {
                        DormitoryInfoVO dormitoryInfoVO = new DormitoryInfoVO();
                        dormitoryInfoVO.setDormitoryId(i+1);
                        dormitoryInfoVO.setDormitoryName(String.format("%01d",i+1)+String.format("%02d", j+1));
                        floorInfoVO.getDormitorys().add(dormitoryInfoVO);
                    }
                    buildingInfoVO.getFloors().add(floorInfoVO);
                }
                campusInfoVO.getBuildings().add(buildingInfoVO);
            }

            totalDormitoryInfoVO.getCampusInfoVOList().add(campusInfoVO);
        }
        return totalDormitoryInfoVO;
    }
}
