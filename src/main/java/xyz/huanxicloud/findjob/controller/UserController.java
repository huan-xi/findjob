package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.userservice.UserService;
import xyz.huanxicloud.findjob.util.AliOSSUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
@GetMapping("test")
public String test(@RequestHeader("Token") String token){
    return getUser(token).getUserId();
}
    /**
     * 获取当前用户信息
     * @param
     * @return
     */
    @GetMapping("/getInfo")
    public ReturnMessage getInfo(@RequestHeader("Token") String token){
        String id= JwtTokenUtil.getUserIdFromToken(token);
        return userService.getUserInfo(id);
    }
    @PostMapping("/uploadIdCard")
    public ReturnMessage uploadIdCard(@RequestHeader("Token") String token,MultipartFile idCard){
        String temp=System.getProperty("os.name").contains("indow")?"f:\\": "/var/tmp/upload";
        String filename ="";
        try {
            filename = UUID.randomUUID().toString() + "." + idCard.getContentType().split("/")[1];
        }catch (NullPointerException e){
            filename=UUID.randomUUID().toString() + ".no";
        }
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) idCard.getInputStream(), filename, "image/head_img/",temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ossFile)) {
            //上传到oss成功
            User user = getUser(token);
            if (user == null) return new ReturnMessage(1001, "上传失败");
            user.setIdcardSrc(ossFile);
            user.setValid("1");
            userService.updateByPrimaryKey(user);
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");
    }
    /**
     * 编辑信息
     * @return
     */
    @PostMapping("/editInfo")
    public ReturnMessage editInfo(@RequestHeader("Token") String token, String phone,String name, String types){
        String id= JwtTokenUtil.getUserIdFromToken(token);
        return userService.editInfo(id,  phone, name,  types);
    }

    private User getUser(String token){
        String id= JwtTokenUtil.getUserIdFromToken(token);
        return userService.findUserById(id);
    }
}
