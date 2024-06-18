package com.william.collegeapartmentsbacke.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    public static String toPinyin(String chineseStr) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder sb = new StringBuilder();
        char[] chars = chineseStr.toCharArray();
        for(char c : chars) {
            String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c,format);
            if(pinyin != null) {
                sb.append(pinyin[0]);
            }
            else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
