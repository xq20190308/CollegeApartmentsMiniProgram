package com.william.collegeapartmentsbacke.pojo.entity.websocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage {
    public static final Integer TYPE_COMMON_SINGLE = 1;
    public static final Integer TYPE_COMMON_GROUP = 2;
    public static final Integer TYPE_HISTORY_MESSAGE = 3;



    private Integer messageId;
    private String senderUserId;
    private Integer type;
    private String data;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
    private String receiver;
    private Boolean status;



//    public List<String> getReceiversStrList()
//    {
//        List<Object> receiversObj = JsonUtil.jsonArrayToList(new JSONArray(receivers));
//        List<String> receiversUserids = new ArrayList<>();
//        for (Object receiverObj : receiversObj) {
//            receiversUserids.add(receiverObj.toString());
//        }
//        return receiversUserids;
//    }
}