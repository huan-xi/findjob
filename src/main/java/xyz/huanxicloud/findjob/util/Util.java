package xyz.huanxicloud.findjob.util;

import java.text.SimpleDateFormat;

public class Util {
    public static boolean isSameDay(Long d,Long dd){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        return  formatter.format(d).equals(formatter.format(dd));
    }
}
