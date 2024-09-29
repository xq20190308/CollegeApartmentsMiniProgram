package com.william.collegeapartmentsbacke.pojo.entity.questionnaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCount {
    private Integer questionId;
    private String answerType;
    private List<String> choiceSumList;


    //使列表中的选项自增1
    public void incrementChoiceAtIndex(int index) {
        try {
            if (index >= 0 && index < choiceSumList.size()) {
                Integer choiceSum = Integer.parseInt(choiceSumList.get(index)) + 1;

                choiceSumList.set(index, choiceSum.toString());
            } else {
                // 索引超出范围的处理，例如抛出异常或打印错误信息
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + choiceSumList.size());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("索引超出范围");
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

