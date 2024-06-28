package com.william.collegeapartmentsbacke.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    public static String toPinyin(String chineseStr) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
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
    public static boolean isEnglish(String str) {
        return str.matches("^[A-Za-z0-9\\s]*$");
    }

    public static boolean isChinese(String str) {
        // 正则表达式匹配中文字符
        // \u4e00-\u9fa5 匹配基本汉字
        // \uF900-\uFA2D 匹配扩展的汉字
        return str.matches(".*[\u4e00-\u9fa5|\uF900-\uFA2D]+.*");
    }

    public static Character getFirstLetter(String str) throws BadHanyuPinyinOutputFormatCombination {
        Character firstLetter = null;
        if(isChinese(str)) {
            String strPinyin = toPinyin(str);
            firstLetter = strPinyin.charAt(0);
        } else if (isEnglish(str)) {
            firstLetter = str.toUpperCase().charAt(0);

        } else {
            firstLetter = str.charAt(0);
        }
        return firstLetter;
    }

}
