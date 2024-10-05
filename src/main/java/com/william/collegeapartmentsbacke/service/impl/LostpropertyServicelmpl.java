package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.william.collegeapartmentsbacke.mapper.LostpropertyMapper;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.PageResults;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class LostpropertyServicelmpl implements LostpropertyService {
    @Autowired
    private LostpropertyMapper lostpropertyMapper;

    @Override
    public void saveSubmit(Itemdata itemdata) {
        itemdata.setStatus(0);
        itemdata.setSolve(0);
        lostpropertyMapper.saveSubmit(itemdata);
    }

    @Override
    public PageResults<Itemdata> getItemData(String category, PageDTO pagePara) {
        Page<PageDTO> page=new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getEachPageCount() == null ? 5 : pagePara.getEachPageCount());
        IPage<Itemdata> queryResult = lostpropertyMapper.selectAll(category,page,pagePara);
        PageDTO pageResult = new PageDTO(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        PageResults<Itemdata> result = new PageResults<>();
        result.setPageCount(pageResult.getPageCount());
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        return result;
    }

    @Override
    public List<Itemdata> getUserItemdata(String stuid) {
        return lostpropertyMapper.selectUserAll(stuid);
    }

    @Override
    public void updateSolve(Integer id, Integer solve) {
        lostpropertyMapper.updateSolve(id,solve);
    }

    @Override
    public void updateItemdata(Itemdata itemdata) {

        lostpropertyMapper.updateAll(itemdata);
    }
    @Override
    public void deleteItemdata(Integer id)
    {
         lostpropertyMapper.deleteData(id);
    }

}
