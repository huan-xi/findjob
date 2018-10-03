package xyz.huanxicloud.findjob;

import org.junit.Test;
import xyz.huanxicloud.findjob.util.Util;

import java.text.SimpleDateFormat;

public class TestNoString {
    @Test
    public void test(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        System.out.println(formatter.format(1538524800000L));
        System.out.println(formatter.format( 1538611200000L));
        System.out.println(Util.isSameDay(1538524800000L, 1538611200000L));
    }
}
