package xyz.huanxicloud.findjob.controller;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;

import java.util.Objects;

@RequestMapping("/user")
@RestController
public class UserContrller {
    @GetMapping("/getInfo")
    public ReturnMessage getInfo(String token){
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        return new ReturnMessage(1,claims);
    }
    private String getToken(HttpHeaders headers){
        return Objects.requireNonNull(headers.get("Token")).toString();
    }
    /**
     * 获取当前用户简历信息
     */
    public ReturnMessage getUserResume(@RequestHeader HttpHeaders headers){
        return new ReturnMessage();
    }
    /**
     * 保存当前用户简历信息
     */
    public ReturnMessage saveUserResume(@RequestHeader HttpHeaders headers){
        return new ReturnMessage();
    }
}
