package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FileEntity {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Uploadfile {
        private String id;
        private String userid;
        private String name;
        private String Type;
        private String Path;
        private byte[] data;
        private String usedType;
    }
}
