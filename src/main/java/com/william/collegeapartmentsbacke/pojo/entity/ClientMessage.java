package com.william.collegeapartmentsbacke.pojo.entity;
import cn.hutool.json.JSONArray;
import com.william.collegeapartmentsbacke.common.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage {
    private String senderUserId;
    private Integer type;
    private String data;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
    private String receivers;



    public List<String> getReceiversStrList()
    {
        List<Object> receiversObj = JsonUtil.jsonArrayToList(new JSONArray(receivers));
        List<String> receiversUserids = new ArrayList<>();
        for (Object receiverObj : receiversObj) {
            receiversUserids.add(receiverObj.toString());
        }

        return receiversUserids;
    }
}
