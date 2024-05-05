package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
public class Datalist {
        private Suggestion[] data;
}
