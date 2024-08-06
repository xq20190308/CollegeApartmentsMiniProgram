/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.william.collegeapartmentsbacke.common.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Slf4j

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User>{
    private Integer id;
    private String openid;
    private String username;
    private String password;
    private String trueName;
    private String userid;
    private String phone;
    private String avatarUrl;
    private String userLevel;
    private String dormitory;

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(User o) {
        String pinyinOfName1 = new String();
        String pinyinOfName2 = new String();
//        pinyinOfName1 = this.getName();
//        pinyinOfName2 = o.getName();
        try {
            if(PinyinUtil.isEnglish(this.getTrueName()))
            {
                pinyinOfName1 = this.getTrueName().toUpperCase();
            }
            else
            {
                pinyinOfName1 = PinyinUtil.toPinyin(this.getTrueName());
            }
            if(PinyinUtil.isEnglish(o.getTrueName()))
            {
                pinyinOfName2 = o.getTrueName().toUpperCase();
            }
            else
            {
                pinyinOfName2 = PinyinUtil.toPinyin(o.getTrueName());
            }
            log.info("pinyinOfName1:" + pinyinOfName1);
            log.info("pinyinOfName2:" + pinyinOfName2);
            log.info(String.valueOf(pinyinOfName1.compareTo(pinyinOfName2)));
            return pinyinOfName1.compareTo(pinyinOfName2);

        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new RuntimeException(e);
        }
    }
}