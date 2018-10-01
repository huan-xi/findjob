package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.util.AliOSSUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    PositionService positionService;
    @PostMapping("/publicPosition")
    public ReturnMessage publicPositon(Position position){
        return positionService.publicPosition(position);
    }
    @PostMapping("/uploadVideo")
    public ReturnMessage uploadVideo(MultipartFile video){
        String filename = UUID.randomUUID().toString() + "." + video.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) video.getInputStream(), filename, "image/head_img/","f:\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ossFile)) {
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");
    }
}
