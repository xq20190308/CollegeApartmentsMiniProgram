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


    //上传文件
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result Savefile(List<MultipartFile>files, HttpServletRequest request) {
        List<String> uploadUrl = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!ObjectUtils.isEmpty(file)) {
                try {
                    // 为每个文件生成一个唯一的ID
                    String ID = String.valueOf(UUID.randomUUID());
                    // 确保文件名不为空，并且获取文件扩展名
                    String originalFilename = file.getOriginalFilename();
                    if (originalFilename != null && originalFilename.lastIndexOf(".") != -1) {
                        String filename = ID + originalFilename.substring(originalFilename.lastIndexOf("."));
                        // 获取文件的MIME类型
                        String filetype = file.getContentType();
                        // 构建文件的URL路径
                        String Path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/static/" + filename;
                        // 读取文件字节
                        byte[] b = file.getBytes();
                        // 创建文件上传对象
                        Uploadfile loadFile = new Uploadfile(ID, filename, filetype, Path, b);
                        // 将文件保存到服务器
                        file.transferTo(new File(localFileUrl + filename));
                        // 保存文件信息到数据库
                        suggestionmapper.savefile(loadFile);
                        // 将文件的URL路径添加到结果列表中
                        uploadUrl.add(Path);
                    }
                } catch (IOException e) {
                    // 处理异常情况
                }
            }
        }
        return Result.success(String.join(",",uploadUrl));
    }


    //获取文件
    @Override
    public String Selectfile(String id) {
        return suggestionmapper.selectfile(id);
    }
}
