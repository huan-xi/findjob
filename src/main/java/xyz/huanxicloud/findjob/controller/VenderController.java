package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtVenderUtil;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

@RestController
@RequestMapping("/vender")
public class VenderController {
    @Autowired
    PositionService positionService;
    @Autowired
    VenderService venderService;
    @PostMapping("/editVender")
    public ReturnMessage editVender(@RequestHeader("Token") String token, Vender vender){
        String id= JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.editVender(id,vender);
    }
    @GetMapping("/getInfo")
    public ReturnMessage getInfo(@RequestHeader("Token") String token){
        String id= JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.getInfo(id);
    }

    /**
     * 获取已发布的职位
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getPositions")
    public ReturnMessage getPositions(@RequestHeader("Token") String token,int page,int size){
        String id=JwtVenderUtil.getVenderIdFromToken(token);
        return positionService.getPositionsByVender(id,page,size);
    }
    @PostMapping("/publicPosition")
    public ReturnMessage publicPosition(@RequestHeader("Token") String token,Position position){
        Vender vender= JwtVenderUtil.getVenderFormToken(token);
        return positionService.publicPosition(vender.getVenderId(),vender.getName(),position);
    }

    /**
     * 上传视频
     * @param video
     * @return
     */
    @PostMapping("/uploadVideo")
    public ReturnMessage uploadVideo(MultipartFile video){
       return new ReturnMessage(1,"test");
        /* String temp=System.getProperty("os.name").contains("indow")?"f:\\": "/var/tmp/upload";
        String filename = UUID.randomUUID().toString() + "." + video.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) video.getInputStream(), filename, "image/head_img/",temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ossFile)) {
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");*/
    }
}
