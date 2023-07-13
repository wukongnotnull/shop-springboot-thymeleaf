package com.wukongnotnull.shop.util;

/**
 * @author 悟空非空也
 */
public class StringUtil {

    public static String subStringUseEllipsis(String source,int maxNum){
        String target = "";
        if(source.length() > maxNum){
            target = source.substring(0,maxNum) + "...";
        }else {
            target = source;
        }
        return  target;
    }


}
