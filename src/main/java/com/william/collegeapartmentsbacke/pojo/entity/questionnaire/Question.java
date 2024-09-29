package com.william.collegeapartmentsbacke.pojo.entity.questionnaire;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("coap.question")
public class Question implements Comparable<Question> {
    private Integer id;
    private String type;
    private String name;
    private String description;
    private String content;
    private Integer questionnaireId;

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Question o) {
        return this.type.compareTo(o.type);
    }
}
