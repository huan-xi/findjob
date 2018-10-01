package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    PositionService positionService;
    @GetMapping("getPositions")
    public ReturnMessage getPositions(int page,int size){
        return positionService.getPositions(page,size);
    }
    @PostMapping("/publicPosition")
    public ReturnMessage publicPosition(Position position){
         return positionService.publicPosition(position);
    }
    @PostMapping("/uploadVideo")
    public ReturnMessage uploadVideo(MultipartFile video){
        return new ReturnMessage(1,"image/head_img/72f3dfac-4fce-488d-b547-b9310c510059.mp4");
        /*String filename = UUID.randomUUID().toString() + "." + video.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) video.getInputStream(), filename, "image/head_img/","f:\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ossFile)) {
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");*/
    }
}
