package cn.wan.reptile.doubanRead.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 万洪基 on 2017/3/19.
 */
public class PageUtil {
    public static String doubanReadRegex(String targetString,String prefix,String suffix){
        Pattern pattern = Pattern.compile(prefix+".+"+suffix);
        Matcher matcher = pattern.matcher(targetString);
        String text = "";
        if (matcher.find()){
            text = matcher.group();
            text = text.replaceAll(prefix,"");
            text = text.replaceAll(suffix,"");
        }else{
            text = null;
        }
        return text.trim();
    }
}
