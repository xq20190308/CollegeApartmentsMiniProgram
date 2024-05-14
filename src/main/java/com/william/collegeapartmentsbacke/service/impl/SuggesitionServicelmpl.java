package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.SuggestionMapper;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SuggesitionServicelmpl implements SuggestionService {
    @Autowired
    private SuggestionMapper suggestionmapper;

    //提交投诉
    @Override
    public void SubmitSuggestion(Suggestion suggestion) {
        suggestionmapper.submit(suggestion);
    }

    //查询草稿
    @Override
    public List<Suggestion> SelectDraftfindall()
    {
        return suggestionmapper.Draftfindall();
    }

   //查询用户投诉
    @Override
    public
    List<Suggestion> Selectfindall()
    {
        return suggestionmapper.findall();
    }

    //保存草稿
    @Override
    public void Savedaft(Suggestion suggestion)
    {
        suggestionmapper.savedaft(suggestion);
    }

    //删除草稿
    @Override
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

    //上传文件
    @Override
    public String uploadFile(@RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty())
        {
            String fileName = file.getOriginalFilename();
            try {
                String filePath = "D:/Desktop/Smart-MergeCode/src/main//resources/static/uploadpicture/" + fileName;
                file.transferTo(new File(filePath));
                return "success";
            } catch (IOException e) {
                e.printStackTrace();
                return "file upload fail";
            }
        }
        else
        {
            return"file is empty";
        }
    }
}
