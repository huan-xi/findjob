package xyz.huanxicloud.findjob.util;

import com.qiniu.util.Auth;

/**
 * @author: huanxi
 * @date: 2019/2/15 18:17
 */
public class QiniuOssUtil {
    private static String accessKey = "oZ30WPkk-yxyFJ6xfyJtZ7GIDZgPTkdlRZK0M5xq";
    private static String secretKey = "94i3M0NYN2h-vb_0hSwstviYLR5jGDNDCAUAamA_";
    private static String bucket = "findjob";

    public static String getUpToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
