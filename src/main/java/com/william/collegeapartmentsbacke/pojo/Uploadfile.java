package com.william.collegeapartmentsbacke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Uploadfile {
    private String id;
    private String user_id;
    private String name;
    private String Type;
    private String Path;
    private byte[] data;
}
