package com.william.collegeapartmentsbacke.pojo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage {
    private String type;
    private String data;
    private List<String> receivers;
}
