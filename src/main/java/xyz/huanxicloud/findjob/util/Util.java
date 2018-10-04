package xyz.huanxicloud.findjob.util;

import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("deprecation")
public class Util {
    public static boolean isSameDay(Long d,Long dd){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        return  formatter.format(d).equals(formatter.format(dd));
    }

    public static Long getTodayTime() {
        Date today=new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        return today.getTime();
    }
}
