package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCount {
    private Integer questionId;
    private Integer answerType;
    private List<Integer> choiceSumList;



    //使列表中的选项自增1
    public void incrementChoiceAtIndex(int index) {
        if (index >= 0 && index < choiceSumList.size()) {
            choiceSumList.set(index, choiceSumList.get(index) + 1);
        } else {
            // 索引超出范围的处理，例如抛出异常或打印错误信息
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + choiceSumList.size());
        }
    }

    @Override
    public String toString() {
        return "AnswerCount{" +
                "questionId=" + questionId +
                ", answerType=" + answerType +
                ", choiceSumList=" + choiceSumList +
                "}\n";
    }
}

