package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.collegeapartmentsbacke.common.properties.WeChatProperties;
import com.william.collegeapartmentsbacke.mapper.DictMapper;
import com.william.collegeapartmentsbacke.mapper.LeavePostMapper;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.dto.SubscribeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.DictItem;
import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import com.william.collegeapartmentsbacke.service.DictService;
import com.william.collegeapartmentsbacke.service.LeavePostService;
import com.william.collegeapartmentsbacke.service.MailService;
import com.william.collegeapartmentsbacke.service.SubsribehttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class LeavePostServiceImpl implements LeavePostService {

    @Autowired
    private LeavePostMapper leavePostMapper;
    @Autowired
    private SubsribehttpService wxCommonService;
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @Override
    public List<LeavePost> getAllLeavePosts() {
        return leavePostMapper.selectList(null);
    }

    @Override
    public int insertLeavePost(LeavePost leavePost) {
        int result = leavePostMapper.insert(leavePost);
        if(result>0){
            //给审核人发邮件
            String reviewerId = leavePost.getReviewerId();
            //获取邮箱
//            String email = userMapper.getUserByUserid(reviewerId).getEmail();
            String email = "1844118046@qq.com";
            ToEmail toEmail = new ToEmail(new String[]{"1844118046@qq.com"}, "新的通知", "有新提交的请假条，请登录后查看",null);

            Result result1 = mailService.commonEmail(toEmail);
            log.info("发邮件{}",result1.toString());
        }
        return result;
    }

    @Override
    public Result updateLeavePost(LeavePost leavePost) {
        if (leavePostMapper.updateById(leavePost)>0){
            String dict_type = "fun_leave_post_status";
            String status = dictService.getDictItem(dict_type,leavePost.getStatus());
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            // 定义日期时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // 格式化当前时间
            String formattedNow = now.format(formatter);
            // 输出日志
            log.info("更新now: {}", formattedNow);

            String userId = leavePost.getUserId();
            String templateId = "boxg-cojQ07hdlJYJup5R6JzE8sfp815eCgdmwYiPMM";
            SubscribeDTO subscribeDTO = new SubscribeDTO();
            subscribeDTO.setTemplateId(templateId);
            subscribeDTO.setUserid(userId);
            subscribeDTO.setPage("pages/home/home");
            subscribeDTO.setData("{\"thing1\":{\"value\":\"审核结果通知\"},\"phrase3\":{\"value\":\""+status+"\"},\"time4\":{\"value\":\""+formattedNow+"\"}}");
            //请求 微信接口 获取 accessToken
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return Result.error("获得access_token失败");
            log.info("向{}发订阅{}消息{}：{}",subscribeDTO.getUserid(),subscribeDTO.getTemplateId(), subscribeDTO.getPage(),subscribeDTO.getData());
            String openid=userMapper.findOpenidByUsername(subscribeDTO.getUserid());
            String result = wxCommonService.sendSubscribeMessage(accessToken,openid, subscribeDTO.getTemplateId(), subscribeDTO.getPage(),subscribeDTO.getData());
            return Result.success(JSONObject.parseObject(result));
        }
        return Result.error("更新失败");
    }

    @Override
    public List<LeavePost> getByReviewerId(String reviewerId) {
        QueryWrapper<LeavePost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewer_id",reviewerId);
        return leavePostMapper.selectList(queryWrapper);
    }

    @Override
    public List<LeavePost> getByUserId(String userId) {
        QueryWrapper<LeavePost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        //按照创建时间降序
        queryWrapper.orderByAsc("created_at");
        return leavePostMapper.selectList(queryWrapper);
    }
}
