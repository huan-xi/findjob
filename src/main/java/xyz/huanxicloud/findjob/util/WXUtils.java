package xyz.huanxicloud.findjob.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class WXUtils {
    private static final String USER_APPID = "wx7ae86a27959c0d1b";
    private static final String USER_SECRET = "d930da378c078da47bef57974650231c";

    public static String getUserAppid() {
        return USER_APPID;
    }

    public static String getUserSecret() {
        return USER_SECRET;
    }

    public static WXInfo getWxInfo(String code,String appId, String secret) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作         
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            WXInfo wxInfo= JSON.parseObject(sessionData,WXInfo.class);
            return wxInfo;
        }
        return null;
    }
}
