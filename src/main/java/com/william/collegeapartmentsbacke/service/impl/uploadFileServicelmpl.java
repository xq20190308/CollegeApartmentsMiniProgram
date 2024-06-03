package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.mapper.SuggestionMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.service.UserService;
import com.william.collegeapartmentsbacke.service.uploadFileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class uploadFileServicelmpl implements uploadFileService {
    @Autowired
    private SuggestionMapper suggestionmapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Value("${localFileUrl}")
    private String localFileUrl;

    @Value("${mapFileUrl}")
    private String mapFileUrl;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result Savefile(String token, List<MultipartFile>files, HttpServletRequest request) {
        String userid=userService.getUseridFromToken(token);
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
                        String Path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + mapFileUrl + filename;
                        // 读取文件字节
                        byte[] b = file.getBytes();
                        // 创建文件上传对象
                        Uploadfile loadFile = new Uploadfile(ID,userid,filename, filetype, Path, b);
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

}
