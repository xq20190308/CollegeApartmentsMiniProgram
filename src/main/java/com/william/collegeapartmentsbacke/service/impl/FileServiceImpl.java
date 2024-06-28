package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.william.collegeapartmentsbacke.mapper.FileMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    public static final String AVATAR = "avatar";
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserService userService;

    @Value("${localFileUrl}")
    private String localFileUrl;

    @Value("${mapFileUrl}")
    private String mapFileUrl;

    @Override
    public Uploadfile SaveSingleFile(String userid, MultipartFile file, HttpServletRequest request) {
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
                    fileMapper.savefile(loadFile);
                    // 将文件的URL路径添加到结果列表中

                    return loadFile;
                }
            } catch (IOException e) {
                // 处理异常情况
                e.printStackTrace();
                e.getMessage();
                return null;
            }
            return null;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public String Savefile(String userid, List<MultipartFile>files, HttpServletRequest request) {
        List<String> uploadUrl = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!ObjectUtils.isEmpty(file)) {
                String savedPath = SaveSingleFile(userid, file, request).getPath();
                uploadUrl.add(savedPath);
            }
        }
        return String.join(",",uploadUrl);
    }

    //删除文件
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String DeletefileByUrl(String Url)
    {
        String filename = Url.substring(Url.lastIndexOf("/")+1);
        String filepath=localFileUrl+filename;
        Path path= Paths.get(filepath);
        try{
            if(Files.exists(path)) {
                Files.delete(path);
                return "success";
            }
            else
                return "fail";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return filename;
    }


    //获取文件路径
    @Override
    public String SelectfileById(String id) {
        return fileMapper.selectfile(id);
    }





}
