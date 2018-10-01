package xyz.huanxicloud.findjob.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class WXUtils {
    private static final String APPID = "wx9716d34719f937f1";
    private static final String SECRET = "772ef3d4995529da245906b04bd6ae50";

    public static WXInfo getWxInfo(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
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
