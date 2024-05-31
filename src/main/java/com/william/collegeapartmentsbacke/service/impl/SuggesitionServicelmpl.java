package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.william.collegeapartmentsbacke.mapper.SuggestionMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Suggestion;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SuggesitionServicelmpl implements SuggestionService {
    @Autowired
    private SuggestionMapper suggestionmapper;

    @Value("${localFileUrl}")
    private String localFileUrl;

    //提交投诉
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void SubmitSuggestion(Suggestion suggestion) {
        LocalDateTime pushtime=LocalDateTime.now();
        pushtime = pushtime.withNano(0);
//        if(suggestion.getPath()!=null){
//            String Path = String.join(",",suggestion.getPath().toString());
//            suggestion.setUrlpath(Path);
//        }
        suggestion.setPushtime(pushtime);
        suggestionmapper.submit(suggestion);
        }

    //查询草稿
    @Override
    public List<Suggestion> SelectDraftfindall(String stu_id)
    {
        return suggestionmapper.Draftfindall(stu_id);
    }


   //查询用户投诉
    @Override
    public List<Suggestion> Selectfindall()
    {
        return suggestionmapper.findall();
    }

    //保存草稿
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String Savedaft(Suggestion suggestion)
    {
        if(suggestionmapper.Draftselect(suggestion.getId())!=null) {
            suggestionmapper.updatedaft(suggestion);
            return "更新成功";
        }
        else{
            LocalDateTime pushtime=LocalDateTime.now();
            pushtime = pushtime.withNano(0);
            suggestion.setPushtime(pushtime);
            suggestionmapper.savedaft(suggestion);
            return "保存成功";
        }
    }


    //删除草稿
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSuggestion(long id)
    {
        if(suggestionmapper.Count()>0) {
            suggestionmapper.delete(id);
            return true;
        }
        else
        {
            return false;
        }
    }


    //获取文件
    @Override
    public String Selectfile(String id) {
        return suggestionmapper.selectfile(id);
    }
}
